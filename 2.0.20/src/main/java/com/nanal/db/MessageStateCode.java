package com.nanal.db;

public class MessageStateCode {
	// DATA STATE
	public static final int DATA_INPUTED = 0; // APP_SEND_DATA테이블에 들어온 상태
	public static final int DATA_SEND_COMPLETE = 1; // G/W서버에 발송완료
	public static final int DATA_RECV_COMPLETE = 2; // G/W서버에서 각 플랫폼으로 발송 요청 후 결과값을 응답 받음.
	public static final int DATA_SERVER_CONNECTFAIL = 3; // APP_SEND_CONTENTS의 MSG_DATA의 길이와
															// APP_SEND_DATA의 VARIABLES의
															// 길이의
															// 합이 제한 초과
	public static final int DATA_TIMEOUT = 4; // G/W서버에 발송을 하였으나 일정 시간안에 응답이 없음
	public static final int DATA_LMS_SEND = 5; // 앱메시지 발송 실패로 SMS/LMS 문자 발송
	public static final int DATA_RECV_SUCCESS_AFTER_LMS = 6; // 앱메시지 발송 실패로 SMS/LMS 문자 발송
	public static final int DATA_RECV_FAIL_AFTER_LMS = 7; // 앱메시지 발송 실패로 SMS/LMS 문자 발송
	public static final int DATA_FAIL_LMS_SEND = 8; // 앱메시지 발송 실패로 SMS/LMS 문자 발송
	public static final int DATA_INVALIDATION = 9; // 발송 불가 (잘못된 템플릿 코드, 잘못된 APP_GUBUN 이름)

	// 성공
	public static final String SUCCESS = "G000";
	// 앱구분 KAKAO, NAVER 이외의 값이 입력됨
	public static final String APP_GUBUN_ERROR = "G100";
	// 전화번호 에러
	public static final String PHONE_NUMBER_ERROR = "G101";
	// 템플릿 코드 존재 하지 않음
	public static final String NOT_EXIST_TEMPLATE_ERROR = "G200";
	// JSON 포맷 에러
	public static final String INVALID_JSON_FORMAT = "G201";
	// 버튼 URL 에러
	public static final String BUTTON_URL_ERROR = "G202";
	// 내용 빈값
	public static final String EMPTY_MSG_DATA_ERROR = "G300";
	// GAON_MSG_TYPE이 S인 경우 90바이트 초과
	public static final String OVERFLOW_BYTE_LENGTH_ERROR = "G301";
	// 핸드폰 번호 별 일일발송량 초과
	public static final String EXCEED_PHONE_COUNT_ERROR = "G400";
	// 서버 연결 실패
	public static final String SERVER_CONNECT_FAIL_ERROR = "G900";

	// ********** 문자 전환 필요한 에러코드 *****************
	// 인증 실패
	public static final String AUTH_FAIL_ERROR = "G901";
	// 타임 아웃
	public static final String TIMEOUT_ERROR = "G902";
	// 문자 제한 길이 초과
	public static final String OVERFLOW_LENGTH_LMS_ERROR = "G903";

	// 테스트 베드
	public static final String TEST_BED = "G777";

	public static final int APPLY_STATE = 0;
	public static final int SEND_STATE = 1;
	public static final int RECV_STATE = 2;
	public static final int LMS_SEND_STATE = 3;
}
