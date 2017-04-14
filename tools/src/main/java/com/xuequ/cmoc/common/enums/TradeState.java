package com.xuequ.cmoc.common.enums;

/**
 * 交易状态
 * @author huqimeng
 * @date 2017年4月9日
 */
public enum TradeState {
	SUCCESS("SUCCESS", "支付成功"),
	REFUND("REFUND", "转入退款"),
	NOTPAY("NOTPAY", "未支付"),
	CLOSED("CLOSED", "已关闭"),
	REVOKED("REVOKED", "已撤销（刷卡支付）"),
	USERPAYING("USERPAYING", "用户支付中"),
	PAYERROR("PAYERROR", "支付失败(其他原因，如银行返回失败)");
	 
	private final String code;
	
	private final String msg;
	
	private TradeState(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	
}
