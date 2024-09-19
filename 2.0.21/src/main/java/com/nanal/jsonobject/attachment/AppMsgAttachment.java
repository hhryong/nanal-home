package com.nanal.jsonobject.attachment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AppMsgAttachment
{
	@JsonProperty("button")
	public List<AppMsgButton> appMsgButton;
	@JsonProperty("item_highlight")
	public KakaoItemHighlight kakaoItemHighlight;
	@JsonProperty("item")
	public KakaoItem kakaoItem;
}
