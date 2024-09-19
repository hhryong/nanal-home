package com.nanal.thread.log;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.nanal.db.DBCommon;
import com.nanal.db.TableLogDao;
import com.nanal.setting.GaonSetting;
import com.nanal.utils.DBUtils;

import lombok.RequiredArgsConstructor;

@Scope("prototype")
@Component
@RequiredArgsConstructor
public class GaonDbLogger implements Runnable {
	public static final Object logLock = new Object();

	private final GaonSetting setting;
	private final DBCommon commonDao;
	private final TableLogDao logDao;

	@Qualifier("rootLogger")
	private final Logger rootLogger;

	@Override
	public void run() {
		try {
			rootLogger.debug("LOG TABLE로 이관 시작");
			moveDataInLogTable();
			rootLogger.debug("LOG TABLE로 이관 완료");
		} catch (Exception e) {
			rootLogger.error("에러 !!", e);
			try {
				TimeUnit.MINUTES.sleep(1);
			} catch (InterruptedException e1) {
				Thread.currentThread().interrupt();
			}
		}
	}

	@Transactional
	private void moveDataInLogTable() {
		String dataLogTableName = DBUtils.getLogTableName(setting);

		// 로그 테이블이 존재하는지 확인 후 생성
		if (setting.getDbName().equals("MYSQL")) {
			commonDao.createAppSendDataForLog(dataLogTableName);
		} else {
			if (!commonDao.checkExistTable(dataLogTableName, setting.getDbUser())) {
				commonDao.createAppSendDataForLog(dataLogTableName);
			}
		}

		while (true) {
			List<Long> resultList = logDao.getMovalbeDataList(setting.getAppSendDataTableName());

			if (resultList.isEmpty()) {
				break;
			}

			if (resultList.size() > 0) {
				rootLogger.info(String.format("COMPLETED DATA %s -> %s : %s",
						setting.getAppSendDataTableName(), dataLogTableName, resultList));
			}
			if (resultList.size() > 0) {
				logDao.logAppSendData(setting.getAppSendDataTableName(), dataLogTableName,
						resultList);
				logDao.deleteAppSendData(setting.getAppSendDataTableName(), resultList);
			}

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
