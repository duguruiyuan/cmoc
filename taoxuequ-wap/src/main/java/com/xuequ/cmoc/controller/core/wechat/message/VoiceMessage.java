package com.xuequ.cmoc.controller.core.wechat.message;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class VoiceMessage implements Serializable {

	private static final long serialVersionUID = 6053423577007188138L;

	@XStreamAlias("MediaId")
	@XStreamCDATA
	private Long MediaId;

	public Long getMediaId() {
		return MediaId;
	}

	public void setMediaId(Long mediaId) {
		MediaId = mediaId;
	}
	
	
}
