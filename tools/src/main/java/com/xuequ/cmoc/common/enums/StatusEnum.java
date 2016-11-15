package com.xuequ.cmoc.common.enums;

/**
 * 状态枚举
 * @author 胡启萌
 * @date 2015年8月26日 上午10:24:33
 */
public enum StatusEnum {
	
	SUCCESS("000", "成功"),
	PRIMARYKEYNULL("1001","ID为空"),
	NOTEXITUSER("1002", "用户不存在"),
	FAIL("9999", "失败");
	
	private String code;
	
	private String msg;
	
	private StatusEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}
