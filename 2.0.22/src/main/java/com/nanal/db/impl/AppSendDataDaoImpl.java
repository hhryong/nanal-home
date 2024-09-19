package com.nanal.db.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nanal.db.AppSendDataDao;
import com.nanal.db.DBCommon;
// import com.nanal.db.DbQueryPrinter;
import com.nanal.db.MessageStateCode;
import com.nanal.dto.AppSendData;
import com.nanal.setting.GaonSetting;
import com.nanal.setting.TemplateCodeInfo;

@Repository
public class AppSendDataDaoImpl implements AppSendDataDao {
	private final SqlSession session;
	private final GaonSetting setting;
	private final DBCommon dbCommon;
	private final SimpleDateFormat dateFormat;
	private final Logger rootLogger;

	@Autowired
	public AppSendDataDaoImpl(SqlSession session, GaonSetting setting, DBCommon dbCommon,
			@Qualifier("rootLogger") Logger logger) {
		this.session = session;
		this.setting = setting;
		this.dbCommon = dbCommon;
		this.dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		this.rootLogger = logger;
	}

	@Override
	public void updateAppSendContents(long packUniquekey, int code) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", setting.getAppSendContentsTableName());
		params.put("packUniquekey", packUniquekey);
		params.put("curState", code);

		// DbQueryPrinter.print(session, logger, "dataTreat.updateAppSendContentsState",
		// params);
		session.update("dataTreat.updateAppSendContentsState", params);
	}

	@Override
	public int updateAppSendDataCurStateSendComplate(AppSendData data) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", setting.getAppSendDataTableName());
		params.put("msgSeq", data.getMsgSeq());
		params.put("moduleSendTime", data.getModuleSendTime());
		params.put("serialNumber", data.getSerialNumber());
		params.put("curState", MessageStateCode.DATA_SEND_COMPLETE);

		return session.update("dataTreat.updateAppSendDataCurStateSendComplte", params);
	}

	@Override
	public void updateAppSendDataState(long msgSeq, int code) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", setting.getAppSendDataTableName());
		params.put("msgSeq", msgSeq);
		params.put("curState", code);

		session.update("dataTreat.updateAppSendDataState", params);
	}

	@Override
	public void updateAppSendDataInvail(AppSendData dto) {
		HashMap<String, Object> params = new HashMap<String, Object>();

		params.put("tableName", setting.getAppSendDataTableName());
		params.put("msgSeq", dto.getMsgSeq());
		params.put("curState", dto.getCurState());
		params.put("rsltCodeApp", dto.getRsltCodeApp());

		session.update("dataTreat.updateAppSendDataInvaild", params);
	}

	@Override
	public void updateAppSendDataStateAll(long packUniquekey, int code) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", setting.getAppSendDataTableName());
		params.put("packUniquekey", packUniquekey);
		params.put("curState", code);

		session.update("dataTreat.updateAppSendDataState", params);
	}

	@Transactional
	@Override
	public void updateAppSendDataStateListSendComplete(List<AppSendData> dataList) {
		for (AppSendData data : dataList) {
			updateAppSendDataCurStateSendComplate(data);
		}
	}

	@Override
	public TemplateCodeInfo selectTemplateCode(String appGubun, String templateCode) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", setting.getTemplateCodeTableName());
		params.put("templateCode", templateCode);
		params.put("appGubun", appGubun);

		return session.selectOne("dataTreat.selectTemplateCode", params);
	}

	@Override
	public void updateAppSendDataNuriMsgSeq(AppSendData data) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", setting.getAppSendDataTableName());
		params.put("data", data);

		session.update("dataTreat.updateAppSendDataNuriMsgSeq", params);
	}

	@Transactional
	@Override
	public void updateAppSendDataState(List<AppSendData> list, int code) {
		for (AppSendData data : list) {
			updateAppSendDataState(data.getMsgSeq(), code);
		}
	}

	@Override
	public int countAppSendDataWhereSendComplete() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", setting.getAppSendDataTableName());
		return (Integer) session.selectOne("dataTreat.countAppSendDataWhereCurState", params);
	}

	@Override
	public List<AppSendData> getAppSendDataLMSSendableList() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		Date date = dbCommon.getDbTime();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, -setting.getTimeoutLimit());

		params.put("tableName", setting.getAppSendDataTableName());
		params.put("time", dateFormat.format(cal.getTime()));
		params.put("useLms", setting.isUseLms());

		RowBounds limit = new RowBounds(0, 1000);

		return session.selectList("dataTreat.getAppSendDataLMSSendableList", params, limit);
	}

	@Override
	public List<TemplateCodeInfo> getTemplateCodeInfoList() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", setting.getTemplateCodeTableName());
		return session.selectList("dataTreat.getTemplateCodeInfoList", params);
	}

	@Override
	public AppSendData getAppSendDataBySendUkeyAndPhoneNum(AppSendData dto) {
		dto.setTableName(setting.getAppSendDataTableName());
		return session.selectOne("dataTreat.getAppSendDataBySendUkeyAndPhoneNum", dto);
	}

	@Override
	public void updateAppSendDataServerConnectionFail(AppSendData dto, String code) {
		dto.setTableName(setting.getAppSendDataTableName());
		dto.setCurState(MessageStateCode.DATA_SERVER_CONNECTFAIL);
		dto.setRsltCodeApp(code);
		session.update("dataTreat.updateAppSendDataServerConnectionFail", dto);
	}

	@Transactional
	@Override
	public void updateAppSendDataServerConnectionFail(List<AppSendData> list, String code) {
		for (AppSendData data : list) {
			updateAppSendDataServerConnectionFail(data, code);
		}
	}

	@Override
	public void updateAppSendDataResend(AppSendData dto) {
		dto.setTableName(setting.getAppSendDataTableName());
		session.update("dataTreat.updateAppSendDataResend", dto);
	}

	@Transactional
	@Override
	public void updateAppSendDataResend(List<AppSendData> dto) {
		for (AppSendData d : dto) {
			updateAppSendDataResend(d);
		}
	}

	@Override
	public List<AppSendData> getAppSendDataList() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		Date date = dbCommon.getDbTime();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, -setting.getBeforeTime());

		String prevDateTime = dateFormat.format(cal.getTime());
		String currDateTime = dateFormat.format(date);

		rootLogger.debug("불러온 데이터 기간: " + prevDateTime + " ~ " + currDateTime);

		params.put("tableName", setting.getAppSendDataTableName());
		params.put("contentsName", setting.getAppSendContentsTableName());
		params.put("templateName", setting.getTemplateCodeTableName());
		params.put("prevDate", prevDateTime);
		params.put("currentDate", currDateTime);

		RowBounds rowBounds = new RowBounds(0, setting.getGroupMessageCount());
		return session.selectList("dataTreat.getAppSendDataList", params, rowBounds);
	}

	@Override
	public void updateAppSendDataAfterLms(AppSendData dto) {
		dto.setTableName(setting.getAppSendDataTableName());
		session.update("dataTreat.updateAppSendDataAfterLms", dto);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateAppSendDataAfterLms(List<AppSendData> dto) {
		for (AppSendData d : dto) {
			updateAppSendDataAfterLms(d);
		}
	}

	@Override
	public List<HashMap<String, Object>> getAppSendDataLMSSendableListWithCustomColumn() {
		HashMap<String, Object> params = new HashMap<String, Object>();

		params.put("tableName", setting.getAppSendDataTableName());
		params.put("contentsTableName", setting.getAppSendContentsTableName());
		params.put("templateTableName", setting.getTemplateCodeTableName());

		RowBounds limit = new RowBounds(0, 100);
		return session.selectList("dataTreat.getAppSendDataLMSSendableListWithCustomColumn", params,
				limit);
	}

	@Transactional
	@Override
	public void updateAppSendDataAfterLms2(List<HashMap<String, Object>> list) {
		for (HashMap<String, Object> data : list) {
			data.put("APP_SEND_DATA_TABLE_NAME", setting.getAppSendDataTableName());
			updateAppSendDataAfterLms2(data);
		}
	}

	@Override
	public void updateAppSendDataAfterLms2(HashMap<String, Object> dto) {
		session.update("dataTreat.updateAppSendDataAfterLms2", dto);
	}

	@Override
	public int updateTimeoutDataError(List<String> serialNumberList) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", setting.getAppSendDataTableName());
		params.put("serialNumberList", serialNumberList);
		return session.update("dataTreat.updateTimeoutDataError", params);
	}

	@Override
	public int updateErrorCode(AppSendData dto) {
		dto.setTableName(setting.getAppSendDataTableName());
		return session.update("dataTreat.updateErrorCode", dto);
	}

	@Override
	public List<String> selectTimeoutData() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", setting.getAppSendDataTableName());
		Date currDate = dbCommon.getDbTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currDate);
		calendar.add(Calendar.MINUTE, -(setting.getTimeoutLimit() + setting.getTimeoutLimit()));
		params.put("limitTime", dateFormat.format(calendar.getTime()));
		RowBounds rowBounds = new RowBounds(0, 1000);
		return session.selectList("dataTreat.selectTimeoutData", params, rowBounds);
	}
}
