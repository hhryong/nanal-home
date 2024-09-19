package com.nanal;

import com.nanal.config.BaseConfig;
import com.nanal.db.initializer.Initializer;
import com.nanal.setting.GaonSetting;
import com.nanal.thread.log.GaonDbLogger;
import com.nanal.thread.log.GaonLogFileDeleter;
import com.nanal.thread.receiver.GaonReceiver;
import com.nanal.thread.sender.GaonSender;
import com.nanal.thread.sender.re.GaonLMSSender;

// import com.nanal.thread.timeout.TimeoutChecker;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		init();

		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

		// 로그를 찍을 때 출력 시간이 이상한 현상을 고치기 위해 사용
		System.setProperty("user.timezone", "Asia/Seoul");

		String settingFilePath = null;
		if (args.length == 0) {
			settingFilePath = "./setting.yaml";
		} else if (args.length == 1) {
			settingFilePath = args[0];
		} else {
			System.out.println("java -jar gaon.jar [setting_file_path]");
			System.exit(1);
		}
		GaonSetting.path = settingFilePath;
		FileLock fileLock;

		// 모듈 중복 실행 방지
		File check2 = new File("./env/check");
		try {
			if (check2.exists()) {
				fileLock = (new RandomAccessFile(check2, "rw")).getChannel().tryLock();
				if (fileLock == null) {
					System.err.println("가온 모듈이 이미 실행 중입니다.");
					System.exit(0);
				}
			} else {
				check2.createNewFile();
				fileLock = (new RandomAccessFile(check2, "rw")).getChannel().lock();
			}
		} catch (Exception e) {
			System.err.println("가온 모듈 실행 체크 실패. ./env/check 파일을 삭제 후 다시 시도해 주시길 바랍니다.");
			System.exit(0);
		}

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BaseConfig.class);
		BeanFactory beanFactory = ctx.getBeanFactory();
		Logger logger = beanFactory.getBean("rootLogger", Logger.class);
		logger.info("가온 모듈 시작");
		GaonSetting setting = beanFactory.getBean(GaonSetting.class);
		// 필요한 DB 테이블 생성
		Initializer initializer = beanFactory.getBean("initializer", Initializer.class);
		initializer.initialize();

		Integer[] ports = setting.getGwPort();
		int portCnt = ports.length;

		// DATA 이관 쓰레드 시작
		if (setting.getIsTransferLog()) {
			if (setting.getLogTerm() != null) {
				scheduledExecutorService.scheduleWithFixedDelay(
						beanFactory.getBean(GaonDbLogger.class), 0, setting.getLogTerm(),
						TimeUnit.SECONDS);
			} else {
				if (setting.isLogRealTime()) {
					scheduledExecutorService.scheduleWithFixedDelay(
							beanFactory.getBean(GaonDbLogger.class), 0, 5, TimeUnit.SECONDS);
				} else {
					scheduledExecutorService.scheduleWithFixedDelay(
							beanFactory.getBean(GaonDbLogger.class), 0, 5, TimeUnit.MINUTES);
				}
			}
		}

		// 문자 발송
		if (setting.isUseLms()) {
			Thread lmsSender = new Thread(beanFactory.getBean(GaonLMSSender.class));
			scheduledExecutorService.scheduleWithFixedDelay(lmsSender, 0, 5, TimeUnit.SECONDS);
		}

		// 임시 파일 삭제
		scheduledExecutorService.scheduleWithFixedDelay(
				beanFactory.getBean(GaonLogFileDeleter.class), 0, 1, TimeUnit.HOURS);

		Thread[] receivers = new Thread[portCnt];

		for (int i = 0; i < portCnt; i++) {
			GaonReceiver receiver = beanFactory.getBean(GaonReceiver.class);
			receiver.setPort(ports[i]);

			Thread t = new Thread(receiver);
			t.setDaemon(true);
			t.start();
			receivers[i] = t;
		}
		Thread sender = new Thread(beanFactory.getBean(GaonSender.class));
		try {
			sender.setDaemon(true);
			sender.start();

			Thread.sleep(5000);

			while (true) {
				logger.info("가온 모듈 기동 중");
				if (!sender.isAlive()) {
					logger.info("GaonSender 스레드가 정지 상태입니다. 재기동 합니다.");
					sender = new Thread(beanFactory.getBean(GaonSender.class));
					sender.setDaemon(true);
					sender.start();
					logger.info("GaonSender 스레드를 재기동 하였습니다.");
				}

				for (int i = 0; i < portCnt; i++) {
					if (!receivers[i].isAlive()) {
						logger.info("GaonReceiver" + (i + 1) + " 스레드가 정지 상태입니다. 재기동 합니다.");
						GaonReceiver receiver = beanFactory.getBean(GaonReceiver.class);
						receiver.setPort(ports[i]);

						Thread t = new Thread(receiver);
						t.setDaemon(true);
						t.start();
						receivers[i] = t;
						logger.info("GaonReceiver 스레드를 재기동 하였습니다.");
					}
				}
				Thread.sleep(600000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void init() {
		File check = new File("./env/");
		if (!check.exists()) {
			check.mkdirs();
		}

		File tempFolder = new File("./env/temp");
		if (!tempFolder.exists()) {
			tempFolder.mkdirs();
		}

		File recvFolder = new File("./env/recv");
		if (!recvFolder.exists()) {
			recvFolder.mkdirs();
		}
	}
}
