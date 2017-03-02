package com.xuequ.cmoc.common.enums;

public enum OrderStatusEnum {
	SUCCESS("000", " 支付成功"),
	PENDING("001", "未支付"),
	FAIL("002", "支付失败"),
	HANDING("003", "支付处理中");
	
	private final String code;
	
	private final String desc;
	
	private OrderStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDesc(String code) {
		for(OrderStatusEnum status : OrderStatusEnum.values()) {
			if(status.getCode().equals(code)) {
				return status.getDesc();
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
