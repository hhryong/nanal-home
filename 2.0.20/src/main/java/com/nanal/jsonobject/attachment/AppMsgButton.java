package com.nanal.jsonobject.attachment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppMsgButton
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
	@JsonProperty("plugin_id")
	public String pluginId;
	@JsonProperty("relay_id")
	public String relayId;
	@JsonProperty("oneclick_id")
	public String oneclickId;
	@JsonProperty("product_id")
	public String productId;

}
