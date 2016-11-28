package com.xuequ.cmoc.controller.core.wechat.message;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class MusicMessage implements Serializable {

	private static final long serialVersionUID = 1052866590732472412L;
	
	@XStreamAlias("Title")
	@XStreamCDATA
	private String Title;
	
	@XStreamAlias("Description")
	@XStreamCDATA
	private String Description;
	
	@XStreamAlias("MusicUrl")
	@XStreamCDATA
	private String MusicUrl;
	
	@XStreamAlias("HQMusicUrl")
	@XStreamCDATA
	private String HQMusicUrl;
	
	@XStreamAlias("ThumbMediaId")
	private Long ThumbMediaId;

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

	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}

	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}

	public Long getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(Long thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
}
