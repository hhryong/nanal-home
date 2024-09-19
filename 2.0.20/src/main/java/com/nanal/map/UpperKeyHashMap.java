package com.nanal.map;

import java.util.HashMap;

public class UpperKeyHashMap extends HashMap<String, Object> {
	private static final long serialVersionUID = 8925547745686250033L;

	@Override
	public Object put(String key, Object value) {
		return super.put(key.toUpperCase(), value);
	}
}
