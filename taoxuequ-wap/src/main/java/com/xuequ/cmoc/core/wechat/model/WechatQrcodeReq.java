package com.xuequ.cmoc.core.wechat.model;

import java.io.Serializable;

public class WechatQrcodeReq implements Serializable {

	private static final long serialVersionUID = 7177184928158275050L;
	/*
	 * 	该二维码有效时间，以秒为单位。 最大不超过604800（即7天）
	 */
	private Long expire_seconds;
	/*
	 * 二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,
	 * QR_LIMIT_STR_SCENE为永久的字符串参数值
	 */
	private String action_name;
	/*
	 * 	二维码详细信息
	 */
	private WechatActionInfo action_info;

	public Long getExpire_seconds() {
		return expire_seconds;
	}

	public void setExpire_seconds(Long expire_seconds) {
		this.expire_seconds = expire_seconds;
	}

	public String getAction_name() {
		return action_name;
	}

	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}

	public WechatActionInfo getAction_info() {
		return action_info;
	}

	public void setAction_info(WechatActionInfo action_info) {
		this.action_info = action_info;
	}
	
	
}
