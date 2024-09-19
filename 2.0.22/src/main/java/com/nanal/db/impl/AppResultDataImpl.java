package com.nanal.db.impl;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nanal.db.AppResultDataDao;
import com.nanal.dto.AppSendData;
import com.nanal.setting.GaonSetting;

@Repository
public class AppResultDataImpl implements AppResultDataDao {
	private final SqlSession session;
	private final GaonSetting setting;

	@Autowired
	public AppResultDataImpl(SqlSession session, GaonSetting setting) {
		this.session = session;
		this.setting = setting;
	}

	@Override
	public int updateAppSendDataAfterRespond(AppSendData data) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", setting.getAppSendDataTableName());
		params.put("data", data);
		return session.update("dataTreat.updateAppSendDataAfterRespond", params);
	}

	@Override
	public int updateTimeoutDataReport(AppSendData data) {
		data.setTableName(setting.getAppSendDataTableName());
		return session.update("dataTreat.updateTimeoutDataReport", data);
	}
}
