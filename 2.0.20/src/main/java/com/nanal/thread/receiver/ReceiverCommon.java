package com.nanal.thread.receiver;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.nanal.dto.AppSendData;

public class ReceiverCommon {
	public final static int APP_GUBUN_INDEX = 0;
	public final static int SEND_DATE_TIME_INDEX = 1;
	public final static int BIZM_ORG_UK_INDEX = 2;
	public final static int ORG_CODE_INDEX = 3;
	public final static int DEPT_CODE_INDEX = 4;
	public final static int MANAGER_CODE_INDEX = 5;
	public final static int SERIAL_NUMBER_INDEX = 6;
	public final static int PACK_UNIQUEKEY_INDEX = 7;
	public final static int PHONE_NUMBER_INDEX = 8;
	public final static int NURI_ID_INDEX = 9;
	public final static int SUBID_INDEX = 10;
	public final static int APP_SEND_TIME_INDEX = 11;
	public final static int APP_RECV_TIME_INDEX = 12;
	public final static int VERSION_INDEX = 13;
	public final static int RSLT_CODE_APP_INDEX = 14;
	public final static int RESPONSE_DATA_SIZE = 15;
	public final static int STATE_SEND_COMPLTE = 1;
	public final static int STATE_NEED_FLOW_CONTROL = 2;

	public final static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	public static AppSendData getAppResultDataFromRespond(String[] resData) {
		AppSendData resultData = new AppSendData();

		String date = dateTimeFormat.format(new Date());
		resultData.setServerRecvTime(date);
		resultData.setAppRecvTime(date.substring(0, 8) + resData[ReceiverCommon.APP_RECV_TIME_INDEX].trim());
		resultData.setRsltCodeApp(resData[ReceiverCommon.RSLT_CODE_APP_INDEX].trim());
		resultData.setSerialNumber(resData[ReceiverCommon.SERIAL_NUMBER_INDEX].trim());

		return resultData;
	}
}
