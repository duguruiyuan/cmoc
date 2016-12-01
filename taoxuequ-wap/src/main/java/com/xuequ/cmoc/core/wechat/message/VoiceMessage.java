package com.xuequ.cmoc.core.wechat.message;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class VoiceMessage extends XStreamSub implements Serializable {

	private static final long serialVersionUID = 6053423577007188138L;

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
