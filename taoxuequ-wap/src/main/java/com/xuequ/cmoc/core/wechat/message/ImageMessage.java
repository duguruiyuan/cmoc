package com.xuequ.cmoc.core.wechat.message;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class ImageMessage extends XStreamSub implements Serializable{

	private static final long serialVersionUID = 4979657664222360236L;
	
	@XStreamAlias("MediaId")
	@XStreamCDATA
	private String mediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	
}
