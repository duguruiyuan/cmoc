package com.xuequ.cmoc.controller.core.wechat.message;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class ArticleItem implements Serializable {

	private static final long serialVersionUID = -6729416960196982499L;
	
	@XStreamAlias("Title")
	@XStreamCDATA
	private String Title;
	
	@XStreamAlias("Description")
	@XStreamCDATA
	private String Description;
	
	@XStreamAlias("PicUrl")
	@XStreamCDATA
	private String PicUrl;
	
	@XStreamAlias("Url")
	@XStreamCDATA
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
	
}
