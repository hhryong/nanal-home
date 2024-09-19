package com.nanal.db;

import java.util.List;

public interface TableLogDao
{
	/**
	 * APP_SEND_DATA의 데이터를 LOG테이블로 이관
	 *
	 * @param tableNameSrc APP_SEND_DATA 테이블의 이름
	 * @param tableNameDest 이관할 목적지 테이블
	 */
	void logAppSendData(String tableNameSrc, String tableNameDest, List<Long> list);

	/**
	 * 로그 테이블로 이관 후 APP_SEND_DATA테이블에서 이관 된 데이터 삭제
	 *
	 * @param tableName APP_SEND_DATA 테이블 명
	 */
	void deleteAppSendData(String tableName, List<Long> list);

	/**
	 * LOG_TABLE로 이관해야하는 데이터 리스트
	 * 
	 * @param tableName APP_SEND_DATA 테이블 이름
	 * @return 로그 테이블로 이관할 수 있는 데이터의 MSG_SEQ 리스트
	 */
	List<Long> getMovalbeDataList(String tableName);
}
