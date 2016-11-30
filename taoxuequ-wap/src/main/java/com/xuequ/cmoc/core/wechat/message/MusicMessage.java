package com.xuequ.cmoc.core.wechat.message;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class MusicMessage extends XStreamSub implements Serializable {

	private static final long serialVersionUID = 1052866590732472412L;
	
	@XStreamAlias("Title")
	@XStreamCDATA
	private String title;
	
	@XStreamAlias("Description")
	@XStreamCDATA
	private String description;
	
	@XStreamAlias("MusicUrl")
	@XStreamCDATA
	private String musicUrl;
	
	@XStreamAlias("HQMusicUrl")
	@XStreamCDATA
	private String hqMusicUrl;
	
	@XStreamAlias("ThumbMediaId")
	@XStreamCDATA
	private String thumbMediaId;

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

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getHqMusicUrl() {
		return hqMusicUrl;
	}

	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	
}
