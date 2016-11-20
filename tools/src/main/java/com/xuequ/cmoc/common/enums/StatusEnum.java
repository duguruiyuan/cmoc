package com.xuequ.cmoc.common.enums;

/**
 * 状态枚举
 * @author 胡启萌
 * @date 2015年8月26日 上午10:24:33
 */
public enum StatusEnum {
	
	SUCCESS("000", "成功"),
	PRIMARYKEYNULL("1001","id为空"),
	NOTEXITUSER("1002", "用户不存在"),
	TEMPLATE_ERROR("1003", "导入失败,请检查模板或数据!"),
	TEMPLATE_DATA_NULL("1004", "文件没有数据！"),
	ACTIVITY_OVER("1005", "活动已结束"),
	ACTIVITY_NOT_EXISTS("1006","活动不存在"),
	ACTIVITY_INVALID("1007","活动已失效"),
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
