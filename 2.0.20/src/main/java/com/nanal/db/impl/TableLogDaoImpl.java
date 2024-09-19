package com.nanal.db.impl;

import com.nanal.db.DBCommon;
import com.nanal.db.TableLogDao;
import com.nanal.setting.GaonSetting;

import lombok.RequiredArgsConstructor;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Repository
public class TableLogDaoImpl implements TableLogDao {
	private final SqlSession session;
	private final DBCommon dbCommon;
	private final GaonSetting setting;
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	@Override
	public void logAppSendData(String tableNameSrc, String tableNameDest, List<Long> list) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("tableNameSrc", tableNameSrc);
		param.put("tableNameDest", tableNameDest);
		param.put("list", list);
		session.insert("log.sendDataLog", param);
	}

	@Override
	public void deleteAppSendData(String tableName, List<Long> list) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("tableName", tableName);
		param.put("list", list);
		session.insert("log.deleteData", param);
	}

	@Override
	public List<Long> getMovalbeDataList(String tableName) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		// 테이블 이름 설정
		params.put("tableName", tableName);
		params.put("useLms", setting.isUseLms());

		RowBounds rowBounds = new RowBounds(0, 1000);
		return session.selectList("log.getMovalbeDataList", params, rowBounds);
	}
}
