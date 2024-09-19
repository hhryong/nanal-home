package com.nanal.jsonobject.supplement;

import com.fasterxml.jackson.annotation.JsonProperty;

@Deprecated
public class QuickReply
{
	public String name;
	public String type;
	@JsonProperty("scheme_android")
	public String schemeAndroid;
	@JsonProperty("scheme_ios")
	public String schemeIos;
	@JsonProperty("url_mobile")
	public String urlMobile;
	@JsonProperty("url_pc")
	public String urlPc;
	@JsonProperty("chat_extra")
	public String chatExtra;
	@JsonProperty("chat_event")
	public String chatEvent;
}
