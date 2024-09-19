package com.nanal.db;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

public class DbQueryPrinter
{

	/**
	 * MYBATIS에서 실행한 쿼리문 불러오기
	 *
	 * @param session session
	 * @param logger logger
	 * @param id mybatis id
	 * @param params 파라메터
	 */
	public static void print(SqlSession session, Logger logger, String id, Object params)
	{
		BoundSql boundSql = session.getConfiguration().getMappedStatement(id).getBoundSql(params);
		String sql = boundSql.getSql();

		for (ParameterMapping mapping : boundSql.getParameterMappings())
		{
			sql = sql.replaceFirst("\\?", String.format("#{%s}", mapping.getProperty()));
		}
		logger.debug(String.format("MYBATIS_ID : %s, SQL QUERY : %s\tPARAMETERS : %s", id,
				sql.replaceAll("\r", "").replaceAll("\n", "").replaceAll("\\s+", " "),
				boundSql.getParameterObject()));
	}
}
