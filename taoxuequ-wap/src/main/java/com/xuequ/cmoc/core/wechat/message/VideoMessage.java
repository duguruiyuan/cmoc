package com.xuequ.cmoc.core.wechat.message;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Video")
public class VideoMessage extends XStreamSub implements Serializable {

	private static final long serialVersionUID = -2830091182614877344L;

	@XStreamAlias("MediaId")
	@XStreamCDATA
	private String MediaId;
	
	@XStreamAlias("title")
	@XStreamCDATA
	private String title;
	
	@XStreamAlias("description")
	@XStreamCDATA
	private String description;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

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

	
	
}
