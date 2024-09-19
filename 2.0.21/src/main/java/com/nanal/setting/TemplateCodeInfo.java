package com.nanal.setting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TemplateCodeInfo
{
	private String appGubun;
	private String templateCode;
	private String header;
	private String foot;
	private String title;
	private String useButton;
}
