package com.xuequ.cmoc.common.enums;

/**
 * 交易类型
 * @author huqimeng
 * @date 2017年4月9日
 */
public enum TradeType {
	
	JSAPI("JSAPI", "公众号支付"),
	NATIVE("NATIVE", "原生扫码支付号支付"),
	APP("APP", "app支付"),
	MICROPAY("MICROPAY", "刷卡支付");
	
	private final String code;
	
	private final String desc;
	
	private TradeType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
}
