package com.nanal.config;

import com.nanal.setting.GaonSetting;
import org.apache.log4j.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = { "com" })
@Configuration
public class BaseConfig {
    
	@Bean
	public GaonSetting nuriSetting() {
		return GaonSetting.getSetting();
	}

	@Bean
	public Logger rootLogger() {
		GaonSetting setting = nuriSetting();
		Logger logger = Logger.getLogger("systemLoger");
		logger.setLevel(Level.toLevel(setting.getLoggerLevel()));
		FileAppender userfa = new DailyRollingFileAppender();
		userfa.setName("userSystemLogger");
		userfa.setFile(setting.getLogFilePath() + setting.getGwClientId() + "_system.log");
		userfa.setLayout(new PatternLayout("[%5p][%d{yyyy-MM-dd HH:mm:ss.SSS}] - %m%n"));
		userfa.setThreshold(Level.toLevel(setting.getLoggerLevel()));
		userfa.setAppend(true);
		userfa.activateOptions();
		logger.addAppender(userfa);

		return logger;
	}

	@Bean
	public Logger sendLogger() {
		GaonSetting setting = nuriSetting();
		Logger logger = Logger.getLogger("sendFileLog");
		logger.setLevel(Level.toLevel(setting.getLoggerLevel()));
		FileAppender userfa = new DailyRollingFileAppender();
		userfa.setName("userSenderLogger");
		userfa.setFile(setting.getLogFilePath() + setting.getGwClientId() + "_sender.log");
		userfa.setLayout(new PatternLayout("[%5p][%d{yyyy-MM-dd HH:mm:ss.SSS}] - %m%n"));
		userfa.setThreshold(Level.toLevel(setting.getLoggerLevel()));
		userfa.setAppend(true);
		userfa.activateOptions();
		logger.addAppender(userfa);

		return logger;
	}

	@Bean
	public Logger recvLogger() {
		GaonSetting setting = nuriSetting();
		Logger logger = Logger.getLogger("recvFileLog");
		logger.setLevel(Level.toLevel(setting.getLoggerLevel()));
		FileAppender userfa = new DailyRollingFileAppender();
		userfa.setName("userReceiverLogger");
		userfa.setFile(setting.getLogFilePath() + setting.getGwClientId() + "_receiver.log");
		userfa.setLayout(new PatternLayout("[%5p][%d{yyyy-MM-dd HH:mm:ss.SSS}] - %m%n"));
		userfa.setThreshold(Level.toLevel(setting.getLoggerLevel()));
		userfa.setAppend(true);
		userfa.activateOptions();
		logger.addAppender(userfa);

		return logger;
	}

	@Bean
	public Logger lmsLogger() {
		GaonSetting setting = nuriSetting();
		Logger logger = Logger.getLogger("lmsLogger");
		logger.setLevel(Level.toLevel(setting.getLoggerLevel()));
		FileAppender userfa = new DailyRollingFileAppender();
		userfa.setName("userReceiverLogger");
		userfa.setFile(setting.getLogFilePath() + setting.getGwClientId() + "_lmssend.log");
		userfa.setLayout(new PatternLayout("[%5p][%d{yyyy-MM-dd HH:mm:ss.SSS}] - %m%n"));
		userfa.setThreshold(Level.toLevel(setting.getLoggerLevel()));
		userfa.setAppend(true);
		userfa.activateOptions();
		logger.addAppender(userfa);

		return logger;
	}
}
