package com.nanal.thread.sender;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nanal.db.AppSendDataDao;
import com.nanal.db.DBCommon;
import com.nanal.db.MessageStateCode;
import com.nanal.dto.AppSendData;
import com.nanal.jsonobject.attachment.AppMsgAttachment;
import com.nanal.jsonobject.attachment.AppMsgButton;
import com.nanal.setting.GaonSetting;
import com.nanal.thread.GateWayServerInfo;
import com.nanal.utils.StringUtils;

import lombok.RequiredArgsConstructor;

@Scope("prototype")
@RequiredArgsConstructor
@Component
public class GaonSender implements Runnable {
	SimpleDateFormat sendTimeDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	private final int MSG_DATA_LENGTH_LIMIT = 990; // 카카오 최대 문자수
	private final GaonSetting setting;
	private final AppSendDataDao dao;
	private final Logger sendLogger;
	private final Logger rootLogger;
	private final DBCommon dbCommon;

	private TreeMap<String, Integer> phoneCount = new TreeMap<String, Integer>();
	private int oldDay;
	private ByteBuffer buf = ByteBuffer.allocate(10240);

	/**
	 * 변수 초기화
	 */
	private void init() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		oldDay = cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 패킷 전송
	 * 
	 * @param bs    write stream
	 * @param is    read stream
	 * @param data  보낼 데이터
	 * @param conti S: 연속 발송 시작, C: 연속 발송 중, E: 연속 발송 끝, F: 단일 패킷
	 * @throws Exception error
	 */
	private String sendpacket(BufferedOutputStream bs, BufferedInputStream is, ByteBuffer buf,
			AppSendData data, String conti) throws Exception {
		buf.clear();
		sendLogger.debug("[MESSAGE] MSG_SEQ(" + data.getMsgSeq() + ") SENDER HEADER");
		PacketInfo.createCommonPacket2(buf, data.getMsgSeq(), data.getAppGubun(), conti, 1, true,
				setting.getGwClientId(), setting.getGwClientPwd(), data.getSubId(),
				data.getSerialNumber());
		sendLogger.debug("[MESSAGE] MSG_SEQ(" + data.getMsgSeq() + ") SENDER BODY");
		AppMsgAttachment attachment = data.getMsgAttachment();
		String appGubun = data.getAppGubun();
		// BODY 부분 보내기
		if (appGubun.equals("KAKAO")) {
			PacketInfo.createKakaoBody2(buf, data, attachment);
		} else if (appGubun.equals("NAVER")) {
			PacketInfo.createNaverBody2(buf, data, attachment);
		}
		sendLogger
				.debug(String.format("[MESSAGE] MSG_SEQ(%d) SENDER INDATAINFO", data.getMsgSeq()));
		// INDATAINFO 부분분
		PacketInfo.createIndataInfo3(buf, data);
		buf.flip();
		ByteBuffer headerBuf = PacketInfo.createMsgHeader(buf.limit(), "1", "1");
		bs.write(headerBuf.array(), headerBuf.position(), headerBuf.limit());
		bs.write(buf.array(), buf.position(), buf.limit());
		bs.flush();
		if (setting.getLoggerLevel().equals("DEBUG")) {
			sendLogger.debug("SEND START : NANAL");
			sendLogger.debug(String.format("PACKET SIZE : %05d", buf.limit() + 2));
			PacketInfo.incommonHeaderLog(buf, sendLogger);
			if (appGubun.equals("KAKAO")) {
				PacketInfo.kakaoBodyLog(buf, sendLogger);
			} else if (appGubun.equals("NAVER")) {
				PacketInfo.naverBodyLog(buf, sendLogger);
			}
			PacketInfo.indataInfoLog(buf, sendLogger);
		}
		sendLogger
				.debug(String.format("[MESSAGE] MSG_SEQ(%d) SENDER SEND FLUSH", data.getMsgSeq()));
		byte[] readBuf = new byte[PacketInfo.MSG_RESPOND_SIZE];
		String responseCode = "";
		if ((is.read(readBuf, 0, PacketInfo.MSG_RESPOND_SIZE)) > 0) {
			responseCode = new String(readBuf, 0, PacketInfo.MSG_RESPOND_SIZE);
			String code = responseCode.substring(PacketInfo.MSG_HEADER_TSIZE);
			sendLogger.info("MSG_SEQ(" + data.getMsgSeq() + ") RESPOND FROM SERVER: " + code);
			return code;
		} else {
			throw new Exception("서버로 부터 데이터 수신 실패");
		}
	}

	/**
	 * 서버에 인증정보 확인하기
	 * 
	 * @param info 서버 정보
	 */
	public boolean checkValidationIdPwd(GateWayServerInfo info) {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		try {
			rootLogger.info(String.format("ID : %s 인증 시작",
					(setting.getGwClientId().substring(0, setting.getGwClientId().length() - 2)
							+ "**")));
			Socket sock = new Socket(info.ip, info.port);
			sock.setSoTimeout(10000);
			ByteBuffer headerBuf = PacketInfo.createMsgHeader(PacketInfo.INCOMMON_HEADER_SIZE, "3", "1");
			ByteBuffer commonBuf = PacketInfo.createCommonPacket(null, 0, "L", "F", 0, false,
					setting.getGwClientId(), setting.getGwClientPwd(), null, f.format(new Date()));
			BufferedOutputStream bos = new BufferedOutputStream(sock.getOutputStream());
			BufferedInputStream bis = new BufferedInputStream(sock.getInputStream());

			bos.write(headerBuf.array(), headerBuf.position(), headerBuf.limit());
			bos.write(commonBuf.array(), commonBuf.position(), commonBuf.limit());
			bos.flush();

			byte[] res = new byte[PacketInfo.MSG_RESPOND_SIZE];

			if ((bis.read(res, 0, PacketInfo.MSG_RESPOND_SIZE)) > 0) {
				String respond = new String(res, 0, PacketInfo.MSG_RESPOND_SIZE, "UTF-8");
				rootLogger.debug(String.format("응답받은 전문: %s", respond));
				String code = respond.substring(PacketInfo.MSG_HEADER_TSIZE);
				rootLogger.debug(String.format("전문길이: %s", respond.substring(5, 10)));
				rootLogger.debug(String.format("종류: %s", respond.substring(10, 12)));
				rootLogger.debug(String.format("응답코드: %s", code));
				if (code.equals("0000")) {
					rootLogger.info(String.format("ID : %s 인증 성공 !!!", (setting.getGwClientId()
							.substring(0, setting.getGwClientId().length() - 2) + "**")));
					sock.close();
					return true;
				} else {
					rootLogger.info(String.format("FROM SERVER: %s", code));
					rootLogger.info(String.format("ID : %s 인증 실패 !!!", (setting.getGwClientId()
							.substring(0, setting.getGwClientId().length() - 2) + "**")));
					rootLogger.info("ID/PASSWORD 재확인 필요");
					sock.close();
					return false;
				}
			}

			sock.close();
			return false;
		} catch (IOException e) {
			rootLogger.error("서버 연결 실패", e);
			return false;
		}
	}

	/**
	 * 서버 연결에 실패 하면 진행할 프로세스
	 *
	 * @param dataList APP_SEND_CONTENTS 데이터 리스트
	 */
	private void cannotConnectServer(List<AppSendData> dataList) {
		for (AppSendData data : dataList) {
			sendDataInvalidate(data, MessageStateCode.SERVER_CONNECT_FAIL_ERROR,
					MessageStateCode.RECV_STATE);
		}
	}

	private void failAuthentication(List<AppSendData> dataList) {
		for (AppSendData data : dataList) {
			sendDataInvalidate(data, MessageStateCode.AUTH_FAIL_ERROR, MessageStateCode.RECV_STATE);
		}
	}

	/**
	 * 연결 할 수 있는 서버의 URL 가져오기
	 *
	 * @param count 라운드 로빈용 카운트
	 * @return 연결 할 수 있는 서버 IP 와 Port
	 */
	private GateWayServerInfo getGWServerInfo(long count) {
		// R-R 으로 연결 분산
		int serverCount = setting.getGwPort().length;
		int ix = (int) (count % serverCount);

		return setting.getServerInfo(ix);
	}

	/**
	 * 오늘 날짜에서 일자만 얻기
	 *
	 * @return 오늘 날짜의 일
	 */
	private int getPresendDay() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 연락처 발송량 제한 초기화 하기
	 */
	private void clearPhoneCount() {
		phoneCount = new TreeMap<String, Integer>();
		oldDay = getPresendDay();
	}

	/**
	 * 에러코드 업데이트
	 *
	 * @param data 데이터
	 */
	private void sendDataInvalidate(AppSendData data, String cd) {
		sendDataInvalidate(data, cd,
				setting.isUseLms() ? MessageStateCode.LMS_SEND_STATE : MessageStateCode.RECV_STATE);
	}

	/**
	 * 에러코드 업데이트
	 *
	 * @param data 데이터
	 */
	private void sendDataInvalidate(AppSendData data, String cd, int state) {
		data.setCurState(state);
		data.setGaonErrorCode(cd);
		dao.updateErrorCode(data);
	}

	private Socket connect(String ip, int port, int timeout) {
		try {
			SocketAddress socketAddress = new InetSocketAddress(ip, port);
			Socket sock = new Socket();
			sock.connect(socketAddress, setting.getSocketConnectionTimeOut() * 1000);
			sock.setSoTimeout(timeout);
			return sock;
		} catch (UnknownHostException e) {
			rootLogger.error("IP, PORT 재확인 필요", e);
			System.exit(0);
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	/**
	 * <pre>
	 * 발송제한 시간을 밀리초로 바꾸기
	 * 
	 * [인덱스]
	 * 0 - 발송제한 시작 시간
	 * 1 - 발송제한 끝나는 시간
	 * </pre>
	 * 
	 * @return 발송제한 시간
	 */
	private long[] getNoSendStartEndTime() {
		Calendar sCalendar = Calendar.getInstance(Locale.KOREA);
		Calendar eCalendar = Calendar.getInstance(Locale.KOREA);

		Date dbTime = dbCommon.getDbTime();

		sCalendar.setTime(dbTime);
		eCalendar.setTime(dbTime);

		sCalendar.set(Calendar.HOUR_OF_DAY,
				Integer.parseInt(setting.getNoSendTimeStart().substring(0, 2)));
		sCalendar.set(Calendar.MINUTE,
				Integer.parseInt(setting.getNoSendTimeStart().substring(2, 4)));
		sCalendar.set(Calendar.SECOND,
				Integer.parseInt(setting.getNoSendTimeStart().substring(4, 6)));
		sCalendar.set(Calendar.MILLISECOND, 0);
		eCalendar.set(Calendar.HOUR_OF_DAY,
				Integer.parseInt(setting.getNoSendTimeEnd().substring(0, 2)));
		eCalendar.set(Calendar.MINUTE,
				Integer.parseInt(setting.getNoSendTimeEnd().substring(2, 4)));
		eCalendar.set(Calendar.SECOND,
				Integer.parseInt(setting.getNoSendTimeEnd().substring(4, 6)));
		eCalendar.set(Calendar.MILLISECOND, 0);
		long startTime = sCalendar.getTime().getTime();
		long endTime = eCalendar.getTime().getTime();

		// 끝나는 시간이 시작시간보다 이전 시간이면 하루 +
		if (endTime < startTime) {
			endTime += 86400000;
		}

		return new long[] { startTime, endTime };
	}

	@Override
	public void run() {
		init();
		rootLogger.info("Gaon Sender Thread Start");
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				rootLogger.info("가온 모듈 종료");
				SenderCommon.savePhoneCount(phoneCount, oldDay);
			}
		});

		// 서버에 전송용 일련번호 생성
		long sendPackUniquekey = 0;
		final AtomicBoolean authSuccess = new AtomicBoolean(false);

		// 인증 실패하는 동안 문자로 발송하는 스레드 생성
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				rootLogger.info("AUTH FAIL, CHANGE LMS SEND MODE");
				while (!authSuccess.get()) {
					List<AppSendData> dataList = dao.getAppSendDataList();
					if (dataList != null) {
						// cannotConnectServer(data);
						failAuthentication(dataList);
					}
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (Exception e) {
					}
				}
				rootLogger.info("AUTH SUCCESS. CHANGE APP MESSAGE SEND MODE");
			}
		});

		// ------------ 인증 시작
		long authCount = setting.isSendLmsWhenConnectionFail() ? Long.MAX_VALUE : setting.getAuthCount() + 1;
		long authRetryCnt = 0;
		for (; authRetryCnt < authCount; authRetryCnt++) {
			boolean auth = false;
			// 서버 인증 시도
			for (int i = 0; i < setting.getGwPort().length; i++) {
				auth |= checkValidationIdPwd(setting.getServerInfo(i));
				if (auth) {
					break;
				}
			}

			if (!auth) {
				// 서버 인증 실패시
				if (!t.isAlive() && setting.isSendLmsWhenConnectionFail()) {
					// 서버인증 실패로 재시도 사이에 들어오는 데이터
					// 문자로 전환하는 스레드가 아직 실행 이전이고 연결 실패시 문자 보내기 옵션이 true인 경우
					t.start();
				}
				rootLogger.info(String.format("%d번째 인증 실패", authRetryCnt + 1));
				try {
					TimeUnit.MINUTES.sleep(setting.getAuthTime());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				rootLogger.info("인증 성공");
				authSuccess.set(true);
				try {
					if (t.isAlive()) {
						t.join();
					}
					rootLogger.info("앱메시지 발송 시작");
					break;
				} catch (InterruptedException e) {
					rootLogger.error("에러", e);
				}
			}
		}

		if (authRetryCnt >= setting.getAuthCount()) {
			rootLogger.info(String.format("인증 시도 횟수 초과로 가온 모듈 종료"));
			System.exit(0);
		}

		// ------------ 인증 종료

		// 메모리 수거를 위해
		t = null;
		rootLogger.info("Gaon Sender Start");
		oldDay = SenderCommon.loadPhoneCount(phoneCount);

		String phoneRegexPattern = "01[016789]\\d{3,4}\\d{4}";
		while (true) {
			long currTime = dbCommon.getDbTime().getTime();
			long[] noSendTime = getNoSendStartEndTime();
			// 발송 제한 시간 체크
			if (noSendTime[1] != noSendTime[0] && noSendTime[0] <= currTime && currTime <= noSendTime[1]) {
				try {
					SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					sendLogger.info("발송 제한 시간: " + temp.format(new Date(noSendTime[0])) + " ~ " + temp.format(new Date(noSendTime[1])));
					Thread.sleep(noSendTime[1] - noSendTime[0]);
					continue;
				} catch (Exception e) {
				}
			}
			// 메모리 정리
			System.gc();

			// 날짜가 바뀌면 연락처 발송량 초기화
			if (oldDay != getPresendDay()) {
				clearPhoneCount();
			}

			List<AppSendData> dataList = null;

			// 데이터 불러오기
			try {
				dataList = dao.getAppSendDataList();
			} catch (Exception e) {
				rootLogger.error("DB에서 데이터를 불러오는데 실패하였습니다.", e);
				try {
					TimeUnit.MINUTES.sleep(1);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				continue;
			}

			if (dataList != null && dataList.size() > 0) {
				HashMap<String, LinkedList<AppSendData>> sendableDataMap = new HashMap<String, LinkedList<AppSendData>>();
				sendableDataMap.put("KAKAO", new LinkedList<AppSendData>());
				sendableDataMap.put("NAVER", new LinkedList<AppSendData>());
				try {
					// 데이터 유효성 체크하기
					Iterator<AppSendData> iter = dataList.iterator();
					while (iter.hasNext()) {
						AppSendData data = iter.next();
						if (!(data.getAppGubun().equals("KAKAO")
								|| data.getAppGubun().equals("NAVER"))) {
							// 앱구분에 KAKAO, NAVER 이외의 값이 들어왔는지 확인하기
							rootLogger.info(String.format(
									"MSG_SEQ(%d)의 APP_GUBUN에 KAKAO, NAVER이외의 값이 입력되었습니다.(대문자만 입력가능)",
									data.getMsgSeq()));
							sendDataInvalidate(data, MessageStateCode.APP_GUBUN_ERROR);
							iter.remove();
							continue;
						}

						if (!data.getPhoneNum().matches(phoneRegexPattern)) {
							rootLogger.info(String.format("MSG_SEQ(%d)의 수신자 번호 에러. (입력된 값: %s)",
									data.getMsgSeq(), data.getPhoneNum()));
							sendDataInvalidate(data, MessageStateCode.PHONE_NUMBER_ERROR);
							iter.remove();
							continue;
						}

						if (data.getGaonMsgType().equals("L")) {
							// DB에 CONTENTS가 있으면
							// attachment에 값이 유효한 값인지 확인하기
							ObjectMapper objectMapper = new ObjectMapper();
							AppMsgAttachment attachment = null;
							if (data.getAttachment() != null && !data.getAttachment().isEmpty()) {
								try {
									attachment = objectMapper.readValue(
											data.getAttachment().getBytes("UTF-8"),
											AppMsgAttachment.class);
									if (attachment.appMsgButton.size() > 0) {
										AppMsgButton btn = attachment.appMsgButton.get(0);
										if (!((btn.urlMobile.startsWith("http://")
												&& btn.urlPc.startsWith("http://"))
												|| (btn.urlMobile.startsWith("https://")
														&& btn.urlPc.startsWith("https://")))) {
											rootLogger.info(String.format(
													"MSG_SEQ(%d)의 버튼 URL이 http 혹은 https로 시작하지 않습니다.",
													data.getMsgSeq()));
											sendDataInvalidate(data,
													MessageStateCode.BUTTON_URL_ERROR);
											continue;
										}
									}
								} catch (Exception e) {
									rootLogger.info(String.format(
											"MSG_SEQ(%d) - PACK_UNIQUEKEY(%d) - ATTACHMENT(%s)이 형식에 맞지 않습니다.",
											data.getMsgSeq(), data.getPackUniquekey(),
											data.getAttachment()));
									// 템플릿 에러
									sendDataInvalidate(data, MessageStateCode.INVALID_JSON_FORMAT);
									iter.remove();
									continue;
								}
							}
							data.setMsgAttachment(attachment);
						} else {
							data.setMsgData(data.getSmsMsgData());
						}

						if (data.getMsgData() == null || data.getMsgData().trim().isEmpty()) {
							sendDataInvalidate(data, MessageStateCode.EMPTY_MSG_DATA_ERROR);
							iter.remove();
							continue;
						}

						// 템플릿 확인
						if (data.getHead() == null) {
							sendDataInvalidate(data, MessageStateCode.NOT_EXIST_TEMPLATE_ERROR);
							iter.remove();
							continue;
						}

						if (data.getUseButton() == null || data.getUseButton().equals("N")) {
							data.setMsgAttachment(null);
						}

						StringBuilder sb = new StringBuilder();
						sb.append(StringUtils.replaceNewLineChar(data.getHead())).append("\n");
						sb.append(StringUtils.replaceNewLineChar(data.getMsgData()));
						if (!StringUtils.isNullOrEmpty(data.getFoot())) {
							sb.append("\n").append(StringUtils.replaceNewLineChar(data.getFoot()));
						}

						data.setRealMsgData(sb.toString());

						if (data.getRealMsgData().length() > MSG_DATA_LENGTH_LIMIT) {
							sendDataInvalidate(data, MessageStateCode.OVERFLOW_LENGTH_LMS_ERROR,
									MessageStateCode.RECV_STATE);
							rootLogger.info(String.format("MSG_SEQ(%d)의 내용이 너무 깁니다. (%d/%d)",
									data.getMsgSeq(), data.getRealMsgData().length(),
									MSG_DATA_LENGTH_LIMIT));
							iter.remove();
							continue;
						}

						if (data.getGaonMsgType().equals("S")
								&& data.getSmsMsgData().getBytes("EUC-KR").length > 90) {
							rootLogger.info(String.format(
									"MSG_SEQ(%d)의 제한 바이트수를 초과하였습니다. (%dB/%dB)", data.getMsgSeq(),
									data.getSmsMsgData().getBytes("EUC-KR").length, 90));
							sendDataInvalidate(data, MessageStateCode.OVERFLOW_BYTE_LENGTH_ERROR);
							iter.remove();
							continue;
						}

						// 연락처 별 발송량 횟수 세기
						Integer p = phoneCount.get(data.getPhoneNum());
						if (p == null) {
							p = 1;
						} else {
							p++;
						}

						phoneCount.put(data.getPhoneNum(), p);

						if (setting.getDuplicatePhoneCount() > 0
								&& p > setting.getDuplicatePhoneCount()) {
							rootLogger
									.info(String.format("MSG_SEQ(%d) 연락처 발송량 제한을 초과하였습니다. (%d/%d)",
											data.getMsgSeq(), p, setting.getDuplicatePhoneCount()));
							sendDataInvalidate(data, MessageStateCode.EXCEED_PHONE_COUNT_ERROR);
							iter.remove();
							continue;
						}

						sendableDataMap.get(data.getAppGubun()).add(data);
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				GateWayServerInfo gwInfo = null;
				for (List<AppSendData> sendableList : sendableDataMap.values()) {
					while (sendableList.size() > 0) {
						Socket sock = null;
						int retryCnt = 0;
						while (true) {
							if (setting.getRetryCount() > 0 && retryCnt > setting.getRetryCount()) {
								if (setting.isSendLmsWhenRetryEnd()) {
									cannotConnectServer(sendableList);
									sendableList.clear();
									continue;
								} else {
									rootLogger.info("서버 접속 재시도 횟수 초과로 시스템 종료");
									System.exit(0);
								}
							}
							sendPackUniquekey++;
							try {
								for (int i = 0; i < setting.getGwPort().length; i++) {
									gwInfo = getGWServerInfo(sendPackUniquekey + i);
									rootLogger
											.debug(String.format("PORT(%d) 소켓 연결 시작", gwInfo.port));
									sock = connect(gwInfo.ip, gwInfo.port, 10000);
								}
								if (sock == null) {
									throw new Exception("연결 가능한 서버가 없습니다.");
								}
								break;
							} catch (Exception e) {
								rootLogger.error("서버 연결 실패!!!!", e);
								if (setting.isSendLmsWhenConnectionFail()) {
									try {
										cannotConnectServer(sendableList);
									} catch (Exception e1) {
										rootLogger.error("서버 접속을 실패하여 문자로 전환하던 중 에러가 발생하였습니다", e1);
									}
									sendableList.clear();
									break;
								} else {
									try {
										TimeUnit.MINUTES.sleep(setting.getRetryTime());
										retryCnt++;
										continue;
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}
							}
						}

						if (sock != null) {
							// 연결된 소켓이 존재하면
							rootLogger.debug(String.format("PORT(%d) 소켓 연결 성공", gwInfo.port));

							BufferedInputStream is = null;
							BufferedOutputStream bs = null;

							try {
								is = new BufferedInputStream(sock.getInputStream());
								bs = new BufferedOutputStream(sock.getOutputStream());
							} catch (IOException e) {
								e.printStackTrace();
							}

							sendLogger.debug(String.format("PORT(%d) SEND START", gwInfo.port));

							int index = 0;
							final int lastIndex = sendableList.size() - 1;
							final String moduleSendTime = sendTimeDateFormat.format(dbCommon.getDbTime());

							try {
								Iterator<AppSendData> di = sendableList.iterator();
								while (di.hasNext()) {
									AppSendData d = di.next();
									d.setModuleSendTime(moduleSendTime.substring(0, 14));
									if (StringUtils.isNullOrEmpty(d.getSerialNumber())) {
										d.setSerialNumber(PacketInfo.getSerialNumber(
												sendPackUniquekey, moduleSendTime, index + 1,
												setting.getGwClientId()));
									}

									String continu;
									if (lastIndex == 0) {
										continu = "F";
									} else {
										if (index == 0)
											continu = "S";
										else if (index == lastIndex)
											continu = "E";
										else
											continu = "C";
									}

									sendLogger.info("MSG_SEQ(" + d.getMsgSeq() + ") SERIAL_NUMBER("
											+ d.getSerialNumber() + ") PHONE_NUM(" + d.getPhoneNum()
											+ ") START");
									String resultCode = sendpacket(bs, is, buf, d, continu);

									if (setting.getGwPort()[0] != 50000 && !resultCode.equals("0000")) {
										throw new RuntimeException("Message send fail; from server: " + resultCode);
									}

									index++;
									while (true) {
										try {
											int rows = dao.updateAppSendDataCurStateSendComplate(d);

											if (setting.getGwPort()[0] == 50000) {
												sendDataInvalidate(d, MessageStateCode.TEST_BED);
											}

											if (rows > 0) {
												sendLogger.info("MSG_SEQ(" + d.getMsgSeq()
														+ ") SERIAL_NUMBER(" + d.getSerialNumber()
														+ ") PHONE_NUM(" + d.getPhoneNum()
														+ ") OK");
											} else {
												sendLogger.error("MSG_SEG(" + d.getMsgSeq() + ") SERIAL_NUMBER(" + d.getSerialNumber() + ") "
																+ "PHONE_NUM(" + d.getPhoneNum() + ") 중복된 MSG_SEQ로 업데이트 실패");
											}
											di.remove();
											break;
										} catch (Exception e) {
											try {
												rootLogger.error(
														"MSG_SEQ(" + d.getMsgSeq() + ") 발송 완료 후 상태 코드 업데이트 실패",
														e);
												TimeUnit.SECONDS.sleep(30);
											} catch (InterruptedException e1) {
												Thread.currentThread().interrupt();
											}
										}
									}
								}
							} catch (Exception e) {
								rootLogger.error("서버 전송 실패 !!", e);
								if (setting.isSendLmsWhenConnectionFail()) {
									try {
										cannotConnectServer(sendableList);
										sendableList.clear();
										TimeUnit.SECONDS.sleep(setting.getTranTerm());
									} catch (InterruptedException e1) {
										Thread.currentThread().interrupt();
									} catch (Exception e1) {
										rootLogger.error("서버 접속을 실패하여 문자로 전환하던 중 에러가 발생하였습니다", e1);
									}
								}
							}
							sendLogger.info(String.format("PORT(%d) SEND END", gwInfo.port));
							if (sock != null && !sock.isClosed()) {
								try {
									sock.close();
								} catch (IOException e) {
									rootLogger.error("소켓 닫기 실패!!", e);
								}
							}
						}
					}
				}
			} else {
				try {
					TimeUnit.SECONDS.sleep(setting.getTranTerm());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
