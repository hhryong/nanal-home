package com.nanal.setting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.nanal.thread.GateWayServerInfo;
import com.nanal.utils.CommonUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GaonSetting {
	public static String path;
	private static GaonSetting setting;
	// DB
	private String dbName;
	private String dbDriverName;
	private String dbUrl;
	private String dbUser;
	private String dbPassword;

	// 디버깅 레벨
	private String loggerLevel;

	// 게이트웨이 정보
	private String[] gwIp;
	private String gwIp1;
	private String gwIp2;
	private Integer[] gwPort;
	private Integer gwPort1;
	private Integer gwPort2;
	private String gwClientId;
	private String gwClientPwd;
	private String gwEncryptedPwd;

	// 기존 누리 시퀸스
	private String msgDataSeq;
	private String msgContentsInfoSeq;

	// 기존 누리 테이블명
	private String msgDataTableName;
	private String msgContentsInfoTableName;

	// BIZM 테이블명
	private String appSendContentsTableName;
	private String appSendDataTableName;
	private String appSendDataLogTableName;
	private String templateCodeTableName;

	// 알림톡 실패시 lms 사용여부
	private Boolean useLms;
	private String dbCharset;

	private String logFilePath;
	private Long authCount;
	private Long authTime;
	// DB 로그 스케쥴
	private Boolean isTransferLog;
	private Integer logMinutes;
	private Integer logHour;
	private Integer logIntervalDay;
	private Boolean logRealTime;

	// 재접속 관련
	private Integer socketConnectionTimeOut;
	private Long retryTime;
	private Integer retryCount;
	private Boolean sendLmsWhenRetryEnd;
	private Boolean sendLmsWhenConnectionFail;

	// 사용자정의 컬럼
	private List<String> customColumnForAppSendData;
	private List<String> customColumnForSmsAppSendData;

	// 사용자정의 인덱스
	private List<CustomIndexInfo> customIndex;
	private List<CustomIndexInfo> customIndexForSmsAppSendData;

	// 로그파일 저장기간
	private Long logSaveTerm;

	// 센더 쓰레드 DB에서 읽어오는 주기 (단위 : 초)
	private Long tranTerm;

	// 사용자정의 컬럼 인덱스 최대 추가 가능한 갯수
	private Integer maxCustomColumnIndexCount;

	// LOG TABLE 모드
	private String makeLogMode;

	private Integer beforeTime;

	private Integer duplicatePhoneCount;

	// 타임아웃 시간
	private Integer timeoutLimit;

	private Integer flowControlTime;

	private Integer groupMessageCount;
	private Integer groupMessageMaxCount;

	private Integer logTableMoveInterval;

	private Integer logTerm;
	private Boolean encryptSettingFile;

	private Boolean copyCustomColumnWhenSendLms;

	private String noSendTimeStart;
	private String noSendTimeEnd;
	private List<String> excludeLmsError;
	private Boolean lmsIncludeHeader;

	

	public Boolean getLmsIncludeHeader() {
		return lmsIncludeHeader;
	}

	public void setLmsIncludeHeader(Boolean lmsIncludeHeader) {
		this.lmsIncludeHeader = lmsIncludeHeader;
	}

	public List<String> getExcludeLmsError() {
		return excludeLmsError;
	}

	public void setExcludeLmsError(List<String> excludeLmsError) {
		this.excludeLmsError = excludeLmsError;
	}

	public String getNoSendTimeStart() {
		return noSendTimeStart;
	}

	public void setNoSendTimeStart(String noSendTimeStart) {
		this.noSendTimeStart = noSendTimeStart.replaceAll("[^0-9]", "");
	}

	public String getNoSendTimeEnd() {
		return noSendTimeEnd;
	}

	public void setNoSendTimeEnd(String noSendTimeEnd) {
		this.noSendTimeEnd = noSendTimeEnd.replaceAll("[^0-9]", "");
	}

	public Boolean getCopyCustomColumnWhenSendLms() {
		return copyCustomColumnWhenSendLms;
	}

	public void setCopyCustomColumnWhenSendLms(Boolean copyCustomColumnWhenSendLms) {
		this.copyCustomColumnWhenSendLms = copyCustomColumnWhenSendLms;
	}

	public static GaonSetting getSetting() {
		if (setting == null) {
			File file = new File(path);
			if (!file.exists()) {
				System.out.println("설정 파일을 찾을 수 없습니다.");
				System.exit(1);
			}
			try {
				ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
				setting = objectMapper.readValue(file, GaonSetting.class);

				if (setting.getEncryptSettingFile() != null && setting.getEncryptSettingFile()) {
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					String line = null;
					StringBuilder sb = new StringBuilder();
					while ((line = br.readLine()) != null) {
						if (line.startsWith("gwClientPwd")) {
							sb.append("gwClientPwd: ").append(setting.getGwClientPwd())
									.append("\n");
						} else {
							sb.append(line).append("\n");
						}
					}

					br.close();

					FileWriter wr = new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(wr);

					bw.write(sb.toString());

					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

		return setting;
	}

	public Boolean getIsTransferLog() {
		return isTransferLog;
	}

	public void setIsTransferLog(Boolean isTransferLog) {
		this.isTransferLog = isTransferLog;
	}

	public static void setSetting(GaonSetting setting) {
		GaonSetting.setting = setting;
	}

	public Integer getLogTableMoveInterval() {
		return logTableMoveInterval;
	}

	public void setLogTableMoveInterval(Integer logTableMoveInterval) {
		this.logTableMoveInterval = logTableMoveInterval;
	}

	public Boolean isSendLmsWhenRetryEnd() {
		return sendLmsWhenRetryEnd;
	}

	public void setSendLmsWhenRetryEnd(Boolean sendLmsWhenRetryEnd) {
		this.sendLmsWhenRetryEnd = sendLmsWhenRetryEnd;
	}

	public Integer getSocketConnectionTimeOut() {
		return socketConnectionTimeOut;
	}

	public void setSocketConnectionTimeOut(Integer socketConnectionTimeOut) {
		this.socketConnectionTimeOut = socketConnectionTimeOut;
	}

	public Integer getFlowControlTime() {
		return Math.max(40, flowControlTime);
	}

	public void setFlowControlTime(Integer flowControlTime) {
		this.flowControlTime = flowControlTime;
	}

	public String getLogFilePath() {
		if (logFilePath == null || logFilePath.isEmpty()) {
			return "./gaonlog/";
		}
		return logFilePath + File.separator;
	}

	public void setLogFilePath(String logFilePath) {
		this.logFilePath = logFilePath;
	}

	public Integer getGwPort1() {
		return gwPort1;
	}

	public void setGwPort1(Integer gwPort1) {
		this.gwPort1 = gwPort1;
	}

	public Integer getGwPort2() {
		return gwPort2;
	}

	public void setGwPort2(Integer gwPort2) {
		this.gwPort2 = gwPort2;
	}

	public Integer getGroupMessageMaxCount() {
		return this.groupMessageMaxCount;
	}

	public void setGroupMessageMaxCount(Integer groupMessageMaxCount) {
		this.groupMessageMaxCount = Math.max(300, Math.min(5000, groupMessageMaxCount));
	}

	public Integer getGroupMessageCount() {
		return this.groupMessageCount;
	}

	public void setGroupMessageCount(Integer groupMessageCount) {
		this.groupMessageCount = Math.max(100, Math.min(1000, groupMessageCount));
	}

	public String[] getGwIp() {
		return gwIp;
	}

	public void setGwIp(String[] gwIp) {
		this.gwIp = gwIp;
	}

	public String getGwIp1() {
		return gwIp1;
	}

	public void setGwIp1(String gwIp1) {
		this.gwIp1 = gwIp1;
	}

	public String getGwIp2() {
		return gwIp2;
	}

	public void setGwIp2(String gwIp2) {
		this.gwIp2 = gwIp2;
	}

	public Integer getTimeoutLimit() {
		return timeoutLimit == null ? 120 : timeoutLimit;
	}

	public void setTimeoutLimit(Integer timeoutLimit) {
		this.timeoutLimit = Math.max(30, timeoutLimit);
	}

	public Integer getDuplicatePhoneCount() {
		return this.duplicatePhoneCount;
	}

	public void setDuplicatePhoneCount(Integer duplicatePhoneCount) {
		this.duplicatePhoneCount = Math.max(0, duplicatePhoneCount);
	}

	public Integer getBeforeTime() {
		return beforeTime;
	}

	public void setBeforeTime(Integer beforeTime) {
		this.beforeTime = Math.max(beforeTime, 3);
	}

	public List<CustomIndexInfo> getCustomIndexForSmsAppSendData() {
		return customIndexForSmsAppSendData;
	}

	public void setCustomIndexForSmsAppSendData(List<CustomIndexInfo> customIndexForSmsAppSendData) {
		this.customIndexForSmsAppSendData = customIndexForSmsAppSendData;
	}

	public String getMakeLogMode() {
		return makeLogMode;
	}

	public void setMakeLogMode(String makeLogMode) {
		this.makeLogMode = makeLogMode;
	}

	public Integer getMaxCustomColumnIndexCount() {
		return maxCustomColumnIndexCount;
	}

	public void setMaxCustomColumnIndexCount(Integer maxCustomColumnIndexCount) {
		this.maxCustomColumnIndexCount = Math.min(10, Math.max(0, maxCustomColumnIndexCount));
	}

	public Long getTranTerm() {
		return this.tranTerm;
	}

	public void setTranTerm(Long tranTerm) {
		this.tranTerm = Math.max(1, tranTerm);
	}

	public List<CustomIndexInfo> getCustomIndex() {
		return customIndex;
	}

	public void setCustomIndex(List<CustomIndexInfo> customIndex) {
		this.customIndex = customIndex;
	}

	public Long getLogSaveTerm() {
		return this.logSaveTerm;
	}

	public void setLogSaveTerm(Long logSaveTerm) {
		this.logSaveTerm = Math.max(1, logSaveTerm);
	}

	public List<String> getCustomColumnForAppSendData() {
		return customColumnForAppSendData;
	}

	public void setCustomColumnForAppSendData(List<String> customColumnForAppSendData) {
		this.customColumnForAppSendData = customColumnForAppSendData;
	}

	public List<String> getCustomColumnForSmsAppSendData() {
		return customColumnForSmsAppSendData;
	}

	public void setCustomColumnForSmsAppSendData(List<String> customColumnForSmsAppSendData) {
		this.customColumnForSmsAppSendData = customColumnForSmsAppSendData;
	}

	public Boolean isSendLmsWhenConnectionFail() {
		return sendLmsWhenConnectionFail;
	}

	public void setSendLmsWhenConnectionFail(Boolean sendLmsWhenConnectionFail) {
		this.sendLmsWhenConnectionFail = sendLmsWhenConnectionFail;
	}

	public Long getRetryTime() {
		return this.retryTime;
	}

	public void setRetryTime(Long retryTime) {
		this.retryTime = Math.max(1, retryTime);
	}

	public Integer getRetryCount() {
		return this.retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = Math.max(0, retryCount);
	}

	public Boolean isLogRealTime() {
		return logRealTime;
	}

	public void setLogRealTime(Boolean logRealTime) {
		this.logRealTime = logRealTime;
	}

	public Integer getLogMinutes() {
		return Math.min(Math.max(0, logMinutes), 59);
	}

	public void setLogMinutes(Integer logMinutes) {
		this.logMinutes = logMinutes;
	}

	public Integer getLogHour() {
		return Math.min(Math.max(0, logHour), 23);
	}

	public void setLogHour(Integer logHour) {
		this.logHour = logHour;
	}

	public Integer getLogIntervalDay() {
		return Math.min(Math.max(1, logIntervalDay), 180);
	}

	public void setLogIntervalDay(Integer logIntervalDay) {
		this.logIntervalDay = logIntervalDay;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbDriverName() {
		return dbDriverName;
	}

	public void setDbDriverName(String dbDriverName) {
		this.dbDriverName = dbDriverName;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getLoggerLevel() {
		return loggerLevel;
	}

	public void setLoggerLevel(String loggerLevel) {
		this.loggerLevel = loggerLevel;
	}

	public Integer[] getGwPort() {
		return gwPort;
	}

	public void setGwPort(Integer[] gwPort) {
		this.gwPort = gwPort;
	}

	public String getGwClientId() {
		return gwClientId;
	}

	public void setGwClientId(String gwClientId) {
		this.gwClientId = gwClientId;
	}

	public String getGwClientPwd() {
		if (gwEncryptedPwd == null) {
			gwEncryptedPwd = CommonUtil.encryptAdmString(gwClientPwd);
		}
		return gwEncryptedPwd;
	}

	public void setGwClientPwd(String gwClientPwd) {
		if (gwClientPwd.length() > 20) {
			String pure = CommonUtil.decryptAdmString(gwClientPwd);
			this.gwClientPwd = pure;
		} else {
			this.gwClientPwd = gwClientPwd;
		}
	}

	public String getMsgDataSeq() {
		return msgDataSeq;
	}

	public void setMsgDataSeq(String msgDataSeq) {
		this.msgDataSeq = msgDataSeq;
	}

	public String getMsgContentsInfoSeq() {
		return msgContentsInfoSeq;
	}

	public void setMsgContentsInfoSeq(String msgContentsInfoSeq) {
		this.msgContentsInfoSeq = msgContentsInfoSeq;
	}

	public String getMsgDataTableName() {
		return this.msgDataTableName;
	}

	public void setMsgDataTableName(String msgDataTableName) {
		this.msgDataTableName = getDbTableNameCaseSensitive(msgDataTableName);
	}

	public String getMsgContentsInfoTableName() {
		return this.msgContentsInfoTableName;
	}

	public void setMsgContentsInfoTableName(String msgContentsInfoTableName) {
		this.msgContentsInfoTableName = getDbTableNameCaseSensitive(msgContentsInfoTableName);
	}

	public String getAppSendContentsTableName() {
		return this.appSendContentsTableName;
	}

	public void setAppSendContentsTableName(String appSendContentsTableName) {
		this.appSendContentsTableName = getDbTableNameCaseSensitive(appSendContentsTableName);
	}

	public String getAppSendDataTableName() {
		return this.appSendDataTableName;
	}

	public void setAppSendDataTableName(String appSendDataTableName) {
		this.appSendDataTableName = getDbTableNameCaseSensitive(appSendDataTableName);
	}

	public String getTemplateCodeTableName() {
		return this.templateCodeTableName;
	}

	public void setTemplateCodeTableName(String templateCodeTableName) {
		this.templateCodeTableName = getDbTableNameCaseSensitive(templateCodeTableName);
	}

	public String getAppSendDataLogTableName() {
		return this.appSendDataLogTableName;
	}

	public void setAppSendDataLogTableName(String appSendDataLogTableName) {
		this.appSendDataLogTableName = getDbTableNameCaseSensitive(appSendDataLogTableName);
	}

	public Boolean isUseLms() {
		return useLms;
	}

	public void setUseLms(Boolean useLms) {
		this.useLms = useLms;
	}

	public String getDbCharset() {
		return dbCharset;
	}

	public void setDbCharset(String dbCharset) {
		this.dbCharset = dbCharset == null ? "UTF8" : dbCharset;
	}

	public GateWayServerInfo getServerInfo(Integer idx) {
		return new GateWayServerInfo(gwIp[0], gwPort[idx]);
	}

	public Integer getLogTerm() {
		return logTerm;
	}

	public void setLogTerm(Integer logTerm) {
		this.logTerm = logTerm;
	}

	public Long getAuthCount() {
		if (authCount == null)
			return 1L;
		return Math.max(authCount, 1);
	}

	public void setAuthCount(Long authCount) {
		this.authCount = authCount;
	}

	public Long getAuthTime() {
		if (authTime == null)
			return 1L;
		return Math.max(authTime, 1);
	}

	public void setAuthTime(Long authTime) {
		this.authTime = authTime;
	}

	public Boolean getEncryptSettingFile() {
		return encryptSettingFile;
	}

	public void setEncryptSettingFile(Boolean encryptSettingFile) {
		this.encryptSettingFile = encryptSettingFile;
	}

	private String getDbTableNameCaseSensitive(String tableName) {
		if (getDbName().equals("ORACLE") || getDbName().equals("TIBERO")
				|| getDbName().equals("ALTIBASE")) {
			return tableName.toUpperCase();
		} else if (getDbName().equals("POSTGRESQL") || getDbName().equals("CUBRID")) {
			return tableName.toLowerCase();
		} else {
			return tableName;
		}
	}
}
