package com.xuequ.cmoc.core.wechat.utils;

import java.util.HashMap;
import java.util.Map;

public final class WechatGlobalMap {
	
	public static Map<String, WechatGlobalValue> caches = new HashMap<String, WechatGlobalValue>();
	
	public static void put(String key, Object value) {
		put(key, value, 0);
	}
	
	public static void put(String key, Object value, long mills) {
		WechatGlobalValue newValue = new WechatGlobalValue(key, value, (mills - 600) * 1000 );
		caches.put(key, newValue);
	}
	
	public static WechatGlobalValue get(String key) {
		WechatGlobalValue value = caches.get(key);
		if(value != null && !value.isExpire()) {
			return value;
		}
		return null;
	}

}
