package com.nanal.db;

import com.nanal.dto.AppSendData;

public interface AppResultDataDao {
	/**
	 * 응답받은 데이터 업데이트
	 * 
	 * @param data
	 */
	int updateAppSendDataAfterRespond(AppSendData data);

	/**
	 * 타임아웃 데이터가 뒤늦게 결과가 도착한 경우 업데이트<br>
	 * 만약 로그테이블에 이관되었으면 그냥 무시
	 * 
	 * @param data
	 * @return
	 */
	int updateTimeoutDataReport(AppSendData data);
}
