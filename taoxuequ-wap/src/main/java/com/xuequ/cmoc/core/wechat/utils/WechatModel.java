package com.xuequ.cmoc.core.wechat.utils;

import java.io.Serializable;

public class WechatModel implements Serializable {
	
	private static final long serialVersionUID = 8464930163333795532L;

	private String accessToken;
	
	private String jsapiTicket;
	/*
	 * js-sdk接口请求url
	 */
	private String url;
	/*
	 * 时间戳
	 */
	private Long timestamp; 
	/*
	 * 微信加密签名
	 */
	private String signature;
	/*
	 * 随机字符串
	 */
	private String nonceStr;
	
	private String appid;
	
	public WechatModel() {}
	
	public WechatModel(String accessToken, String jsapiTicket) {
		this.accessToken = accessToken;
		this.jsapiTicket = jsapiTicket;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
	
}
