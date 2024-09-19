package com.nanal.thread.sender;

import com.nanal.dto.AppSendData;
import com.nanal.jsonobject.attachment.AppMsgAttachment;
import com.nanal.jsonobject.attachment.AppMsgButton;
import com.nanal.setting.TemplateCodeInfo;
import com.nanal.utils.StringUtils;
import org.apache.log4j.Logger;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.util.Arrays;

public class PacketInfo {
	public static final String serverVersion = "2.0.0:000021"; // 앞에 2개 숫자는 서버 버전, 그 이후는 클라이언트 버전
	public static final String displayVersion = "2.0.21"; // 앞에 2개 숫자는 서버 버전, 그 이후는 클라이언트 버전

	// ***************** MSG_HEADER ***********************
	public static final int MSG_HEADER = 5;
	public static final int MSG_LENGTH = 5;
	public static final int MSG_KIND = 1;
	public static final int MSG_TYPE = 1;

	public static final int MSG_HEADER_TSIZE = MSG_HEADER + MSG_LENGTH + MSG_KIND + MSG_TYPE;

	// ***************** MSG_RESULT ***********************
	public static final int MSG_RESULT = 4;

	public static final int MSG_RESULT_TSIZE = 4;

	public static final int MSG_RESPOND_SIZE = MSG_HEADER_TSIZE + MSG_RESULT_TSIZE;

	// ***************** INCOMMON *************************
	public static final int ORGANIZATION_SIZE = 4; // 기관고유키 (현재 사용X)
	public static final int DEPARTMENTAL_SIZE = 3; // 부서고유키 (현재 사용X)
	public static final int MANAGER_CODE_SIZE = 8;
	public static final int LMSNURIID_SIZE = 30; // ID
	public static final int LMSNURIPWD_SIZE = 255; // PWD
	public static final int LMSSUBID_SIZE = 30; // SUB_ID
	public static final int COMPANYCODE_SIZE = 3; // 1: KAKAO, 2: NAVER, 3: TOSS
	public static final int CONTINUSTAT_SIZE = 1; // 연속 발송 여부, (S: 연속발송 시작, C: 연속발송 중, E: 연속발송 끝, F:
													// 단일패킷)
	public static final int SERIAL_NUMBER_SIZE = 39; // 시리얼 넘버
	public static final int RECEIPT_NO_SIZE = 11; // 접수 번호
	public static final int REQORRESP_SIZE = 1; // 응답소켓 여부, 1: SENDER, 2: RECEIVER
	public static final int VERSIONINFO_SIZE = 16; // 버전 정보

	public static final int INCOMMON_HEADER_SIZE =
			ORGANIZATION_SIZE + DEPARTMENTAL_SIZE + MANAGER_CODE_SIZE + LMSNURIID_SIZE
					+ LMSNURIPWD_SIZE + LMSSUBID_SIZE + COMPANYCODE_SIZE + CONTINUSTAT_SIZE
					+ SERIAL_NUMBER_SIZE + RECEIPT_NO_SIZE + REQORRESP_SIZE + VERSIONINFO_SIZE;

	// ****************** INDATAINFO ********************
	public static final int CALLBACK_SIZE = 11; // 발신자 연락처
	public static final int MSGTITLE_SIZE = 50; // 메시지 제목
	public static final int MSGDATA_SIZE = 2980; // 메시지 내용

	// *************** INPHONINFO *************
	public static final int INFOCNT_SIZE = 10; // 연락처 개수
	public static final int INFOLIST_SIZE = 12; // 연락처 (01000000001!)

	public static final int INDATA_INFO_SIZE =
			CALLBACK_SIZE + MSGTITLE_SIZE + MSGDATA_SIZE + INFOCNT_SIZE + INFOLIST_SIZE;

	// ************* KAKAODEFLIST **************
	public static final int TITLE_SIZE = 100; // 카카오 타이틀
	public static final int HEADER_SIZE = 32; // 카카오 헤더
	public static final int TEMPLATE_CODE_SIZE = 30; // 템플릿 코드

	// - KAKAOBUTTON
	public static final int NAME_SIZE = 42; // 버튼(바로연결) 이름
	public static final int TYPE_SIZE = 2; // 버튼(바로연결) 타입
	public static final int SCHEME_ANDROID_SIZE = 125; // 버튼(바로연결) 안드로이드 앱 스키마
	public static final int SCHEME_IOS_SIZE = 125; // 버튼(바로연결) IOS 앱 스키마
	public static final int URL_MOBILE_SIZE = 300; // 버튼(바로연결) 모바일용 URL
	public static final int URL_PC_SIZE = 300; // 버튼(바로연결) PC용 URL
	public static final int CHAT_EXTRA_SIZE = 50; // 버튼(바로연결) 상담톡/봇 전환시 전달할 메타정보
	public static final int CHAT_EVENT_SIZE = 50; // 버튼(바로연결) 봇 전환시 연결할 봇 이벤트 명
	public static final int PLUGIN_ID_SIZE = 24; // 플러그인 ID
	public static final int RELAY_ID_SIZE = 125; // 플러그인 실행시 X-Kakao-Plugin-Id헤더를 통해 전달 받은 값
	public static final int ONECLICK_ID_SIZE = 125; // 원클릭 결제 플러그인에서 사용하는 결제 정보
	public static final int PRODUCT_ID_SIZE = 125; // 원클릭 결제 플러그인에서 사용하는 결제 정보

	public static final int KAKAOBUTTON_SIZE = NAME_SIZE + TYPE_SIZE + SCHEME_ANDROID_SIZE
			+ SCHEME_IOS_SIZE + URL_MOBILE_SIZE + URL_PC_SIZE + CHAT_EXTRA_SIZE + CHAT_EVENT_SIZE
			+ PLUGIN_ID_SIZE + RELAY_ID_SIZE + ONECLICK_ID_SIZE + PRODUCT_ID_SIZE;

	// - KAKAOITEM_HIGHLIGHT
	public static final int ITEM_HIGHLIGHT_TITLE_SIZE = 60; // 카카오 아이템 하이라이트 타이틀
	public static final int ITEM_HIGHLIGHT_DESCRIPTION_SIZE = 38; // 카카오 아이템 하이라이트 부가정보
	public static final int KAKAOHIGHLIGHT_SIZE =
			ITEM_HIGHLIGHT_TITLE_SIZE + ITEM_HIGHLIGHT_DESCRIPTION_SIZE;
	// - KAKAOITEM
	// - KAKAOITEM_LIST
	public static final int ITEM_TITLE_SIZE = 12; // 카카오 아이템 타이틀
	public static final int ITEM_DESCRIPTION_SIZE = 46; // 카카오 아이템 부가정보
	public static final int KAKAOITEM_LIST_SIZE = ITEM_TITLE_SIZE + ITEM_DESCRIPTION_SIZE;
	// - KAKAO_SUMMARY
	public static final int ITEM_SUMMARY_TITLE_SIZE = 12; // 카카오 아이템 요약정보 타이틀
	public static final int ITEM_SUMMARY_DESCRIPTION_SIZE = 28; // 카카오 아이템 요약정보 가격정보
	public static final int KAKAOITEM_SUMMARY_SIZE =
			ITEM_SUMMARY_TITLE_SIZE + ITEM_SUMMARY_DESCRIPTION_SIZE;

	public static final int KAKAOATTACHMENT_SIZE =
			KAKAOBUTTON_SIZE + KAKAOHIGHLIGHT_SIZE + KAKAOITEM_LIST_SIZE + KAKAOITEM_SUMMARY_SIZE;

	// KAKAODEFLIST - KAKAOSUPPLEMENT
	// - KAKAOQUICK_REPLY
	public static final int KAKAOQUICK_REPLAY_SIZE = NAME_SIZE + TYPE_SIZE + SCHEME_ANDROID_SIZE
			+ SCHEME_IOS_SIZE + URL_MOBILE_SIZE + URL_PC_SIZE + CHAT_EXTRA_SIZE + CHAT_EVENT_SIZE;

	public static final int KAKAOSUPPLEMENT_SIZE = KAKAOQUICK_REPLAY_SIZE;

	public static final int KAKAO_BODY_SIZE = TITLE_SIZE + HEADER_SIZE + TEMPLATE_CODE_SIZE
			+ KAKAOATTACHMENT_SIZE + KAKAOSUPPLEMENT_SIZE;

	// ********************* NAVER ************************************
	public static final int NAVER_TEMPLATECODE_SIZE = 64; // 템플릿 코드

	public static final int NAVER_BUTTONCODE_SIZE = 64; // 버튼코드(TEMPLATE_CODE와 같은 값으로 신청)
	public static final int NAVER_BUTTON_PCURL_SIZE = 256; // PC용 URL
	public static final int NAVER_BUTTON_MOBILEURL_SIZE = 256; // 모바일용 URL

	public static final int NAVER_ATTACHMENT_SIZE =
			NAVER_BUTTONCODE_SIZE + NAVER_BUTTON_PCURL_SIZE + NAVER_BUTTON_MOBILEURL_SIZE;
	public static final int NAVER_BODY_SIZE = NAVER_TEMPLATECODE_SIZE + NAVER_ATTACHMENT_SIZE;

	// ********************* TOSS ************************************
	// TOSS_BUTTON
	public static final int TOSS_NAME_SIZE = 36; // 버튼 이름
	public static final int TOSS_URL_SIZE = 200; // URL
	public static final int TOSS_BUTTON_SIZE = TOSS_NAME_SIZE + TOSS_URL_SIZE;

	public static final int TOSS_MESSAGES_SIZE = TOSS_BUTTON_SIZE;
	public static final int TOSS_BODY_SIZE = TOSS_MESSAGES_SIZE;

	/**
	 * <pre>
	 * 공백문자(0x20)으로 나머지 바이트 채우기
	 *
	 * ex) getPaddingByte([0x00, 0x01, 0x02, 0x03], 8)
	 *     -> [0x00, 0x01, 0x02, 0x03, 0x20, 0x20, 0x20, 0x20]
	 * </pre>
	 *
	 * @param data buffer
	 * @param len 총 길이
	 * @return buffer에 공백문자를 채운 byte배열
	 */
	public static byte[] getPaddingByte(byte[] data, int len) {
		byte[] t = Arrays.copyOf(data, len);
		for (int i = data.length; i < len; i++) {
			t[i] = 0x20;
		}

		return t;
	}

	public static void incommonHeaderLog(ByteBuffer byteBuffer, Logger logger) {
		try {
			byte[] buffer = new byte[256];
			byteBuffer.get(buffer, 0, ORGANIZATION_SIZE + DEPARTMENTAL_SIZE + MANAGER_CODE_SIZE);
			byteBuffer.get(buffer, 0, LMSNURIID_SIZE);
			logger.debug(String.format("[PACKET]NURI ID: %s",
					new String(buffer, 0, LMSNURIID_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, LMSNURIPWD_SIZE);
			byteBuffer.get(buffer, 0, LMSSUBID_SIZE);
			logger.debug(String.format("[PACKET]LMS SUB ID: %s",
					new String(buffer, 0, LMSSUBID_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, COMPANYCODE_SIZE);
			logger.debug(String.format("[PACKET]COMPANY CODE: %s",
					new String(buffer, 0, COMPANYCODE_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, CONTINUSTAT_SIZE);
			logger.debug(String.format("[PACKET]CONTINU STAT: %s",
					new String(buffer, 0, CONTINUSTAT_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, SERIAL_NUMBER_SIZE);
			logger.debug(String.format("[PACKET]SERIAL NUMBER: %s",
					new String(buffer, 0, SERIAL_NUMBER_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, RECEIPT_NO_SIZE);
			logger.debug(String.format("[PACKET]RECEIPT NO: %s",
					new String(buffer, 0, RECEIPT_NO_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, REQORRESP_SIZE);
			logger.debug(String.format("[PACkET]IS SENDDER: %s",
					new String(buffer, 0, REQORRESP_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, VERSIONINFO_SIZE);
			logger.debug(String.format("[PACKET]VERSION INFO: %s",
					new String(buffer, 0, VERSIONINFO_SIZE, "UTF-8")));
		} catch (Exception e) {
			logger.error("ERROR!", e);
		}
	}

	/**
	 * 공통 헤더 만들기 <br>
	 * <table>
	 * <tr>
	 * <th>이름</th>
	 * <th>크기</th>
	 * <th>전송 요청인때</th>
	 * <th>결과 요청인때</th>
	 * <th>인증 요청인때</th>
	 * <th>설명</th>
	 * </tr>
	 * <tr>
	 * <td>기관코드</td>
	 * <td>4</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>현재 8626 고정값 보내는 중</td>
	 * </tr>
	 * <tr>
	 * <td>부서코드</td>
	 * <td>3</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>현재 154 고정값 보내는 중</td>
	 * </tr>
	 * <tr>
	 * <td>담당자코드</td>
	 * <td>8</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>현재 admin01 고정값 보내는 중</td>
	 * </tr>
	 * <tr>
	 * <td>아이디</td>
	 * <td>30</td>
	 * <td>미사용</td>
	 * <td>사용</td>
	 * <td>사용</td>
	 * <td>MGOV 아이디</td>
	 * </tr>
	 * <tr>
	 * <td>패스워드</td>
	 * <td>255</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>사용</td>
	 * <td>암호화된 패스워드</td>
	 * </tr>
	 * <tr>
	 * <td>서브아이디</td>
	 * <td>30</td>
	 * <td>사용</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>빌링용 계정</td>
	 * </tr>
	 * <tr>
	 * <td>플랫폼</td>
	 * <td>3</td>
	 * <td>사용</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>1: 카카오, 2: 네이버, 3: 토스</td>
	 * </tr>
	 * <tr>
	 * <td>연속발송여부</td>
	 * <td>1</td>
	 * <td>사용</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>S: 연속 발송 시작, C: 연속 발송중, E: 연속 발송 종료, F: 1건 발송</td>
	 * </tr>
	 * <tr>
	 * <td>시리얼 번호</td>
	 * <td>39</td>
	 * <td>사용</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>오늘날짜(yyyyMMdd)[8]-1000건일련번호[8]1000건내에시퀀스[4]아이디[18:가변]</td>
	 * </tr>
	 * <tr>
	 * <td>전송요청 세션 여부</td>
	 * <td>1</td>
	 * <td>사용</td>
	 * <td>사용</td>
	 * <td>사용</td>
	 * <td>1: 전송요청 세션, 2: 전송요청 세션 아님</td>
	 * </tr>
	 * <tr>
	 * <td>버전 정보</td>
	 * <td>16</td>
	 * <td>사용</td>
	 * <td>사용</td>
	 * <td>사용</td>
	 * <td>서버버전(10.30).모듈버전(10:000001)</td>
	 * </tr>
	 * </table>
	 * 
	 * 
	 * @param bs 안쓰임
	 * @param packUniquekey 전송일련번호
	 * @param appGubun 앱 구분
	 * @param contiStat 연속 발송 여부 (S: 연속 발송 시작, C: 연속 발송 중, E: 연속 발송 종료, F: 1건 발송)
	 * @param packSeq 전송 시퀀스
	 * @param isSender 전송요청 여부
	 * @param nuriId 누리 아이디
	 * @param nuriPwd 누리 패스워드
	 * @param subId 서브 아이디
	 * @param serial 시리얼 넘버
	 * @return 서버에 보낼 데이터
	 * @throws BufferOverflowException 버퍼 오버플로우
	 * @throws ReadOnlyBufferException 버퍼가 읽기 전용
	 * @throws UnsupportedEncodingException 지원하지 않는 인코딩
	 */
	public static ByteBuffer createCommonPacket(BufferedOutputStream bs, long packUniquekey,
			final String appGubun, String contiStat, int msgSeq, boolean isSender, String nuriId,
			String nuriPwd, String subId, String serial)
			throws BufferOverflowException, ReadOnlyBufferException, UnsupportedEncodingException {
		String app = getAppGubunValue(appGubun);
		ByteBuffer byteBuffer = ByteBuffer.allocate(INCOMMON_HEADER_SIZE);
		byteBuffer.put(getPaddingByte("8626".getBytes("UTF-8"), ORGANIZATION_SIZE));
		byteBuffer.put(getPaddingByte("154".getBytes("UTF-8"), DEPARTMENTAL_SIZE));
		byteBuffer.put(getPaddingByte("admin01".getBytes("UTF-8"), MANAGER_CODE_SIZE));
		byteBuffer.put(getPaddingByte(nuriId.getBytes("UTF-8"), LMSNURIID_SIZE));
		byteBuffer.put(getPaddingByte(nuriPwd.getBytes("UTF-8"), LMSNURIPWD_SIZE));
		byteBuffer.put(getPaddingByte(
				subId == null ? "".getBytes("UTF-8") : subId.getBytes("UTF-8"), LMSSUBID_SIZE));
		byteBuffer.put(getPaddingByte(app.getBytes("UTF-8"), COMPANYCODE_SIZE));
		byteBuffer.put(getPaddingByte(contiStat.getBytes("UTF-8"), CONTINUSTAT_SIZE));
		byteBuffer.put(getPaddingByte(serial.getBytes("UTF-8"), SERIAL_NUMBER_SIZE));
		byteBuffer.put(getPaddingByte(String.format("%011d", packUniquekey).getBytes("UTF-8"),
				RECEIPT_NO_SIZE));
		if (isSender) {
			byteBuffer.put(getPaddingByte("1".getBytes("UTF-8"), REQORRESP_SIZE));
		} else {
			byteBuffer.put(getPaddingByte("2".getBytes("UTF-8"), REQORRESP_SIZE));
		}
		byteBuffer.put(getPaddingByte(serverVersion.getBytes("UTF-8"), VERSIONINFO_SIZE));
		byteBuffer.flip();

		return byteBuffer;
	}

	/**
	 * 공통 헤더 만들기 <br>
	 * <table>
	 * <tr>
	 * <th>이름</th>
	 * <th>크기</th>
	 * <th>전송 요청인때</th>
	 * <th>결과 요청인때</th>
	 * <th>인증 요청인때</th>
	 * <th>설명</th>
	 * </tr>
	 * <tr>
	 * <td>기관코드</td>
	 * <td>4</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>현재 8626 고정값 보내는 중</td>
	 * </tr>
	 * <tr>
	 * <td>부서코드</td>
	 * <td>3</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>현재 154 고정값 보내는 중</td>
	 * </tr>
	 * <tr>
	 * <td>담당자코드</td>
	 * <td>8</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>현재 admin01 고정값 보내는 중</td>
	 * </tr>
	 * <tr>
	 * <td>아이디</td>
	 * <td>30</td>
	 * <td>미사용</td>
	 * <td>사용</td>
	 * <td>사용</td>
	 * <td>MGOV 아이디</td>
	 * </tr>
	 * <tr>
	 * <td>패스워드</td>
	 * <td>255</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>사용</td>
	 * <td>암호화된 패스워드</td>
	 * </tr>
	 * <tr>
	 * <td>서브아이디</td>
	 * <td>30</td>
	 * <td>사용</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>빌링용 계정</td>
	 * </tr>
	 * <tr>
	 * <td>플랫폼</td>
	 * <td>3</td>
	 * <td>사용</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>1: 카카오, 2: 네이버, 3: 토스</td>
	 * </tr>
	 * <tr>
	 * <td>연속발송여부</td>
	 * <td>1</td>
	 * <td>사용</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>S: 연속 발송 시작, C: 연속 발송중, E: 연속 발송 종료, F: 1건 발송</td>
	 * </tr>
	 * <tr>
	 * <td>시리얼 번호</td>
	 * <td>39</td>
	 * <td>사용</td>
	 * <td>미사용</td>
	 * <td>미사용</td>
	 * <td>오늘날짜(yyyyMMdd)[8]-1000건일련번호[8]1000건내에시퀀스[4]아이디[18:가변]</td>
	 * </tr>
	 * <tr>
	 * <td>전송요청 세션 여부</td>
	 * <td>1</td>
	 * <td>사용</td>
	 * <td>사용</td>
	 * <td>사용</td>
	 * <td>1: 전송요청 세션, 2: 전송요청 세션 아님</td>
	 * </tr>
	 * <tr>
	 * <td>버전 정보</td>
	 * <td>16</td>
	 * <td>사용</td>
	 * <td>사용</td>
	 * <td>사용</td>
	 * <td>서버버전(10.30).모듈버전(10:000001)</td>
	 * </tr>
	 * </table>
	 * 
	 * 
	 * @param bs 안쓰임
	 * @param packUniquekey 전송일련번호
	 * @param appGubun 앱 구분
	 * @param contiStat 연속 발송 여부 (S: 연속 발송 시작, C: 연속 발송 중, E: 연속 발송 종료, F: 1건 발송)
	 * @param packSeq 전송 시퀀스
	 * @param isSender 전송요청 여부
	 * @param nuriId 누리 아이디
	 * @param nuriPwd 누리 패스워드
	 * @param subId 서브 아이디
	 * @param serial 시리얼 넘버
	 * @return 서버에 보낼 데이터
	 * @throws BufferOverflowException 버퍼 오버플로우
	 * @throws ReadOnlyBufferException 버퍼가 읽기 전용
	 * @throws UnsupportedEncodingException 지원하지 않는 인코딩
	 */
	public static void createCommonPacket2(ByteBuffer buf, long packUniquekey,
			final String appGubun, String contiStat, int msgSeq, boolean isSender, String nuriId,
			String nuriPwd, String subId, String serial)
			throws BufferOverflowException, ReadOnlyBufferException, UnsupportedEncodingException {
		String app = getAppGubunValue(appGubun);
		buf.put(getPaddingByte("8626".getBytes("UTF-8"), ORGANIZATION_SIZE));
		buf.put(getPaddingByte("154".getBytes("UTF-8"), DEPARTMENTAL_SIZE));
		buf.put(getPaddingByte("admin01".getBytes("UTF-8"), MANAGER_CODE_SIZE));
		buf.put(getPaddingByte(nuriId.getBytes("UTF-8"), LMSNURIID_SIZE));
		buf.put(getPaddingByte(nuriPwd.getBytes("UTF-8"), LMSNURIPWD_SIZE));
		buf.put(getPaddingByte(subId == null ? "".getBytes("UTF-8") : subId.getBytes("UTF-8"),
				LMSSUBID_SIZE));
		buf.put(getPaddingByte(app.getBytes("UTF-8"), COMPANYCODE_SIZE));
		buf.put(getPaddingByte(contiStat.getBytes("UTF-8"), CONTINUSTAT_SIZE));
		buf.put(getPaddingByte(serial.getBytes("UTF-8"), SERIAL_NUMBER_SIZE));
		buf.put(getPaddingByte(String.format("%011d", packUniquekey).getBytes("UTF-8"),
				RECEIPT_NO_SIZE));
		if (isSender) {
			buf.put(getPaddingByte("1".getBytes("UTF-8"), REQORRESP_SIZE));
		} else {
			buf.put(getPaddingByte("2".getBytes("UTF-8"), REQORRESP_SIZE));
		}
		buf.put(getPaddingByte(serverVersion.getBytes("UTF-8"), VERSIONINFO_SIZE));
	}

	public static void kakaoBodyLog(ByteBuffer byteBuffer, Logger logger) {
		try {
			logger.debug("******************* KAKAO INFO *******************");
			byte[] buffer = new byte[1024];
			byteBuffer.get(buffer, 0, TITLE_SIZE);
			logger.debug(String.format("[PACKET]KAKAO TITLE: %s",
					new String(buffer, 0, TITLE_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, HEADER_SIZE);
			logger.debug(String.format("[PACKET]KAKAO HEADER: %s",
					new String(buffer, 0, HEADER_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, TEMPLATE_CODE_SIZE);
			logger.debug(String.format("[PACKET]KAKAO TEMPLATE CODE: %s",
					new String(buffer, 0, TEMPLATE_CODE_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, NAME_SIZE);
			logger.debug(String.format("[PACKET]KAKAO BUTTON NAME: %s",
					new String(buffer, 0, NAME_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, TYPE_SIZE);
			logger.debug(String.format("[PACKET]KAKAO BUTTON TYPE: %s",
					new String(buffer, 0, TYPE_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, SCHEME_ANDROID_SIZE);
			byteBuffer.get(buffer, 0, SCHEME_IOS_SIZE);
			byteBuffer.get(buffer, 0, URL_MOBILE_SIZE);
			logger.debug(String.format("[PACKET]KAKAO BUTTON MOBILE URL: %s",
					new String(buffer, 0, URL_MOBILE_SIZE, "UTF-8")).trim());
			byteBuffer.get(buffer, 0, URL_PC_SIZE);
			logger.debug(String.format("[PACKET]KAKAO BUTTON PC URL: %s",
					new String(buffer, 0, URL_PC_SIZE, "UTF-8").trim()));
			byteBuffer.get(buffer, 0, CHAT_EXTRA_SIZE);
			byteBuffer.get(buffer, 0, CHAT_EVENT_SIZE);
			byteBuffer.get(buffer, 0, PLUGIN_ID_SIZE);
			byteBuffer.get(buffer, 0, RELAY_ID_SIZE);
			byteBuffer.get(buffer, 0, ONECLICK_ID_SIZE);
			byteBuffer.get(buffer, 0, PRODUCT_ID_SIZE);
			byteBuffer.get(buffer, 0, KAKAOHIGHLIGHT_SIZE);
			byteBuffer.get(buffer, 0, KAKAOITEM_LIST_SIZE);
			byteBuffer.get(buffer, 0, KAKAOITEM_SUMMARY_SIZE);
			byteBuffer.get(buffer, 0, KAKAOSUPPLEMENT_SIZE);
		} catch (Exception e) {
			logger.error("ERROR", e);
		}
	}

	/**
	 * 카카오 BODY 만들기
	 *
	 * @param bs write stream
	 * @param templateCodeInfo 템플릿 정보
	 * @param attachment 버튼 정보
	 * @param quickReplies 바로연결 정보
	 * @throws IOException I/O error
	 */
	public static ByteBuffer createKakaoBody(BufferedOutputStream bs,
			TemplateCodeInfo templateCodeInfo, AppMsgAttachment attachment) throws IOException {
		ByteBuffer byteBuffer = ByteBuffer.allocate(KAKAO_BODY_SIZE);
		byteBuffer.put(getPaddingByte(templateCodeInfo.getTitle() == null ? "".getBytes("UTF-8")
				: templateCodeInfo.getTitle().getBytes("UTF-8"), TITLE_SIZE));
		byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), HEADER_SIZE));
		byteBuffer.put(getPaddingByte(templateCodeInfo.getTemplateCode().getBytes("UTF-8"),
				TEMPLATE_CODE_SIZE));

		if (attachment != null) {
			if (attachment.appMsgButton == null) {
				byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), KAKAOBUTTON_SIZE));
			} else {
				AppMsgButton appMsgButton = attachment.appMsgButton.get(0);
				byteBuffer.put(getPaddingByte(appMsgButton.name == null ? "".getBytes("UTF-8")
						: appMsgButton.name.getBytes("UTF-8"), NAME_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.type == null ? "".getBytes("UTF-8")
						: appMsgButton.type.getBytes("UTF-8"), TYPE_SIZE));
				byteBuffer.put(getPaddingByte(
						appMsgButton.schemeAndroid == null ? "".getBytes("UTF-8")
								: appMsgButton.schemeAndroid.getBytes("UTF-8"),
						SCHEME_ANDROID_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.schemeIos == null ? "".getBytes("UTF-8")
						: appMsgButton.schemeIos.getBytes("UTF-8"), SCHEME_IOS_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.urlMobile == null ? "".getBytes("UTF-8")
						: appMsgButton.urlMobile.getBytes("UTF-8"), URL_MOBILE_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.urlPc == null ? "".getBytes("UTF-8")
						: appMsgButton.urlPc.getBytes("UTF-8"), URL_PC_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.chatExtra == null ? "".getBytes("UTF-8")
						: appMsgButton.chatExtra.getBytes("UTF-8"), CHAT_EXTRA_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.chatEvent == null ? "".getBytes("UTF-8")
						: appMsgButton.chatEvent.getBytes("UTF-8"), CHAT_EVENT_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.pluginId == null ? "".getBytes("UTF-8")
						: appMsgButton.pluginId.getBytes("UTF-8"), PLUGIN_ID_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.relayId == null ? "".getBytes("UTF-8")
						: appMsgButton.relayId.getBytes("UTF-8"), RELAY_ID_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.oneclickId == null ? "".getBytes("UTF-8")
						: appMsgButton.oneclickId.getBytes("UTF-8"), ONECLICK_ID_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.productId == null ? "".getBytes("UTF-8")
						: appMsgButton.productId.getBytes("UTF-8"), PRODUCT_ID_SIZE));
			}

			byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), KAKAOHIGHLIGHT_SIZE));
			byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), KAKAOITEM_LIST_SIZE));
			byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), KAKAOITEM_SUMMARY_SIZE));
		} else {
			byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), KAKAOATTACHMENT_SIZE));
		}

		byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), KAKAOSUPPLEMENT_SIZE));

		byteBuffer.flip();

		return byteBuffer;
	}

	/**
	 * 카카오 BODY 만들기
	 *
	 * @param bs write stream
	 * @param appSendData 템플릿 정보
	 * @param attachment 버튼 정보
	 * @param quickReplies 바로연결 정보
	 * @throws IOException I/O error
	 */
	public static void createKakaoBody2(ByteBuffer byteBuffer, AppSendData appSendData,
			AppMsgAttachment attachment) throws IOException {
		byteBuffer.put(getPaddingByte(appSendData.getTitle() == null ? "".getBytes("UTF-8")
				: appSendData.getTitle().getBytes("UTF-8"), TITLE_SIZE));
		byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), HEADER_SIZE));
		byteBuffer.put(getPaddingByte(appSendData.getTemplateCode().getBytes("UTF-8"),
				TEMPLATE_CODE_SIZE));

		if (attachment != null) {
			if (attachment.appMsgButton == null) {
				byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), KAKAOBUTTON_SIZE));
			} else {
				AppMsgButton appMsgButton = attachment.appMsgButton.get(0);
				byteBuffer.put(getPaddingByte(appMsgButton.name == null ? "".getBytes("UTF-8")
						: appMsgButton.name.getBytes("UTF-8"), NAME_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.type == null ? "".getBytes("UTF-8")
						: appMsgButton.type.getBytes("UTF-8"), TYPE_SIZE));
				byteBuffer.put(getPaddingByte(
						appMsgButton.schemeAndroid == null ? "".getBytes("UTF-8")
								: appMsgButton.schemeAndroid.getBytes("UTF-8"),
						SCHEME_ANDROID_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.schemeIos == null ? "".getBytes("UTF-8")
						: appMsgButton.schemeIos.getBytes("UTF-8"), SCHEME_IOS_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.urlMobile == null ? "".getBytes("UTF-8")
						: appMsgButton.urlMobile.getBytes("UTF-8"), URL_MOBILE_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.urlPc == null ? "".getBytes("UTF-8")
						: appMsgButton.urlPc.getBytes("UTF-8"), URL_PC_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.chatExtra == null ? "".getBytes("UTF-8")
						: appMsgButton.chatExtra.getBytes("UTF-8"), CHAT_EXTRA_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.chatEvent == null ? "".getBytes("UTF-8")
						: appMsgButton.chatEvent.getBytes("UTF-8"), CHAT_EVENT_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.pluginId == null ? "".getBytes("UTF-8")
						: appMsgButton.pluginId.getBytes("UTF-8"), PLUGIN_ID_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.relayId == null ? "".getBytes("UTF-8")
						: appMsgButton.relayId.getBytes("UTF-8"), RELAY_ID_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.oneclickId == null ? "".getBytes("UTF-8")
						: appMsgButton.oneclickId.getBytes("UTF-8"), ONECLICK_ID_SIZE));
				byteBuffer.put(getPaddingByte(appMsgButton.productId == null ? "".getBytes("UTF-8")
						: appMsgButton.productId.getBytes("UTF-8"), PRODUCT_ID_SIZE));
			}

			byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), KAKAOHIGHLIGHT_SIZE));
			byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), KAKAOITEM_LIST_SIZE));
			byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), KAKAOITEM_SUMMARY_SIZE));
		} else {
			byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), KAKAOATTACHMENT_SIZE));
		}

		byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), KAKAOSUPPLEMENT_SIZE));
	}

	public static void naverBodyLog(ByteBuffer byteBuffer, Logger logger) {
		logger.debug("******************** NAVER ********************");
		byte[] buffer = new byte[300];
		try {
			byteBuffer.get(buffer, 0, NAVER_TEMPLATECODE_SIZE);
			logger.debug(String.format("[PACKET]NAVER TEMPLATE CODE: %s",
					new String(buffer, 0, NAVER_TEMPLATECODE_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, NAVER_BUTTONCODE_SIZE);
			logger.debug(String.format("[PACKET]NAVER BUTTON CODE: %s",
					new String(buffer, 0, NAVER_TEMPLATECODE_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, NAVER_BUTTON_PCURL_SIZE);
			logger.debug(String.format("[PACKET]NAVER BUTTON PC URL: %s",
					new String(buffer, 0, NAVER_TEMPLATECODE_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, NAVER_BUTTON_MOBILEURL_SIZE);
			logger.debug(String.format("[PACKET]NAVER BUTTON MOBILE URL: %s",
					new String(buffer, 0, NAVER_TEMPLATECODE_SIZE, "UTF-8")));
		} catch (Exception e) {
			logger.error("ERROR", e);
		}
	}

	/**
	 * NAVER BODY 만들기
	 *
	 * @param bs write stream
	 * @param templateCodeInfo 템플릿 코드 정보
	 * @param attachment 버튼 정보
	 * @throws IOException I/O error
	 */
	public static ByteBuffer createNaverBody(BufferedOutputStream bs,
			TemplateCodeInfo templateCodeInfo, AppMsgAttachment attachment) throws IOException {
		ByteBuffer byteBuffer = ByteBuffer.allocate(NAVER_BODY_SIZE);

		byteBuffer.put(getPaddingByte(templateCodeInfo.getTemplateCode().getBytes("UTF-8"),
				NAVER_TEMPLATECODE_SIZE));
		if (attachment != null) {
			AppMsgButton naverBtn = attachment.appMsgButton.get(0);
			byteBuffer.put(getPaddingByte(templateCodeInfo.getTemplateCode().getBytes("UTF-8"),
					NAVER_BUTTONCODE_SIZE));
			byteBuffer.put(getPaddingByte(naverBtn.urlPc == null ? "".getBytes("UTF-8")
					: naverBtn.urlPc.getBytes("UTF-8"), NAVER_BUTTON_PCURL_SIZE));
			byteBuffer.put(getPaddingByte(naverBtn.urlMobile == null ? "".getBytes("UTF-8")
					: naverBtn.urlMobile.getBytes("UTF-8"), NAVER_BUTTON_MOBILEURL_SIZE));
		} else {
			byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), NAVER_ATTACHMENT_SIZE));
		}

		byteBuffer.flip();

		return byteBuffer;
	}

	/**
	 * NAVER BODY 만들기
	 *
	 * @param bs write stream
	 * @param appSendData 템플릿 코드 정보
	 * @param attachment 버튼 정보
	 * @throws IOException I/O error
	 */
	public static void createNaverBody2(ByteBuffer byteBuffer, AppSendData appSendData,
			AppMsgAttachment attachment) throws IOException {
		byteBuffer.put(getPaddingByte(appSendData.getTemplateCode().getBytes("UTF-8"),
				NAVER_TEMPLATECODE_SIZE));
		if (attachment != null) {
			AppMsgButton naverBtn = attachment.appMsgButton.get(0);
			byteBuffer.put(getPaddingByte(appSendData.getTemplateCode().getBytes("UTF-8"),
					NAVER_BUTTONCODE_SIZE));
			byteBuffer.put(getPaddingByte(naverBtn.urlPc == null ? "".getBytes("UTF-8")
					: naverBtn.urlPc.getBytes("UTF-8"), NAVER_BUTTON_PCURL_SIZE));
			byteBuffer.put(getPaddingByte(naverBtn.urlMobile == null ? "".getBytes("UTF-8")
					: naverBtn.urlMobile.getBytes("UTF-8"), NAVER_BUTTON_MOBILEURL_SIZE));
		} else {
			byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), NAVER_ATTACHMENT_SIZE));
		}
	}

	public static void tossBodyLog(ByteBuffer byteBuffer, Logger logger) {
		logger.debug("******************** TOSS ********************");
		byte[] buffer = new byte[300];
		try {
			byteBuffer.get(buffer, 0, TOSS_NAME_SIZE);
			logger.debug(String.format("[PACKET]TOSS BUTTON NAME: %s",
					new String(buffer, 0, TOSS_NAME_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, TOSS_URL_SIZE);
			logger.debug(String.format("[PACKET]TOSS BUTTON URL: %s",
					new String(buffer, 0, TOSS_URL_SIZE, "UTF-8")));
		} catch (Exception e) {
			logger.error("ERROR", e);
		}
	}

	/**
	 * TOSS BODY 만들기
	 *
	 * @param bs write stream
	 * @param attachment 버튼 정보
	 * @throws IOException I/O error
	 */
	public static ByteBuffer createTossBody(BufferedOutputStream bs, AppMsgAttachment attachment)
			throws IOException {
		ByteBuffer byteBuffer = ByteBuffer.allocate(TOSS_BODY_SIZE);

		if (attachment != null) {
			AppMsgButton tossBtn = attachment.appMsgButton.get(0);
			byteBuffer.put(getPaddingByte(
					tossBtn.name == null ? "".getBytes("UTF-8") : tossBtn.name.getBytes("UTF-8"),
					TOSS_NAME_SIZE));
			byteBuffer.put(getPaddingByte(tossBtn.urlMobile == null ? "".getBytes("UTF-8")
					: tossBtn.urlMobile.getBytes("UTF-8"), TOSS_URL_SIZE));
		} else {
			byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), TOSS_MESSAGES_SIZE));
		}

		byteBuffer.flip();

		return byteBuffer;
	}

	/**
	 * TOSS BODY 만들기
	 *
	 * @param byteBuffer 버퍼
	 * @param attachment 버튼 정보
	 * @throws IOException I/O error
	 */
	public static void createTossBody2(ByteBuffer byteBuffer, AppMsgAttachment attachment)
			throws IOException {
		if (attachment != null) {
			AppMsgButton tossBtn = attachment.appMsgButton.get(0);
			byteBuffer.put(getPaddingByte(
					tossBtn.name == null ? "".getBytes("UTF-8") : tossBtn.name.getBytes("UTF-8"),
					TOSS_NAME_SIZE));
			byteBuffer.put(getPaddingByte(tossBtn.urlMobile == null ? "".getBytes("UTF-8")
					: tossBtn.urlMobile.getBytes("UTF-8"), TOSS_URL_SIZE));
		} else {
			byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), TOSS_MESSAGES_SIZE));
		}
	}

	public static void indataInfoLog(ByteBuffer byteBuffer, Logger logger) {
		logger.debug("******************** INDATA ********************");
		byte[] buffer = new byte[4096];
		try {
			byteBuffer.get(buffer, 0, CALLBACK_SIZE);
			logger.debug(String.format("[PACKET]CALL BACK: %s",
					new String(buffer, 0, CALLBACK_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, MSGTITLE_SIZE);
			logger.debug(String.format("[PACKET]MSG TITLE: %s",
					new String(buffer, 0, MSGTITLE_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, MSGDATA_SIZE);
			logger.debug(String.format("[PACKET]MSG DATA: %s",
					new String(buffer, 0, MSGDATA_SIZE, "UTF-8").trim()));
			byteBuffer.get(buffer, 0, INFOCNT_SIZE);
			logger.debug(String.format("[PACKET]INFO COUNT: %s",
					new String(buffer, 0, INFOCNT_SIZE, "UTF-8")));
			byteBuffer.get(buffer, 0, INFOLIST_SIZE);
			logger.debug(String.format("[PACKET]INFO LIST(%d): %s", INFOLIST_SIZE,
					new String(buffer, 0, INFOLIST_SIZE, "UTF-8").trim()));
		} catch (Exception e) {
			logger.error("ERROR", e);
		}
	}

	/**
	 * <pre>
	 * 발신 번호, 메시지 데이터, 수신 번호 정보 데이터 만들기
	 * </pre>
	 * 
	 * @param bs
	 * @param data
	 * @param templateCodeInfo
	 * @param infoCount
	 * @param info
	 * @return
	 * @throws IOException
	 */
	public static ByteBuffer createIndataInfo(BufferedOutputStream bs, AppSendData data,
			TemplateCodeInfo templateCodeInfo, int infoCount, String info) throws IOException {
		ByteBuffer byteBuffer = ByteBuffer.allocate(INDATA_INFO_SIZE);

		byteBuffer.put(getPaddingByte(data.getCallBack().getBytes("UTF-8"), CALLBACK_SIZE));
		byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), MSGTITLE_SIZE));

		StringBuilder sb = new StringBuilder();
		if (data.getAppGubun().equals("KAKAO") || data.getAppGubun().equals("NAVER")) {
			sb.append(StringUtils.replaceNewLineChar(templateCodeInfo.getHeader())).append("\n");
		}
		String s = StringUtils.replaceNewLineChar(data.getAppSendContents().getMsgData()).trim();
		sb.append(s).append("\n");
		if (templateCodeInfo.getFoot() != null && !templateCodeInfo.getFoot().isEmpty()) {
			sb.append(StringUtils.replaceNewLineChar(templateCodeInfo.getFoot()));
		}

		byteBuffer.put(getPaddingByte(sb.toString().trim().getBytes("UTF-8"), MSGDATA_SIZE));
		byteBuffer.put(getPaddingByte(String.valueOf(infoCount).getBytes("UTF-8"), INFOCNT_SIZE));
		byteBuffer.put(getPaddingByte(info.getBytes("UTF-8"), INFOLIST_SIZE));

		byteBuffer.flip();

		return byteBuffer;
	}

	/**
	 * <pre>
	 * 발신 번호, 메시지 데이터, 수신 번호 정보 데이터 만들기
	 * </pre>
	 * 
	 * @param bs
	 * @param data
	 * @param templateCodeInfo
	 * @param infoCount
	 * @param info
	 * @return
	 * @throws IOException
	 */
	public static void createIndataInfo2(ByteBuffer byteBuffer, AppSendData data,
			TemplateCodeInfo templateCodeInfo, int infoCount, String info) throws IOException {

		byteBuffer.put(getPaddingByte(data.getCallBack().getBytes("UTF-8"), CALLBACK_SIZE));
		byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), MSGTITLE_SIZE));

		StringBuilder sb = new StringBuilder();
		if (data.getAppGubun().equals("KAKAO") || data.getAppGubun().equals("NAVER")) {
			sb.append(StringUtils.replaceNewLineChar(templateCodeInfo.getHeader())).append("\n");
		}
		String s = StringUtils.replaceNewLineChar(data.getAppSendContents().getMsgData()).trim();
		sb.append(s).append("\n");
		if (templateCodeInfo.getFoot() != null && !templateCodeInfo.getFoot().isEmpty()) {
			sb.append(StringUtils.replaceNewLineChar(templateCodeInfo.getFoot()));
		}

		byteBuffer.put(getPaddingByte(sb.toString().trim().getBytes("UTF-8"), MSGDATA_SIZE));
		byteBuffer.put(getPaddingByte(String.valueOf(infoCount).getBytes("UTF-8"), INFOCNT_SIZE));
		byteBuffer.put(getPaddingByte(info.getBytes("UTF-8"), INFOLIST_SIZE));
	}

	public static void createIndataInfo3(ByteBuffer byteBuffer, AppSendData data)
			throws IOException {
		byteBuffer.put(getPaddingByte(data.getCallBack().getBytes("UTF-8"), CALLBACK_SIZE));
		byteBuffer.put(getPaddingByte("".getBytes("UTF-8"), MSGTITLE_SIZE));
		byteBuffer
				.put(getPaddingByte(data.getRealMsgData().trim().getBytes("UTF-8"), MSGDATA_SIZE));
		byteBuffer.put(getPaddingByte("1".getBytes("UTF-8"), INFOCNT_SIZE));
		byteBuffer.put(getPaddingByte(String.format("%s!", data.getPhoneNum()).getBytes("UTF-8"),
				INFOLIST_SIZE));
	}

	/**
	 * <pre>
	 * 서버에 전송할때 가장 먼저 보내야 하는 HEADER 데이터
	 * 
	 * 메시지 헤더[5] : NANAL로 고정
	 * 전문 길이[5] : 실제로 보내는 데이터의 전문 길이 + 2(전문 종류[1] + 전문타입[1])
	 * 전문 종류[1] : 1 - 발송요청, 2 - 결과조회, 3 - 인증요청
	 * 전문 타입[1] : 1 - 요청, 2 - 응답, 3 - 레포트 데이터, 4 - 레포트 데이터 응답
	 * </pre>
	 * 
	 * <table border="1">
	 * <tr>
	 * <th>전문 종류</th>
	 * <th>전문 타입</th>
	 * <th>의미</th>
	 * </tr>
	 * <tr>
	 * <td>1</td>
	 * <td>1</td>
	 * <td>클라이언트에서 서버로 앱메시지 전송 요청</td>
	 * </tr>
	 * <tr>
	 * <td>1</td>
	 * <td>2</td>
	 * <td>앱메시지 전송 요청에 대한 응답</td>
	 * </tr>
	 * <tr>
	 * <td>2</td>
	 * <td>1</td>
	 * <td>클라이언트에서 서버로 앱메시지 결과 요청</td>
	 * </tr>
	 * <tr>
	 * <td>2</td>
	 * <td>2</td>
	 * <td>서버에서 레포트를 전부 보냈으면 0000, 이외에는 에러</td>
	 * </tr>
	 * <tr>
	 * <td>2</td>
	 * <td>3</td>
	 * <td>서버에서 레포트데이터를 보냄</td>
	 * </tr>
	 * <tr>
	 * <td>2</td>
	 * <td>4</td>
	 * <td>레포트 데이터를 받은 거에 대한 결과 응답</td>
	 * </tr>
	 * <tr>
	 * <td>3</td>
	 * <td>1</td>
	 * <td>모듈 실행 직 후 서버로 아이디,비밀번호 인증</td>
	 * </tr>
	 * <tr>
	 * <td>3</td>
	 * <td>2</td>
	 * <td>아이디, 비밀번호 인증 결과</td>
	 * </tr>
	 * </table>
	 * 
	 * @param size
	 * @param kind
	 * @param type
	 * @return
	 */
	public static ByteBuffer createMsgHeader(int size, String kind, String type) {
		ByteBuffer buffer = ByteBuffer.allocate(MSG_HEADER_TSIZE);
		try {
			buffer.put("NANAL".getBytes("UTF-8"));
			buffer.put(String.format("%05d", size + 2).getBytes("UTF-8"));
			buffer.put(kind.getBytes("UTF-8"));
			buffer.put(type.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		buffer.flip();

		return buffer;
	}

	/**
	 * <pre>
	 * 서버에 전송할때 가장 먼저 보내야 하는 HEADER 데이터
	 * 
	 * 메시지 헤더[5] : NANAL로 고정
	 * 전문 길이[5] : 실제로 보내는 데이터의 전문 길이 + 2(전문 종류[1] + 전문타입[1])
	 * 전문 종류[1] : 1 - 발송요청, 2 - 결과조회, 3 - 인증요청
	 * 전문 타입[1] : 1 - 요청, 2 - 응답, 3 - 레포트 데이터, 4 - 레포트 데이터 응답
	 * </pre>
	 * 
	 * <table border="1">
	 * <tr>
	 * <th>전문 종류</th>
	 * <th>전문 타입</th>
	 * <th>의미</th>
	 * </tr>
	 * <tr>
	 * <td>1</td>
	 * <td>1</td>
	 * <td>클라이언트에서 서버로 앱메시지 전송 요청</td>
	 * </tr>
	 * <tr>
	 * <td>1</td>
	 * <td>2</td>
	 * <td>앱메시지 전송 요청에 대한 응답</td>
	 * </tr>
	 * <tr>
	 * <td>2</td>
	 * <td>1</td>
	 * <td>클라이언트에서 서버로 앱메시지 결과 요청</td>
	 * </tr>
	 * <tr>
	 * <td>2</td>
	 * <td>2</td>
	 * <td>서버에서 레포트를 전부 보냈으면 0000, 이외에는 에러</td>
	 * </tr>
	 * <tr>
	 * <td>2</td>
	 * <td>3</td>
	 * <td>서버에서 레포트데이터를 보냄</td>
	 * </tr>
	 * <tr>
	 * <td>2</td>
	 * <td>4</td>
	 * <td>레포트 데이터를 받은 거에 대한 결과 응답</td>
	 * </tr>
	 * <tr>
	 * <td>3</td>
	 * <td>1</td>
	 * <td>모듈 실행 직 후 서버로 아이디,비밀번호 인증</td>
	 * </tr>
	 * <tr>
	 * <td>3</td>
	 * <td>2</td>
	 * <td>아이디, 비밀번호 인증 결과</td>
	 * </tr>
	 * </table>
	 * 
	 * @param size
	 * @param kind
	 * @param type
	 * @return
	 */
	public static void createMsgHeader2(ByteBuffer buffer, int size, String kind, String type) {
		try {
			buffer.put("NANAL".getBytes("UTF-8"));
			buffer.put(String.format("%05d", size + 2).getBytes("UTF-8"));
			buffer.put(kind.getBytes("UTF-8"));
			buffer.put(type.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * type 1인 경우에 대한 응답
	 * 
	 * @param kind 전문 종류
	 * @param code 보낼 응답코드(0000-정상, 그 외-에러)
	 * @return
	 */
	public static ByteBuffer createRespond(String kind, String code) {
		ByteBuffer buf = ByteBuffer.allocate(MSG_RESPOND_SIZE);
		buf.put(getByteUtf8("NANAL"));
		buf.put(getByteUtf8("00006"));
		buf.put(getByteUtf8(kind));
		buf.put(getByteEuckr("2"));
		buf.put(getByteUtf8(code));

		buf.flip();

		return buf;
	}

	/**
	 * <pre>
	 * type이 4인 응답
	 * 
	 * kind가 2이고 type이 3인경우 레포트 데이터가 들어오는 경우로
	 * 그 데이터에 대한 결과로 kind는 2, type은 4로 설정해서 서버로 응답함
	 * </pre>
	 * 
	 * @param kind 전문 종류
	 * @param code 보낼 응답코드(0000-정상, 그 외-에러)
	 * @return
	 */
	public static ByteBuffer createRespond2(String kind, String code) {
		ByteBuffer buf = ByteBuffer.allocate(MSG_RESPOND_SIZE);
		buf.put(getByteUtf8("NANAL"));
		buf.put(getByteUtf8("00006"));
		buf.put(getByteUtf8(kind));
		buf.put(getByteUtf8("4"));
		buf.put(getByteUtf8(code));

		buf.flip();

		return buf;
	}

	public static byte[] getByteUtf8(String s) {
		try {
			return s.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] getByteEuckr(String s) {
		try {
			return s.getBytes("EUC-KR");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 각 플랫폼 별 고유 번호 구하기
	 *
	 * @param appGubun 플랫폼 이름
	 * @return 플랫폼 고유 번호
	 */
	private static String getAppGubunValue(String appGubun) {
		if (appGubun.equalsIgnoreCase("KAKAO"))
			return "1";
		if (appGubun.equalsIgnoreCase("TOSS"))
			return "2";
		if (appGubun.equalsIgnoreCase("NAVER"))
			return "3";
		return "0";
	}

	public static String getSerialNumber(long sendPackUkey, String now, int i, String id) {
		StringBuilder sb = new StringBuilder();

		sb.append(now.substring(0, 8)).append("-").append(now.substring(8, 16))
				.append(String.format("%04d", i))
				.append(id.length() > 18 ? id.substring(0, 18) : id);

		return sb.toString();
	}
}
