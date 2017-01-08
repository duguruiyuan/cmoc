package com.xuequ.cmoc.core.wechat.message;

import java.io.Serializable;

import org.nfunk.jep.function.Str;

public class ShorturlReq implements Serializable {

	private static final long serialVersionUID = -7358928357833381122L;

	private String action = "long2short";
	private String long_url;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getLong_url() {
		return long_url;
	}
	public void setLong_url(String long_url) {
		this.long_url = long_url;
	}
	
}
