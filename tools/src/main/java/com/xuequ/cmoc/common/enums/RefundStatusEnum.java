package com.xuequ.cmoc.common.enums;

public enum RefundStatusEnum {

	SUCCESS("000", "已退款"),
	HANDING("001", "退款申请中"),
	FAIL("002", "支付失败");
	
	private final String code;
	
	private final String desc;
	
	private RefundStatusEnum(String code, String desc) {
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
