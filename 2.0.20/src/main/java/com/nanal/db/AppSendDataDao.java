package com.nanal.db;

import com.nanal.dto.AppSendData;
import com.nanal.setting.TemplateCodeInfo;

import java.util.HashMap;
import java.util.List;

/**
 * 패키지 : com.nanal.db 작성자 : 이준희 설명 : APP_SEND_CONTENTS, APP_SEND_DATA 테이블을 다루는
 * dao
 */
public interface AppSendDataDao {
	/**
	 * <pre>
	 * packUniquekey에 해당하는 APP_SEND_CONTENTS 행의 CUR_STATE컬럼을 code 값으로 갱신
	 *
	 * [테이블 명 | CRUD]
	 * [APP_SEND_CONTENTS | UPDATE]
	 * </pre>
	 *
	 * @param packUniquekey PACK_UNIQUEKEY 값
	 * @param code          갱신할 CUR_STATE 값
	 */
	void updateAppSendContents(long packUniquekey, int code);

	/**
	 * <pre>
	 * APP_SEND_DATA의 MSG_SEQ에 해당하는 행의 CUR_STATE컬럼을 code 값으로 갱신
	 *
	 * [테이블 명 | CRUD]
	 * [APP_SEND_DATA | UPDATE]
	 * </pre>
	 *
	 * @param msgSeq MSG_SEQ 값
	 * @param code   갱신할 CUR_STATE 값
	 */
	void updateAppSendDataState(long msgSeq, int code);

	/**
	 * APP_SEND_DATA의 상태를 INVAILD(9)와 그에 따른 에러코드 설정
	 * 
	 * @param dto
	 */
	void updateAppSendDataInvail(AppSendData dto);

	/**
	 * <pre>
	 * APP_SEND_DATA의 CUR_STATE컬럼을 발송 완료 상태로 갱신한다.
	 *
	 * [테이블 명 | CRUD]
	 * [APP_SEND_DATA | UPDATE]
	 * </pre>
	 *
	 * @param msgSeq msg_seq
	 */
	int updateAppSendDataCurStateSendComplate(AppSendData msgSeq);

	/**
	 * <pre>
	 *     APP_SEND_DATA테이블에 PACK_UNIQUEKEY컬럼이 packUniquekey인 행 전부를
	 *     CUR_STATE 컬럼의 값을 code로 바꾼다.
	 *
	 *     [테이블 명 | CRUD]
	 *     [APP_SEND_DATA | UPDATE]
	 * </pre>
	 *
	 * @param packUniquekey 바꾸고 싶은 행들의 packUniquekey
	 * @param code          바꿀 curState의 값
	 */
	void updateAppSendDataStateAll(long packUniquekey, int code);

	void updateAppSendDataStateListSendComplete(List<AppSendData> dataList);

	/**
	 * <pre>
	 *     SMS/LMS 발송 후 얻은 NURI_MSG_DATA테이블의 MSG_SEQ컬럼의 값을
	 *     APP_SEND_DATA테이블의 NURI_MSG_SEQ 컬럼에 갱신함
	 * </pre>
	 *
	 * @param data sms/lms발송한 AppSendData
	 */
	void updateAppSendDataNuriMsgSeq(AppSendData data);

	/**
	 * <pre>
	 *     템플릿이 존재하는지 확인하기
	 * </pre>
	 *
	 * @param appGubun     플랫폼 명(KAKAO, NAVER)
	 * @param templateCode 템플릿 코드
	 * @return 템플릿 정보
	 */
	TemplateCodeInfo selectTemplateCode(String appGubun, String templateCode);

	/**
	 * <pre>
	 *      데이터 상태 업데이트
	 * </pre>
	 * 
	 * @param list 데이터 리스트
	 * @param code 바꾸고 싶은 상태 값
	 */
	void updateAppSendDataState(List<AppSendData> list, int code);

	/**
	 * <pre>
	 * 레포트를 받아야 하는 데이터 개수 가져오기
	 * </pre>
	 * 
	 * @param data 데이터
	 * @return 조건에 맞는 데이터 개수
	 */
	int countAppSendDataWhereSendComplete();

	/**
	 * <pre>
	 * 문자 발송을 위해
	 * 타임아웃 or 발송 샐패한 데이터 불러오기
	 * </pre>
	 * 
	 * @return
	 */
	List<AppSendData> getAppSendDataLMSSendableList();

	/**
	 * 템플릿정보 리스트 가져오기
	 * 
	 * @return 템플릿정보 리스트
	 */
	@Deprecated
	List<TemplateCodeInfo> getTemplateCodeInfoList();

	/**
	 * SEND_PACK_UKEY와 PHONE_NUM를 조건으로 데이터 가져오기
	 * 
	 * @param dto
	 * @return
	 */
	AppSendData getAppSendDataBySendUkeyAndPhoneNum(AppSendData dto);

	/**
	 * 서버 연결 실패로 문자 발송 후 상태 업데이트
	 * 
	 * CUR_STATE - 3 RSLT_CODE_APP - code
	 * 
	 * @param dto  target
	 * @param code L000 문자 발송 완료, L001 문자 발송 요청
	 */
	void updateAppSendDataServerConnectionFail(AppSendData dto, String code);

	/**
	 * 서버 연결 실패로 문자 발송 후 상태 업데이트
	 * 
	 * CUR_STATE - 3 RSLT_CODE_APP - code
	 * 
	 * @param list
	 * @param code L000 문자 발송 완료, L001 문자 발송 요청
	 */
	void updateAppSendDataServerConnectionFail(List<AppSendData> list, String code);

	/**
	 * 토스 발송 실패 후 카카오로 재발송 할 때 상태 업데이트
	 * 
	 * @param dto
	 */
	void updateAppSendDataResend(AppSendData dto);

	/**
	 * 토스 발송 실패 후 카카오로 재발송 했을 때 상태 업데이트
	 * 
	 * @param dto
	 */
	void updateAppSendDataResend(List<AppSendData> dto);

	/**
	 * Data 리스트 가져오기
	 * 
	 * @return
	 */
	List<AppSendData> getAppSendDataList();

	void updateAppSendDataAfterLms(AppSendData dto);

	void updateAppSendDataAfterLms(List<AppSendData> dto);

	/**
	 * 사용자 정의 컬럼 포함해서 데이터 가져오기
	 * 
	 * @return
	 */
	List<HashMap<String, Object>> getAppSendDataLMSSendableListWithCustomColumn();

	void updateAppSendDataAfterLms2(List<HashMap<String, Object>> list);

	/**
	 * 문자 발송 후 상태값 업데이트
	 * 
	 * @param dto
	 */
	void updateAppSendDataAfterLms2(HashMap<String, Object> dto);

	/**
	 * 타임아웃 데이터 에러 코드 설정
	 * 
	 * @return 적용된 데이터 개수
	 */
	int updateTimeoutDataError(List<String> serialNumberList);

	/**
	 * 에러코드 설정
	 * 
	 * @param dto 데이터
	 * @return 성공 1, 실패 0
	 */
	int updateErrorCode(AppSendData dto);

	/**
	 * 타임아웃 데이터 리스트 불러오기
	 * 
	 * @return
	 */
	List<String> selectTimeoutData();
}
