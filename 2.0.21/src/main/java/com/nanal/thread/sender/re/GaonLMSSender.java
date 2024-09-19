package com.nanal.thread.sender.re;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nanal.db.AppSendDataDao;
import com.nanal.db.LMSSenderDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Scope("prototype")
@Component
public class GaonLMSSender implements Runnable {

	private final AppSendDataDao dataDao;
	@Qualifier("rootLogger")
	private final Logger rootLogger;
	private final LMSSenderDao lmsDao;

	@Override
	public void run() {
		rootLogger.debug("Gaon LMSSender Thread Start");
		while (true) {
			try {
				List<HashMap<String, Object>> list = null;
				try {
					list = dataDao.getAppSendDataLMSSendableListWithCustomColumn();
				} catch (Exception e) {
					rootLogger.error("문자 발송 대상 데이터 불러오기 실패 !!", e);
					try {
						TimeUnit.MINUTES.sleep(1);
					} catch (InterruptedException e1) {
						Thread.currentThread().interrupt();
					}
					break;
				}

				if (list.isEmpty()) {
					break;
				}

				rootLogger.debug("LMSSender data load succes count: " + list.size());

				sendLms(list, 0);
			} catch (Exception e) {
				rootLogger.error("LMS SEND FAIL", e);
			}
		}
		rootLogger.debug("LMSSender end");
	}

	@Transactional
	private void sendLms(List<HashMap<String, Object>> list, int code) {
		lmsDao.sendMobileMessage(list, code);
		dataDao.updateAppSendDataAfterLms2(list);
	}
}
