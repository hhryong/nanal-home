package com.nanal.db.impl;

import com.nanal.db.AppSendDataDao;
import com.nanal.db.LMSSenderDao;
import com.nanal.db.MessageStateCode;
import com.nanal.setting.GaonSetting;
import com.nanal.utils.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class LMSSenderDaoImple implements LMSSenderDao {
	private static final String getNextValue = "getNextValue";
	private static final String lmsFormat = "lms.%s.%s";

	private final GaonSetting setting;
	private final Logger logger;
	private final SqlSession session;

	@Autowired
	public LMSSenderDaoImple(GaonSetting setting, AppSendDataDao appSendDataDao,
			@Qualifier("lmsLogger") Logger logger, SqlSession session) {
		this.setting = setting;
		this.logger = logger;
		this.session = session;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void sendMobileMessage(List<HashMap<String, Object>> list, int code) {
		for (HashMap<String, Object> d : list) {
			StringBuilder sb = new StringBuilder();

			String msg = "";

			if (setting.getLmsIncludeHeader()) {
				sb.append(d.get("HEAD")).append("\n");
			}

			if (d.get("GAON_MSG_TYPE").equals("L")) {
				msg = d.get("MSG_DATA").toString();
			} else {
				msg = d.get("SMS_MSG_DATA").toString();
			}

			sb.append(msg);

			if (setting.getLmsIncludeHeader() && d.get("FOOT") != null) {
				sb.append("\n").append(d.get("FOOT"));
			}

			msg = sb.toString().trim();

			try {
				if (msg.getBytes("EUC-KR").length <= 90) {
					d.put("SMS_MSG_DATA", msg);
					sendSms(d);
				} else {
					if (msg.getBytes("EUC-KR").length <= 2000) {
						d.put("MSG_DATA", msg);
						sendLms(d);
					} else {
						d.put("CUR_STATE", MessageStateCode.DATA_FAIL_LMS_SEND);
					}
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	private void sendLms(HashMap<String, Object> data) {
		logger.info(String.format("[LMS] MSG_SEQ: %s, PHONE_NUM: %s, SERIAL_NUMBER: %s",
				data.get("MSG_SEQ"), data.get("PHONE_NUM"), data.get("SERIAL_NUMBER")));

		data = addCustomColumnInfo(data);
		data.put("MSG_DATA", StringUtils.replaceNewLineChar(data.get("MSG_DATA").toString().trim()));

		// NURI_CONTENT_INFO 데이터 삽입
		if (setting.getMsgContentsInfoSeq().equals("DEFAULT")) {
			data.put("CONT_SEQ", data.get("PACK_UNIQUEKEY"));
			if (checkExsitContentsInfo(data) < 1) {
				session.insert(String.format(lmsFormat, setting.getDbName(),
						"insertContentsInfoWithCustomColumn"), data);
			}
		} else {
			if (setting.getMsgContentsInfoSeq().equals("AUTO")) {
				session.insert(String.format(lmsFormat, setting.getDbName(),
						"insertContentsInfoWithCustomColumnAuto"), data);
			} else {
				data.put("CONT_SEQ", getNextNuriConSeq());
				session.insert(String.format(lmsFormat, setting.getDbName(),
						"insertContentsInfoWithCustomColumn"), data);
			}
		}

		// NURI_MSG_DATA 데이터 삽입
		if (setting.getMsgDataSeq().equals("DEFAULT")) {
			data.put("NURI_MSG_SEQ", data.get("MSG_SEQ"));
			session.insert(
					String.format(lmsFormat, setting.getDbName(), "insertMsgDataWithCustomColumn"),
					data);
		} else {
			if (setting.getMsgDataSeq().equals("AUTO")) {
				session.insert(String.format(lmsFormat, setting.getDbName(),
						"insertMsgDataWithCustomColumnAuto"), data);
			} else {
				data.put("NURI_MSG_SEQ", getNextNuriMsgSeq());
				session.insert(String.format(lmsFormat, setting.getDbName(),
						"insertMsgDataWithCustomColumn"), data);
			}
		}
	}

	private void sendSms(HashMap<String, Object> data) {
		logger.info(String.format("[SMS] MSG_SEQ: %s, PHONE_NUM: %s, SERIAL_NUMBER: %s", data.get("MSG_SEQ"),
				data.get("PHONE_NUM"), data.get("SERIAL_NUMBER")));

		data = addCustomColumnInfo(data);
		data.put("SMS_MSG_DATA", StringUtils.replaceNewLineChar(data.get("SMS_MSG_DATA").toString().trim()));

		// NURI_MSG_DATA 데이터 삽입
		if (setting.getMsgDataSeq().equals("DEFAULT")) {
			data.put("NURI_MSG_SEQ", data.get("MSG_SEQ"));
			session.insert(String.format(lmsFormat, setting.getDbName(),
					"insertMsgDataSmsWithCustomColumn"), data);
		} else {
			if (setting.getMsgDataSeq().equals("AUTO")) {
				session.insert(String.format(lmsFormat, setting.getDbName(),
						"insertMsgDataSmsWithCustomColumnAuto"), data);
			} else {
				data.put("NURI_MSG_SEQ", getNextNuriMsgSeq());
				session.insert(String.format(lmsFormat, setting.getDbName(),
						"insertMsgDataSmsWithCustomColumn"), data);
			}
		}
	}

	private Long getNextNuriMsgSeq() {
		return (Long) session.selectOne(String.format(lmsFormat, setting.getDbName(), getNextValue),
				setting.getMsgDataSeq());
	}

	private Long getNextNuriConSeq() {
		return (Long) session.selectOne(String.format(lmsFormat, setting.getDbName(), getNextValue),
				setting.getMsgContentsInfoSeq());
	}

	private HashMap<String, Object> addCustomColumnInfo(HashMap<String, Object> data) {
		data.put("NURI_MSG_DATA_TABLE_NAME", setting.getMsgDataTableName());
		data.put("NURI_CONTENT_INFO_TABLE_NAME", setting.getMsgContentsInfoTableName());

		if (setting.getCopyCustomColumnWhenSendLms() != null
				&& setting.getCopyCustomColumnWhenSendLms()
				&& setting.getCustomColumnForAppSendData() != null
				&& !setting.getCustomColumnForAppSendData().isEmpty()) {
			List<String> keyList = new ArrayList<String>();
			List<Object> valueList = new ArrayList<Object>();

			for (String customField : setting.getCustomColumnForAppSendData()) {
				String fieldName = customField.split(" ")[0].toUpperCase();
				if (data.containsKey(fieldName)) {
					keyList.add(fieldName);
					valueList.add(data.get(fieldName));
				}
			}
			data.put("CUSTOM_COLUMN_KEY_LIST", keyList);
			data.put("CUSTOM_COLUMN_VALUE_LIST", valueList);
		}

		return data;
	}

	private Long checkExsitContentsInfo(HashMap<String, Object> data) {
		return (Long) session.selectOne(String.format(lmsFormat, setting.getDbName(), "checkExsitContentsInfo"), data);
	}
}
