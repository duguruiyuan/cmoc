package com.xuequ.cmoc.core.wechat.message;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("item")
public class ArticleItem extends XStreamSub implements Serializable {

	private static final long serialVersionUID = -6729416960196982499L;
	
	@XStreamAlias("Title")
	@XStreamCDATA
	private String title;
	
	@XStreamAlias("Description")
	@XStreamCDATA
	private String description;
	
	@XStreamAlias("PicUrl")
	@XStreamCDATA
	private String picUrl;
	
	@XStreamAlias("Url")
	@XStreamCDATA
	private String url;
	
	@XStreamCDATA
	private String author;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	
}
