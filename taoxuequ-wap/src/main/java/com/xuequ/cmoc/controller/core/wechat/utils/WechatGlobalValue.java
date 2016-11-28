package com.xuequ.cmoc.controller.core.wechat.utils;

public class WechatGlobalValue {
	
	private String key;
	
	private String value;
	
	private long expireMillis;
	
	public WechatGlobalValue(String key, String value, long mills) {
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
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
