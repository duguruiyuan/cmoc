package com.xuequ.cmoc.core.wechat.utils;

public class WechatGlobalValue {
	
	private String key;
	
	private Object value;
	
	private long expireMillis;
	
	public WechatGlobalValue(String key, Object value, long mills) {
		this.key = key;
		this.value = value;
		if (mills>0){
			this.expireMillis = System.currentTimeMillis() + mills;
		}
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public long getExpireMillis() {
		return expireMillis;
	}

	public void setExpireMillis(long expireMillis) {
		this.expireMillis = expireMillis;
	}

	public void revive(long millis){
		if (millis>0){
			expireMillis = System.currentTimeMillis() + millis;
		}
	}
	
	public boolean isExpire(){
		if (expireMillis > 0){
			if (expireMillis < System.currentTimeMillis()){
				return true;
			}
		}
		return false;
	}
	

}
