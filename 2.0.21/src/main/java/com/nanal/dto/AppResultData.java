package com.nanal.dto;

import lombok.Data;

@Data
public class AppResultData
{
	private String appGubun;
	private long msgSeq;
	private String rsltCodeApp;
	private String moduleSendTime;
	private String svrRecvTime;
	private String appSendTime;
	private String appRecvTime;
	private String serialNumber;

	private String subId;
	private long sendPackUkey;

}
