package com.nanal.db;

import java.util.HashMap;
import java.util.List;

public interface LMSSenderDao {
	/**
	 * 커스텀 필드 포함 문자메시지 보내기
	 * 
	 * @param list
	 * @param code
	 */
	void sendMobileMessage(List<HashMap<String, Object>> list, int code);
}
