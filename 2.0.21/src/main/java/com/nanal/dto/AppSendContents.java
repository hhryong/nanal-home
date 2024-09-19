package com.nanal.dto;

import com.nanal.jsonobject.attachment.AppMsgAttachment;

import lombok.Data;

@Data
public class AppSendContents
{
	private String reqSendDate;
	private long packUniquekey;
	private String msgSubject;
	private String msgData;
	private String msgType;
	private String header;
	private int btnCount;
	private String attachment;
	private int linkCount;
	private String supplement;
	private int phoneCount;
	private int curState;
	private boolean isSms;

	private long realSendPackUniquekey;
	private String gwIp;
	private AppMsgAttachment msgAttachment;
}
