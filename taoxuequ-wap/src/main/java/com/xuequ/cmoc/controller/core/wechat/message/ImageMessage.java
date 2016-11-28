package com.xuequ.cmoc.controller.core.wechat.message;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Image")
public class ImageMessage implements Serializable{

	private static final long serialVersionUID = 4979657664222360236L;
	
	@XStreamAlias("MediaId")
	private Long MediaId;

	public Long getMediaId() {
		return MediaId;
	}

	public void setMediaId(Long mediaId) {
		MediaId = mediaId;
	}
	
	
}
