package com.nanal.config;

import com.nanal.setting.GaonSetting;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;

/**
 * 패키지 : com.nanal.config 작성자 : 이준희 설명 : 마이바티스 관련 설정
 */

@EnableTransactionManagement
@Configuration
@Import(BaseConfig.class)
public class MybatisSetting {
	private final ApplicationContext applicationContext;
	private final Logger logger;
	private final GaonSetting setting;

	@Autowired
	public MybatisSetting(ApplicationContext applicationContext, GaonSetting setting) {
		this.applicationContext = applicationContext;
		this.logger = Logger.getRootLogger();
		this.setting = setting;
	}

	@Bean
	public BasicDataSource dataSource() {
		try {
			BasicDataSource r = new BasicDataSource();
			r.setDriverClassName(setting.getDbDriverName());
			r.setUrl(setting.getDbUrl());
			r.setUsername(setting.getDbUser());
			r.setPassword(setting.getDbPassword());
			r.setMaxActive(5);
			r.setMinIdle(5);
			r.setMaxIdle(5);
			r.setMaxWait(10000);
			r.setDefaultAutoCommit(true);
			if (setting.getDbName().equals("MSSQL")
					|| setting.getDbName().equals("MYSQL")
					|| setting.getDbName().equals("POSTGRESQL")) {
				r.setValidationQuery("SELECT 1");
			}
			if (setting.getDbName().equals("ORACLE")
					|| setting.getDbName().equals("TIBERO")
					|| setting.getDbName().equals("ALTIBASE")) {
				r.setValidationQuery("SELECT 1 FROM DUAL");
			}
			r.setTestOnBorrow(false);
			r.setTestOnReturn(false);
			if (setting.getDbName().equals("CUBRID")) {
				r.setTestWhileIdle(false);
			} else {
				r.setTestWhileIdle(true);
				r.setTimeBetweenEvictionRunsMillis(1000000);
			}
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			System.exit(1);
		}

		return null;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean r = new SqlSessionFactoryBean();
		r.setDataSource(dataSource());
		r.setConfigLocation(applicationContext.getResource("classpath:mybatis/config/mybatis-configuration.xml"));
		try {
			r.setMapperLocations(applicationContext.getResources("classpath:mybatis/mapper/*_mapper.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return r.getObject();
	}

	@Bean
	public SqlSession sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
}
