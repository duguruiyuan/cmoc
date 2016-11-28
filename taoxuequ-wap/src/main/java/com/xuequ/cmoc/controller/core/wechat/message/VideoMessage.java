package com.xuequ.cmoc.controller.core.wechat.message;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class VideoMessage implements Serializable {

	private static final long serialVersionUID = -2830091182614877344L;

	@XStreamAlias("MediaId")
	@XStreamCDATA
	private Long MediaId;
	
	@XStreamAlias("Title")
	@XStreamCDATA
	private String Title;
	
	@XStreamAlias("Description")
	@XStreamCDATA
	private String Description;

	public Long getMediaId() {
		return MediaId;
	}

	public void setMediaId(Long mediaId) {
		MediaId = mediaId;
	}

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
	
	
}
