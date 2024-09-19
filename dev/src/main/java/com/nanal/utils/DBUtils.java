package com.nanal.utils;

import com.nanal.setting.GaonSetting;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBUtils {
	public static String getLogTableName(GaonSetting setting) {
		String dataLogTableName;
		if (setting.getMakeLogMode().equalsIgnoreCase("default")) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
			String logSuffix = simpleDateFormat.format(new Date());
			dataLogTableName = String.format("%s_%s", setting.getAppSendDataLogTableName(), logSuffix);
		} else {
			dataLogTableName = setting.getAppSendDataLogTableName();
		}

		return dataLogTableName;
	}
}
