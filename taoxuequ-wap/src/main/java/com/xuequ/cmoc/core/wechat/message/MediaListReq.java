package com.xuequ.cmoc.core.wechat.message;

import java.io.Serializable;

public class MediaListReq implements Serializable {

	private static final long serialVersionUID = 8400739483284393089L;

	private String type = "news";
	
	private int offset;
	
	private int count;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
