package com.nanal.db;

import com.nanal.dto.TableCommentDTO;

import java.util.Date;

public interface DBCommon
{
	/**
	 * APP_SEND_CONTENTS테이블 생성
	 *
	 * @param tableName APP_SEND_CONTENTS 테이블 명
	 */
	void createAppSendContents(String tableName);

	/**
	 * APP_SEND_DATA 테이블 생성
	 *
	 * @param tableName APP_SEND_DATA 테이블 명
	 */
	void createAppSendData(String tableName);

	/**
	 * APP_SEND_DATA_LOG 테이블 생성
	 *
	 * @param tableName APP_SEND_DATA 테이블 명
	 */
	void createAppSendDataForLog(String tableName);

	/**
	 * 사용자 정의 INDEX 생성
	 */
	void createCustomIndex();

	/**
	 * 테이블이 이미 존재하는지 확인
	 *
	 * @param tableName 존재하는지 확인 할 테이블 명
	 * @param owner 테이블 주인
	 * @return 테이블 존재여부
	 */
	boolean checkExistTable(String tableName, String owner);

	/**
	 * 템플릿 코드를 관리할 TEMPLATE_CODE 테이블 생성
	 *
	 * @param tableName TEMPLATE_CODE 테이블 명
	 */
	void createTemplateCodeTable(String tableName);

	/**
	 * APP_SEND_DATA의 PACK_UNIQUEKEY에 인덱스 생성
	 */
	void createPackUniquekeyIndex();

	/**
	 * 시리얼 넘버 인덱스 생성
	 */
	void createSerialNumberIndex();

	/**
	 * DB의 시간 불러오기
	 *
	 * @return DB의 현재 시간
	 */
	Date getDbTime();

	/**
	 * ORACLE, TIBERO, POSTGRESQL 컬럼 코멘트 추가
	 */
	void addColumnComment(TableCommentDTO comment);

	void createCustomColumn(String tableName);

	void createNewColumn(String tableName, String columnList);
}
