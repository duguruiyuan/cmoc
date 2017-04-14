package com.xuequ.cmoc.common.enums;

public enum PayType {

	KUAIQIANBILL("99Bill","快钱支付"),
	CHINAPLAY("ChinaPlay","银联支付"),
	WEIXIN("WeiXin","微信支付"),
	ALIPAY("ALiPay","支付宝支付");
	
	private final String code;
	
	private final String desc;
	
	private PayType(String code, String desc){
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
