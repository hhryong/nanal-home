package com.nanal.dto;

import lombok.Data;

@Data
public class LmsDataInfo
{
	private long msgSeq;
	private String callTo;
	private String callFrom;
	private long contSeq;
	private String smsTxt;
	private String subId;
	private String tableName;
}
