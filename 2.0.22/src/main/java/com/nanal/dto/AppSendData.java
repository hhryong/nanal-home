package com.nanal.dto;

import com.nanal.jsonobject.attachment.AppMsgAttachment;
import com.nanal.setting.TemplateCodeInfo;

import lombok.Data;

@Data
public class AppSendData
{
	private String tableName;
	private long msgSeq;
	private String reqSendDate;
	private long packUniquekey;
	private String phoneNum;
	private long nuriMsgSeq;
	private int curState;
	private String appGubun;
	private String callBack;
	private int varCount;
	private String variables;
	private String subId;
	private String prevAppGubun;
	private int resendCnt;
	private String rsltCodeApp;
	private String gaonMsgType;
	private String smsMsgData;
	private String templateCode;
	private String moduleSendTime;
	private String serverRecvTime;
	private String appSendTime;

	private String appRecvTime;
	private String serialNumber;
	private long sendPackUkey;
	private boolean isReSendData = false;
	private String isTimeout;
	private TemplateCodeInfo templateCodeInfo;
	private AppSendContents appSendContents;

	private String realMsgData;
	private AppMsgAttachment msgAttachment;

	// APP_SEND_CONTENTS JOIN COLUMN
	private String msgSubject;
	private String msgData;
	private String attachment;

	// TEMPLATE_CODE JOIN COLUMN
	private String head;
	private String useButton;
	private String title;
	private String foot;

	private int updateRetryCount;

	private String gaonErrorCode;

	public void increaseUpdateRetryCount()
	{
		this.updateRetryCount++;
	}
}
