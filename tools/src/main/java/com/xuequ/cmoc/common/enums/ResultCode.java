package com.xuequ.cmoc.common.enums;

/**
 * 支付返回状态码
 * @author huqimeng
 * @date 2017年4月9日
 */
public enum ResultCode {
	SUCCESS("SUCCESS"),
	FAIL("FAIL");
	
	private final String code;
	
	private ResultCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

}
