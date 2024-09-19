package com.nanal.db.initializer;

import com.nanal.db.DBCommon;
import com.nanal.dto.TableCommentDTO;
import com.nanal.setting.GaonSetting;
import com.nanal.thread.sender.PacketInfo;
import com.nanal.utils.DBUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 패키지 : com.nanal.db.TableCreator 작성자 : 이준희 설명 : 가온 모듈 실행 후 필요한 테이블과 인덱스를 생성하는
 * 클래스
 */
@Component("initializer")
public class Initializer {
	private final Logger logger;
	private final GaonSetting setting;
	private final DBCommon db;
	private final TableCommentDTO[] contentsComments;
	private final TableCommentDTO[] dataComments;

	@Autowired
	public Initializer(GaonSetting setting, @Qualifier("rootLogger") Logger logger,
			DBCommon dbCommon) {
		this.setting = setting;
		this.logger = logger;
		this.db = dbCommon;

		contentsComments = new TableCommentDTO[] {
				new TableCommentDTO(setting.getAppSendContentsTableName(), "REQ_SEND_DATE",
						"메시지를 DB에 넣은 시간 (yyyymmddHHMMSS)"),
				new TableCommentDTO(setting.getAppSendContentsTableName(), "PACK_UNIQUEKEY",
						"일련번호"),
				new TableCommentDTO(setting.getAppSendContentsTableName(), "MSG_SUBJECT", "메시지 제목"),
				new TableCommentDTO(setting.getAppSendContentsTableName(), "MSG_DATA", "메시지 내용"),
				new TableCommentDTO(setting.getAppSendContentsTableName(), "MSG_TYPE",
						"메시지 타입 AT로 고정"),
				new TableCommentDTO(setting.getAppSendContentsTableName(), "HEADER", "카카오알림톡 헤더"),
				new TableCommentDTO(setting.getAppSendContentsTableName(), "BTN_CNT",
						"버튼 개수 (현재 최대 1개)"),
				new TableCommentDTO(setting.getAppSendContentsTableName(), "ATTACHMENT", "버튼 내용"),
				new TableCommentDTO(setting.getAppSendContentsTableName(), "LINK_CNT",
						"카카오 바로연결 개수(최대 1개) 사용X"),
				new TableCommentDTO(setting.getAppSendContentsTableName(), "SUPPLEMENT",
						"카카오 바로연결 내용 사용X"),
				new TableCommentDTO(setting.getAppSendContentsTableName(), "PHONE_CNT",
						"연락처 개수 사용X"),
				new TableCommentDTO(setting.getAppSendContentsTableName(), "CUR_STATE",
						"메시지 상태 사용X") };

		dataComments = new TableCommentDTO[] {
				new TableCommentDTO(setting.getAppSendDataTableName(), "MSG_SEQ", "일련번호"),
				new TableCommentDTO(setting.getAppSendDataTableName(), "REQ_SEND_DATE",
						"메시지를 DB에 넣은 시간 (yyyymmddHHMMSS) 미래 시간을 넣으면 예약발송"),
				new TableCommentDTO(setting.getAppSendDataTableName(), "PACK_UNIQUEKEY",
						"APP_SEND_CONTENTS의 PACK_UNIQUEKEY GAON_MSG_TYPE이 S인 경우 불필요"),
				new TableCommentDTO(setting.getAppSendDataTableName(), "PHONE_NUM", "수신 연락처"),
				new TableCommentDTO(setting.getAppSendDataTableName(), "NURI_MSG_SEQ",
						"발송 실패시 누리 테이블에 입력된 MSG_SEQ값"),
				new TableCommentDTO(setting.getAppSendDataTableName(), "CUR_STATE", "메시지 상태"),
				new TableCommentDTO(setting.getAppSendDataTableName(), "APP_GUBUN",
						"앱구분 KAKAO, NAVER"),
				new TableCommentDTO(setting.getAppSendDataTableName(), "CALL_BACK", "발신 연락처"),
				new TableCommentDTO(setting.getAppSendDataTableName(), "SUB_ID", "SUB_ID"),
				new TableCommentDTO(setting.getAppSendDataTableName(), "GAON_MSG_TYPE",
						"메시지 타입 L(장문), S(단문)"),
				new TableCommentDTO(setting.getAppSendDataTableName(), "SMS_MSG_DATA",
						"메시지 타입이 S(단문)인 경우 메시지 내용"),
				new TableCommentDTO(setting.getAppSendDataTableName(), "TEMPLATE_CODE", "템플릿 코드"),
				new TableCommentDTO(setting.getAppSendDataTableName(), "RSLT_CODE_APP",
						"발신 후 결과 코드"),
				new TableCommentDTO(setting.getAppSendDataTableName(), "MODULE_SEND_TIME",
						"모듈에서 서버로 발송한 시간"),
				new TableCommentDTO(setting.getAppSendDataTableName(), "SVR_RECV_TIME",
						"서버에서 레포트 결과를 받은 시간"),
				new TableCommentDTO(setting.getAppSendDataTableName(), "APP_SEND_TIME",
						"서버에서 앱메시지를 보낸 시간"),
				new TableCommentDTO(setting.getAppSendDataTableName(), "APP_RECV_TIME",
						"서버에서 앱메시지 결과를 받은 시간"),
				new TableCommentDTO(setting.getAppSendDataTableName(), "SERIAL_NUMBER", "시리얼 넘버"),
				new TableCommentDTO(setting.getAppSendDataTableName(), "SEND_PACK_UKEY",
						"서버에 발송할때 사용된 패킷의 고유번호") };
	}

	/**
	 * <pre>
	 *     가온 모듈 실행에 필요한 기본 테이블 및 인덱스를 생성
	 * </pre>
	 */
	public void initialize() {
		RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();

		if (!validationCheckSetting()) {
			System.exit(1);
		}

		logger.info("PID : " + Long.parseLong(mxBean.getName().split("@")[0]));
		logger.info("버전 : " + PacketInfo.displayVersion);
		logger.info("로깅 레벨 : " + setting.getLoggerLevel());
		for (int i = 0; i < setting.getGwPort().length; i++) {
			logger.info(String.format("게이트웨이 URL(%d) : %s.xxx.xxx.%s:%d", i + 1,
					setting.getGwIp()[0].split("\\.")[0], setting.getGwIp()[0].split("\\.")[3],
					setting.getGwPort()[i]));
		}
		String tmp = setting.getGwClientId();
		logger.info(
				String.format("게이트웨이 CLIENT ID : %s", tmp.substring(0, tmp.length() - 2) + "**"));
		logger.info(String.format("DB_DRIVER_NAME : %s", setting.getDbDriverName()));
		logger.info(String.format("DB_URL : %s", setting.getDbUrl()
				.replaceAll("(\\d{1,})\\.(\\d{1,})\\.(\\d{1,})\\.(\\d{1,})", "$1.xxx.xxx.$4")));
		String urlName = setting.getDbUser();
		logger.info(String.format("DB_USER_NAME : %s",
				urlName.substring(0, urlName.length() - 2) + "**"));
		logger.info(String.format("누리 CONTENTS_INFO 테이블 이름 : %s",
				setting.getMsgContentsInfoTableName()));
		logger.info(String.format("누리 CONTENTS_INFO 시퀸스 : %s", setting.getMsgContentsInfoSeq()));
		logger.info(String.format("누리 MSG_DATA 테이블 이름 : %s", setting.getMsgDataTableName()));
		logger.info(String.format("누리 MSG_DATA 시퀸스 : %s", setting.getMsgDataSeq()));
		logger.info(String.format("가온 SEND_CONTENTS 테이블 이름 : %s",
				setting.getAppSendContentsTableName()));
		logger.info(String.format("가온 SEND_DATA 테이블 이름 : %s", setting.getAppSendDataTableName()));
		logger.info(String.format("가온 SEND_DATA LOG 테이블 이름 : %s",
				setting.getAppSendDataLogTableName()));
		logger.info(
				String.format("가온 TEMPLATE_CODE 테이블 이름 : %s", setting.getTemplateCodeTableName()));
		logger.info(String.format("앱메시지 실패시 SMS/LMS 발송 여부 : %b", setting.isUseLms()));
		logger.info(String.format("REALTIME 여부 : %b", setting.isLogRealTime()));
		if (!setting.getMakeLogMode().equalsIgnoreCase("default")
				&& !setting.getMakeLogMode().equalsIgnoreCase("one")) {
			logger.info(String.format("makeLogMode 옵션에 잘못된 값이 들어왔습니다. (현재값 %s)",
					setting.getMakeLogMode()));
			System.exit(0);
		}
		logger.info(String.format("로그 테이블 생성 옵션 : %s", setting.getMakeLogMode()));
		logger.info(String.format("데이터 로그테이블로 이관 여부 : %b", setting.getIsTransferLog()));
		logger.info(String.format("DB에서 한번에 불러올 최대 행 수 : %d", setting.getGroupMessageCount()));
		logger.info(String.format("연락처 별 발송량 제한 : %d", setting.getDuplicatePhoneCount()));
		logger.info(String.format("발송 제한 시간 : %s ~ %s", setting.getNoSendTimeStart(),
				setting.getNoSendTimeEnd()));
		logger.info("로그 테이블 이관 여부: " + setting.getIsTransferLog());
		String errors = "";
		for (String error : setting.getExcludeLmsError()) {
			errors += error + ";";
		}
		logger.info("LMS 발송 제외 에러 " + errors);

		logger.info("-----------필요한 테이블 생성 시작-----------");
		while (true) {
			try {
				logger.info("접속 시도");
				db.getDbTime();
				logger.info("DB 접속 성공");
				break;
			} catch (Exception e) {
				logger.error("DB 접속 실패", e);
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e1) {
					Thread.currentThread().interrupt();
				}
			}
		}

		while (true) {
			try {
				logger.info(String.format("%s 테이블 생성 시작", setting.getAppSendContentsTableName()));
				if (setting.getDbName().equals("MYSQL")) {
					db.createAppSendContents(setting.getAppSendContentsTableName());
				} else {
					if (!db.checkExistTable(setting.getAppSendContentsTableName(),
							setting.getDbUser())) {
						db.createAppSendContents(setting.getAppSendContentsTableName());
						if (setting.getDbName().equals("ORACLE")
								|| setting.getDbName().equals("POSTGRESQL")) {
							for (TableCommentDTO comment : contentsComments) {
								db.addColumnComment(comment);
							}
						}
					} else {
						logger.info(String.format("%s 테이블이 이미 존재",
								setting.getAppSendContentsTableName()));
					}
				}
				logger.info(String.format("%s 테이블 생성 완료", setting.getAppSendContentsTableName()));
				break;
			} catch (Exception e) {
				logger.error("테이블 생성 에러", e);
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					Thread.currentThread().interrupt();
				}
			}
		}

		while (true) {
			// 컨텐츠 별 데이터를 담는 테이블 생성 (컨텐츠 테이블과 1:N관계)
			try {
				logger.info(String.format("%s 테이블 생성 시작", setting.getAppSendDataTableName()));
				if (setting.getDbName().equals("MYSQL")) {
					db.createAppSendData(setting.getAppSendDataTableName());
				} else {
					if (!db.checkExistTable(setting.getAppSendDataTableName(), setting.getDbUser())) {
						db.createAppSendData(setting.getAppSendDataTableName());
						if (setting.getDbName().equals("ORACLE")
								|| setting.getDbName().equals("POSTGRESQL")
								|| setting.getDbName().equals("TIBERO")) {
							for (TableCommentDTO comment : dataComments) {
								db.addColumnComment(comment);
							}
						}
					} else {
						logger.info(
								String.format("%s 테이블이 이미 존재", setting.getAppSendDataTableName()));
					}
				}
				db.createSerialNumberIndex();
				db.createCustomIndex();
				db.createCustomColumn(setting.getAppSendDataTableName());
				logger.info(String.format("%s 테이블 생성 완료", setting.getAppSendDataTableName()));
				break;
			} catch (Exception e) {
				logger.error("테이블 생성 에러", e);
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					Thread.currentThread().interrupt();
				}
			}
		}

		while (true) {
			// 템플릿 코드 관리 테이블 생성
			try {
				logger.info(String.format("%s 테이블 생성 시작", setting.getTemplateCodeTableName()));
				if (setting.getDbName().equals("MYSQL")) {
					db.createTemplateCodeTable(setting.getTemplateCodeTableName());
				} else {
					if (!db.checkExistTable(setting.getTemplateCodeTableName(),
							setting.getDbUser())) {
						db.createTemplateCodeTable(setting.getTemplateCodeTableName());
					} else {
						logger.info(
								String.format("%s 테이블이 이미 존재", setting.getTemplateCodeTableName()));
					}
				}
				db.createNewColumn(setting.getTemplateCodeTableName(), "USE_BUTTON CHAR(1)");
				logger.info(String.format("%s 테이블 생성 완료", setting.getTemplateCodeTableName()));
				break;
			} catch (Exception e) {
				logger.error("테이블 생성 에러", e);
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}

		String dataLogTableName = DBUtils.getLogTableName(setting);

		if (setting.getIsTransferLog()) {
			try {
				logger.info(String.format("%s 테이블 생성 시작", dataLogTableName));
				if (setting.getDbName().equals("MYSQL")) {
					db.createAppSendDataForLog(dataLogTableName);
				} else {
					if (!db.checkExistTable(dataLogTableName, setting.getDbUser())) {
						db.createAppSendDataForLog(dataLogTableName);
					} else {
						logger.info(String.format("%s 테이블 이미 존재", dataLogTableName));
					}
				}

				db.createCustomColumn(dataLogTableName);
				logger.info(String.format("%s 테이블 생성 완료", dataLogTableName));
			} catch (Exception f) {
				logger.error("테이블 생성 에러", f);
			}
		} else {
			logger.info("로그테이블 이관 사용하지 않음");
		}

		logger.info("-----------필요한 테이블 생성 완료-----------");
	}

	public boolean validationCheckSetting() {
		boolean result = true;

		result &= setting.getGwIp() != null;
		if (!result) {
			logger.error("Gateway ip is required");
			return result;
		}
		result &= setting.getGwPort() != null;
		if (!result) {
			logger.error("Gateway port is required");
			return result;
		}
		result &= setting.getGwClientId() != null;
		if (!result) {
			logger.error("Gateway client id is required");
			return result;
		}
		result &= setting.getGwClientPwd() != null;
		if (!result) {
			logger.error("Gateway password is required");
			return result;
		}
		result &= setting.getDbName() != null;
		if (!result) {
			logger.error("DB Name is required");
			return result;
		}
		result &= setting.getDbDriverName() != null;
		if (!result) {
			logger.error("DB driver name is required");
			return result;
		}
		result &= setting.getDbUrl() != null;
		if (!result) {
			logger.error("DB url is required");
			return result;
		}
		result &= setting.getDbUser() != null;
		if (!result) {
			logger.error("DB user is required");
			return result;
		}
		result &= setting.getDbPassword() != null;
		if (!result) {
			logger.error("DB password is required");
			return result;
		}
		result &= setting.getMsgDataSeq() != null;
		if (!result) {
			logger.error("Msg data sequence is required");
			return result;
		}
		result &= setting.getMsgContentsInfoSeq() != null;
		if (!result) {
			logger.error("Msg contents info sequence is required");
			return result;
		}
		result &= setting.getAppSendContentsTableName() != null;
		if (!result) {
			logger.error("App send contents table name is required");
			return result;
		}
		result &= setting.getAppSendDataTableName() != null;
		if (!result) {
			logger.error("App send data table name is required");
			return result;
		}
		result &= setting.getAppSendDataLogTableName() != null;
		if (!result) {
			logger.error("App send data log table name is required");
			return result;
		}
		result &= setting.getTemplateCodeTableName() != null;
		if (!result) {
			logger.error("Template code table name is required");
			return result;
		}
		result &= setting.getMsgDataTableName() != null;
		if (!result) {
			logger.error("Msg data table name is required");
			return result;
		}
		result &= setting.getMsgContentsInfoTableName() != null;
		if (!result) {
			logger.error("Msg contents info table name is required");
			return result;
		}
		result &= setting.isUseLms() != null;
		if (!result) {
			logger.error("Use lms is required");
			return result;
		}
		result &= setting.getCopyCustomColumnWhenSendLms() != null;
		if (!result) {
			logger.error("Copy custom column when send lms is required");
			return result;
		}
		result &= setting.getLoggerLevel() != null;
		if (!result) {
			logger.error("Logger level is required");
			return result;
		}
		result &= setting.isLogRealTime() != null;
		if (!result) {
			logger.error("Log real time is required");
			return result;
		}
		result &= setting.getMakeLogMode() != null;
		if (!result) {
			logger.error("Make log mode is required");
			return result;
		}
		result &= setting.getRetryTime() != null;
		if (!result) {
			logger.error("Retry time is required");
			return result;
		}
		result &= setting.getRetryCount() != null;
		if (!result) {
			logger.error("RetryCount is required");
			return result;
		}
		result &= setting.isSendLmsWhenRetryEnd() != null;
		if (!result) {
			logger.error("Send lms when retry end is required");
			return result;
		}
		result &= setting.isSendLmsWhenConnectionFail() != null;
		if (!result) {
			logger.error("Send lms when connection fail is required");
			return result;
		}
		result &= setting.getSocketConnectionTimeOut() != null;
		if (!result) {
			logger.error("Socket connection timeout is required");
			return result;
		}
		result &= setting.getMaxCustomColumnIndexCount() != null;
		if (!result) {
			logger.error("Max custom column index count is required");
			return result;
		}
		result &= setting.getLogSaveTerm() != null;
		if (!result) {
			logger.error("Log save term is required");
			return result;
		}
		result &= setting.getLogFilePath() != null;
		if (!result) {
			logger.error("Log file path is required");
			return result;
		}
		result &= setting.getTranTerm() != null;
		if (!result) {
			logger.error("Tran term is required");
			return result;
		}
		result &= setting.getBeforeTime() != null;
		if (!result) {
			logger.error("Before time is required");
			return result;
		}
		result &= setting.getDuplicatePhoneCount() != null;
		if (!result) {
			logger.error("Duplicate phone count is required");
			return result;
		}
		
		result &= setting.getGroupMessageCount() != null;
		if (!result) {
			logger.error("Group message count is required");
			return result;
		}
		result &= setting.getAuthCount() != null;
		if (!result) {
			logger.error("Auth count is required");
			return result;
		}
		result &= setting.getAuthTime() != null;
		if (!result) {
			logger.error("Auth time is required");
			return result;
		}
		result &= setting.getNoSendTimeStart() != null;
		if (!result) {
			logger.error("No send time start is required");
			return result;
		}
		result &= setting.getNoSendTimeEnd() != null;
		if (!result) {
			logger.error("No send time end is required");
			return result;
		}
		result &= setting.getIsTransferLog() != null;
		if (!result) {
			logger.error("Is transfer log is required");
			return result;
		}

		if (setting.getExcludeLmsError() == null) {
			setting.setExcludeLmsError(new ArrayList<String>());
			logger.info("ExcludeLmsError set empty array");
		}

		if (setting.getLmsIncludeHeader() == null) {
			setting.setLmsIncludeHeader(false);
			logger.info("lmsIncludeHeader 옵션이 비어 있습니다. 기본값: no로 셋팅");
		}

		return result;
	}
}
