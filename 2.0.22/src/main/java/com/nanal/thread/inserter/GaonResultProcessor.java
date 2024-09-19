package com.nanal.thread.inserter;

// import java.io.File;
// import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.nanal.db.AppResultDataDao;
import com.nanal.db.MessageStateCode;
import com.nanal.dto.AppSendData;
import com.nanal.setting.GaonSetting;
import com.nanal.thread.timeout.TimeoutChecker;

public class GaonResultProcessor implements Runnable {
	private final long updateFailSleepTime;
	private final long maxRetryCount;
	private final long cycleLog;
	private final TimeoutChecker timeoutChecker;

	private Logger logger;
	private LinkedBlockingQueue<AppSendData> queue;
	private AppResultDataDao dao;
	private GaonSetting setting;
	private Set<String> lmsExcludeErrorSet;

	public GaonResultProcessor(LinkedBlockingQueue<AppSendData> queue, Logger logger, AppResultDataDao dao,
			GaonSetting setting, TimeoutChecker timeoutChecker) {
		this.updateFailSleepTime = 5L;
		this.maxRetryCount = TimeUnit.SECONDS.toMillis(5) / this.updateFailSleepTime;
		this.cycleLog = 50L;

		this.queue = queue;
		this.logger = logger;
		this.dao = dao;
		this.setting = setting;
		this.timeoutChecker = timeoutChecker;
		this.lmsExcludeErrorSet = new HashSet<String>(setting.getExcludeLmsError());
	}

	@Override
	public void run() {
		logger.info("Gaon ResultProcessor Thread Start");

		while (true) {
			AppSendData d = null;

			try {
				d = queue.poll(5, TimeUnit.SECONDS);
				logger.debug("Queue Loaded");
			} catch (InterruptedException e) {
				logger.error("InterruptedException in GaonResultProcessor2");
				logger.error("Fail get report data from BlockingLinkedQueue");
				Thread.currentThread().interrupt();
			}

			if (d == null) {
				logger.debug("Queue empty Timeout check");
				Thread t = new Thread(timeoutChecker);
				t.start();
				try {
					t.join();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				continue;
			}

			
			int affected = 0;
			if (isExcludeError(d.getRsltCodeApp())) {
				d.setCurState(setting.isUseLms() ? MessageStateCode.LMS_SEND_STATE : MessageStateCode.RECV_STATE);
			} else {
				d.setCurState(MessageStateCode.RECV_STATE);
			}
			logger.debug("레포트 업데이트 시도 SERIAL NUMBER : " + d.getSerialNumber());
			affected = dao.updateAppSendDataAfterRespond(d);
			if (affected < 1) {
				if (d.getUpdateRetryCount() < maxRetryCount) {
					if (d.getUpdateRetryCount() % cycleLog == 0) {
						logger.debug("레포트 업데이트 실패 SERIAL_NUMBER(" + d.getSerialNumber()
								+ ") - 이 시리얼 넘버에 해당하는 데이터가 존재하지 않습니다.");
						logger.debug("서버에 메시지 전송 후 아직 시리얼 넘버가 DB에 업데이트 되지 않은 가능성 존재");
					}

					d.increaseUpdateRetryCount();

					try {
						queue.put(d);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				} else {
					logger.info("레포트 업데이트 실패 SERIAL_NUMBER(" + d.getSerialNumber()
							+ ") - 재시도 횟수 초과");
				}
			} else {
				logger.debug("레포트 업데이트 성공 SERIAL_NUMBER(" + d.getSerialNumber() + ")");
			}
		}
	}

	private boolean isExcludeError(String rsltCodeApp) {
		return lmsExcludeErrorSet.contains(rsltCodeApp);
	}
}
