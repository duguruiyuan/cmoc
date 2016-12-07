package com.xuequ.cmoc.vo;

import com.xuequ.cmoc.model.GridBase;

public class MarineLiveQueryVO extends GridBase {

	private Integer marineId;
	
	private String msgType;

	public Integer getMarineId() {
		return marineId;
	}

	public void setMarineId(Integer marineId) {
		this.marineId = marineId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	
}
