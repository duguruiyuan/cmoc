package com.xuequ.cmoc.controller.core.wechat.message;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class TextMessage implements Serializable{

	private static final long serialVersionUID = 2589382404769447809L;
	
	@XStreamAlias("Content")
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
	
}
