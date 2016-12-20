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
	PWD_ERROR("1008", "账号或密码错误！"),
	ALREADY_REGISTER("1009", "您已注册！"),
	OPENID_DEFECT("1010", "openid缺失，请联系管理员"),
	ACTIVITY_NON_SIGN("1011", "您不在此次活动名单中，请下次再来"),
	EXPIRED_DATA("1012", "数据已失效，请刷新后再试"),
	HM_NON_ACTIVE("1013", "透明人未激活，不能报名参加活动"),
	ALREADY_SIGN_ACTIVITY("1014", "你已报名成功"),
	PARENT_BIND_ERROR("1015", "绑定失败！确认信息是否正确"),
	ORDER_WARN("1016", "订单存在安全风险，请稍后重试"),
	PARAM_FAIL("1017", "参数错误"),
	MARINE_SUPPORT_HAD("1018", "已经投过票"),
	ACTIVITY_NON_START("1019", "活动未开始"),
	ACCESS_TOKEN_FAIL("获取access_token失败","001"),
	JSAPI_TICKET_FAIL("获取jsapi_ticket失败","002"),
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
