package com.nanal.thread.receiver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.nanal.db.AppResultDataDao;
import com.nanal.db.AppSendDataDao;
import com.nanal.dto.AppSendData;
import com.nanal.setting.GaonSetting;
import com.nanal.thread.inserter.GaonResultProcessor;
import com.nanal.thread.sender.PacketInfo;
import com.nanal.thread.timeout.TimeoutChecker;
import com.nanal.utils.SocketUtils;

import lombok.RequiredArgsConstructor;

@Scope("prototype")
@RequiredArgsConstructor
@Component
public class GaonReceiver implements Runnable {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	private final LinkedBlockingQueue<AppSendData> queue = new LinkedBlockingQueue<AppSendData>();

	private final TimeoutChecker checker;
	private final GaonSetting setting;
	private final Logger rootLogger;
	private final Logger recvLogger;
	private final AppSendDataDao dataDao;
	private final AppResultDataDao resultDao;

	private int port;
	private Thread processThread;
	long lastRecvTime = -1;
	ByteBuffer msgHeader = ByteBuffer.allocate(PacketInfo.MSG_HEADER_TSIZE);

	private GaonResultProcessor makeGaonResultProcessor() {
		return new GaonResultProcessor(queue, recvLogger, resultDao, setting, checker);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		String ip = setting.getGwIp()[0];
		processThread = new Thread(makeGaonResultProcessor());
		processThread.start();

		lastRecvTime = System.currentTimeMillis();
		rootLogger.info(String.format("Gaon Receiver Thread Start port: " + getPort()));
		while (true) {
			if (!processThread.isAlive()) {
				rootLogger.info("GaonResultProcessor가 정지 상태 입니다. 재기동합니다");
				processThread = new Thread(makeGaonResultProcessor());
				processThread.start();
				rootLogger.info("GaonResultProcessor를 재기동하였습니다");
			}

			// 아직 응답받지 않은 데이터가 존재 하는지 확인하기
			int count = 0;
			try {
				count = dataDao.countAppSendDataWhereSendComplete();
			} catch (Exception e) {
				rootLogger.error("DB에서 데이터 불러오기 실패", e);
				try {
					TimeUnit.MINUTES.sleep(1);
				} catch (InterruptedException e1) {
					Thread.currentThread().interrupt();
				}
			}

			// 아직 응답받지 않은 데이터가 존재하거나
			// 마지막으로 응답받은 시간에서 일정시간 이상 경과한 경우
			if (count > 0 || System.currentTimeMillis() - lastRecvTime >= TimeUnit.MINUTES.toMillis(10)) {
				lastRecvTime = System.currentTimeMillis();
				// 아직 응답받지 않은 데이터가 존재하면
				SocketChannel sc = null;
				try {
					rootLogger.debug(String.format("레포트 서버 연결 시도 PORT(%d)", port));
					sc = SocketChannel.open();
					sc.connect(new InetSocketAddress(ip, port));
					sc.configureBlocking(true);
					sc.socket().setSoTimeout(10000);
					rootLogger.debug(String.format("레포트 서버 연결 성공 PORT(%d)", port));
					rootLogger.debug(String.format("레포트 서버 정보 보내기 PORT(%d)", port));
					ByteBuffer headerBuf = PacketInfo.createMsgHeader(PacketInfo.INCOMMON_HEADER_SIZE, "2", "1");
					ByteBuffer byteBuffer = PacketInfo.createCommonPacket(null, 0, "REP", "R", 0,
							false, setting.getGwClientId(), setting.getGwClientPwd(), null,
							String.format("%s-000000010001%s", dateFormat.format(new Date()),
									setting.getGwClientId()));

					try {
						while (headerBuf.hasRemaining() || byteBuffer.hasRemaining()) {
							sc.write(new ByteBuffer[] { headerBuf, byteBuffer });
						}
					} catch (IOException writeException) {
						rootLogger.error(String.format("소켓 쓰기 실패 PORT(%d)", port), writeException);
						try {
							Thread.sleep(5000);
							sc.close();
							continue;
						} catch (Exception e) {
							rootLogger.error("Sleep fail", e);
						}
					}

					rootLogger.info(String.format("레포트 받기 시작 PORT(%d)", port));

					int recvCnt = 0;

					try {
						while (true) {
							msgHeader.clear();
							SocketUtils.read(msgHeader, sc);

							int packetLength = 0;
							String header = new String(msgHeader.array(), "UTF-8");

							if (!header.startsWith("NANAL")) {
								throw new Exception(String.format("잘못된 헤더입니다. (%s)", header));
							}

							try {
								packetLength = Integer.parseInt(header.substring(5, 10));
							} catch (NumberFormatException e) {
								rootLogger.error("잘못된 헤더 형식입니다!!", e);
								throw e;
							}

							String type = header.substring(11, 12);

							if (type.equals("3")) {
								ByteBuffer dataBuf = ByteBuffer.allocate(packetLength - 2);
								SocketUtils.read(dataBuf, sc);
								String respondData = new String(dataBuf.array(), "UTF-8").trim();
								final String[] resData = respondData.split("!");
								// 포맷이 맞는지 확인
								if (resData.length != ReceiverCommon.RESPONSE_DATA_SIZE) {
									rootLogger.info(String.format(
											"레포트 길이가 맞지 않습니다 응답온 길이: %d, 필요한 길이 : %d",
											resData.length, ReceiverCommon.RESPONSE_DATA_SIZE));
									rootLogger.info(String.format(
											"응답받은 데이터 포맷이 맞지 않음 : rawdata(%s)", respondData));
									ByteBuffer respond = PacketInfo.createRespond2("2", "2000");
									while (respond.hasRemaining()) {
										sc.write(respond);
									}
									continue;
								}

								recvLogger.info("SERIAL_NUMBER(" + resData[ReceiverCommon.SERIAL_NUMBER_INDEX]
										+ ") RESPOND_DATA(" + respondData + ")");

								AppSendData d = ReceiverCommon.getAppResultDataFromRespond(resData);

								recvLogger.info("레포트 데이터 버터 입력 SERIAL_NUMBER(" + d.getSerialNumber() + ")");
								recvLogger.debug("Report data: " + d);
								queue.put(d);
								ByteBuffer respond = PacketInfo.createRespond2("2", "0000");
								recvCnt++;
								sc.write(respond);

								try {
									Thread.sleep(5);
								} catch (InterruptedException e) {
									Thread.currentThread().interrupt();
								}
							} else {
								ByteBuffer codeBuf = ByteBuffer.allocate(PacketInfo.MSG_RESULT_TSIZE);
								SocketUtils.read(codeBuf, sc);

								String code = new String(codeBuf.array(), "UTF-8");
								if (code.equals("0000")) {
									rootLogger.info(String.format("응답 받은 개수 : %d", recvCnt));
								} else {
									rootLogger.info(
											String.format("서버로부터 에러: PORT(%d) : %s", port, code));
								}

								break;
							}
						}
					} catch (Exception e) {
						rootLogger.error("소켓 읽기 실패 !!", e);
					}
				} catch (Exception e) {
					// 소켓 연결 실패
					recvLogger.error(String.format("레포트 소켓 서버 연결 실패 PORT(%d)", port), e);
				} finally {
					try {
						if (sc != null) {
							sc.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			// 일정 주기로 쉬기
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

}
