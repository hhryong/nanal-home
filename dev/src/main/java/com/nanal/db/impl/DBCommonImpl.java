package com.nanal.db.impl;

import com.nanal.db.DBCommon;
import com.nanal.db.DbQueryPrinter;
import com.nanal.dto.TableCommentDTO;
import com.nanal.setting.CustomIndexInfo;
import com.nanal.setting.GaonSetting;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

@Repository
public class DBCommonImpl implements DBCommon {
	private static final String createrFormat = "createTable.%s.%s";
	private static final String createAppSendContents = "createAppSendContents";
	private static final String createAppSendData = "createAppSendData";
	private static final String createAppSendDataForLog = "createAppSendDataForLog";
	private static final String checkTableExists = "checkTableExists";
	private static final String createTemplateCode = "createTemplateCode";

	private final SqlSession session;
	private final SqlSessionFactory sessionFactory;
	private final GaonSetting setting;
	private final Logger logger;

	@Autowired
	public DBCommonImpl(SqlSession session, GaonSetting setting,
			@Qualifier("rootLogger") Logger logger, SqlSessionFactory sessionFactory) {
		this.session = session;
		this.setting = setting;
		this.logger = logger;
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void createAppSendDataForLog(String tableName) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", tableName);
		params.put("charset", setting.getDbCharset());
		if (setting.getCustomColumnForAppSendData() != null) {
			StringBuilder sb = new StringBuilder();
			int count = setting.getMaxCustomColumnIndexCount();
			for (String column : setting.getCustomColumnForAppSendData()) {
				if (count > 0) {
					sb.append(column).append(",");
					count--;
				} else {
					break;
				}
			}
			String customColumn = sb.toString().replaceAll(",$", "");
			params.put("customColumn", customColumn);
		}

		session.selectOne(
				String.format(createrFormat, setting.getDbName(), createAppSendDataForLog), params);
		DbQueryPrinter.print(session, logger,
				String.format(createrFormat, setting.getDbName(), createAppSendDataForLog), params);
	}

	@Override
	public void createAppSendContents(String tableName) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", tableName);
		params.put("charset", setting.getDbCharset());
		session.selectOne(String.format(createrFormat, setting.getDbName(), createAppSendContents),
				params);
		DbQueryPrinter.print(session, logger,
				String.format(createrFormat, setting.getDbName(), createAppSendContents), params);
	}

	@Override
	public void createAppSendData(String tableName) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", tableName);
		params.put("charset", setting.getDbCharset());
		if (setting.getCustomColumnForAppSendData() != null) {
			StringBuilder sb = new StringBuilder();
			int count = setting.getMaxCustomColumnIndexCount();
			for (String column : setting.getCustomColumnForAppSendData()) {
				if (count > 0) {
					sb.append(column).append(",");
					count--;
				} else {
					break;
				}
			}

			params.put("customColumn", sb.toString());
		}

		session.selectOne(String.format(createrFormat, setting.getDbName(), createAppSendData),
				params);
		DbQueryPrinter.print(session, logger,
				String.format(createrFormat, setting.getDbName(), createAppSendData), params);
	}

	@Override
	public void createCustomIndex() {
		if (setting.getCustomIndex() != null) {
			int count = 0;
			if (setting.getCustomColumnForAppSendData() == null) {
				count = setting.getMaxCustomColumnIndexCount();
			} else {
				count = setting.getMaxCustomColumnIndexCount()
						- setting.getCustomColumnForAppSendData().size();
			}
			for (CustomIndexInfo info : setting.getCustomIndex()) {
				if (count > 0) {
					info.setTargetTableName(setting.getAppSendDataTableName());
					try {
						logger.info(
								String.format("INDEX NAME - %s, TARGET TABLE - %s, COLUMNS - %s",
										info.getIndexName(), setting.getAppSendDataTableName(),
										info.getColumns()));
						DbQueryPrinter.print(session, logger, "common.createIndex", info);
						session.selectOne("common.createIndex", info);
					} catch (Exception e) {

					}
					count--;
				} else {
					break;
				}
			}
		}
	}

	@Override
	public boolean checkExistTable(String tableName, String owner) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("tableName", tableName);
		param.put("owner", owner.toUpperCase());
		return 1 == (Integer) session.selectOne(
				String.format(createrFormat, setting.getDbName(), checkTableExists), param);
	}

	@Override
	public void createTemplateCodeTable(String tableName) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", tableName);
		params.put("charset", setting.getDbCharset());
		session.selectOne(String.format(createrFormat, setting.getDbName(), createTemplateCode),
				params);
		DbQueryPrinter.print(session, logger,
				String.format(createrFormat, setting.getDbName(), createTemplateCode), params);
	}

	@Override
	public Date getDbTime() {
		return session.selectOne(String.format(createrFormat, setting.getDbName(), "getDbTime"));
	}

	@Override
	public void createPackUniquekeyIndex() {
		CustomIndexInfo myIndex = new CustomIndexInfo();
		myIndex.setIndexName("APP_SEND_DATA_PACK_UKEY_INDEX");
		myIndex.setTargetTableName(setting.getAppSendDataTableName());
		myIndex.setColumns("PACK_UNIQUEKEY");

		DbQueryPrinter.print(session, logger, "common.createIndex", myIndex);
		try {
			session.selectOne("common.createIndex", myIndex);
		} catch (Exception e) {
			if (setting.getDbName().equals("MYSQL")) {
				if (!e.getMessage().contains("Duplicate key name 'APP_SEND_DATA_PACK_UKEY_INDEX'")) {
					logger.debug(e.getMessage());
				}
			} else {
				logger.debug(e.getMessage());
			}
		}
	}

	@Override
	public void addColumnComment(TableCommentDTO comment) {
		try {
			DbQueryPrinter.print(session, logger, "common.addColumnComment", comment);
			session.selectOne("common.addColumnComment", comment);
		} catch (Exception e) {

		}
	}

	@Override
	public void createSerialNumberIndex() {
		String createIndexName = String.format("%s_SERIAL_IDX", setting.getAppSendDataTableName());
		CustomIndexInfo myIndex = new CustomIndexInfo();
		myIndex.setIndexName(createIndexName);
		myIndex.setTargetTableName(setting.getAppSendDataTableName());
		myIndex.setColumns("SERIAL_NUMBER");

		DbQueryPrinter.print(session, logger, "common.createUniqueIndex", myIndex);
		try {
			session.selectOne("common.createUniqueIndex", myIndex);
		} catch (Exception e) {

		}
	}

	@Override
	public void createCustomColumn(String tableName) {
		SqlSession s = sessionFactory.openSession();
		try {
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("TABLE_NAME", tableName);
			Statement statement = s.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s WHERE 0=1", tableName));

			ResultSetMetaData metadata = resultSet.getMetaData();
			int columnCount = metadata.getColumnCount();

			HashSet<String> columnNames = new HashSet<String>();
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metadata.getColumnName(i);
				columnNames.add(columnName.toUpperCase());
			}

			if (setting.getCustomColumnForAppSendData() != null) {
				int count = setting.getMaxCustomColumnIndexCount();
				for (String column : setting.getCustomColumnForAppSendData()) {
					if (count > 0) {
						String[] info = column.split(" +");
						String name = info[0].trim().toUpperCase();
						String type = info[1].trim().toUpperCase();

						if (!columnNames.contains(name)) {
							params.put("COLUMN_NAME", name);
							params.put("COLUMN_TYPE", type);
							session.selectOne(String.format(createrFormat, setting.getDbName(),
									"createColumn"), params);
						}
						count--;
					} else {
						break;
					}
				}
			}

		} catch (SQLException e) {
			logger.error("사용자정의 컬럼 생성 실패", e);
		} finally {
			s.close();
		}
	}

	@Override
	public void createNewColumn(String tableName, String columnList) {
		SqlSession s = sessionFactory.openSession();
		try {
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("TABLE_NAME", tableName);
			Statement statement = s.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s WHERE 0=1", tableName));

			ResultSetMetaData metadata = resultSet.getMetaData();
			int columnCount = metadata.getColumnCount();

			HashSet<String> columnNames = new HashSet<String>();
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metadata.getColumnName(i);
				columnNames.add(columnName.toUpperCase());
			}

			String[] newColumnList = columnList.split(";");

			for (String newColumn : newColumnList) {
				String[] tmp = newColumn.split(" ");
				String columnName = tmp[0];
				String dataType = tmp[1];

				if (!columnNames.contains(columnName)) {
					params.put("COLUMN_NAME", columnName);
					params.put("COLUMN_TYPE", dataType);
					session.selectOne(
							String.format(createrFormat, setting.getDbName(), "createColumn"),
							params);
				}
			}
		} catch (SQLException e) {
			logger.error("새로생긴 컬럼 추가 실패", e);
		} finally {
			s.close();
		}
	}
}
