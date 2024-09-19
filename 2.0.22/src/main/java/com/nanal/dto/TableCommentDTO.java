package com.nanal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TableCommentDTO
{
	private String tableName;
	private String columnName;
	private String comment;
}
