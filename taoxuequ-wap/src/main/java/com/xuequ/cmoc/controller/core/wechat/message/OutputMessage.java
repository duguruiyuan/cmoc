package com.xuequ.cmoc.controller.core.wechat.message;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class OutputMessage implements Serializable {

	private static final long serialVersionUID = 1526082641484914474L;

	@XStreamAlias("ToUserName")  
    @XStreamCDATA 
    private String ToUserName;  
  
    @XStreamAlias("FromUserName")  
    @XStreamCDATA  
    private String FromUserName;  
  
    @XStreamAlias("CreateTime")  
    private Long CreateTime;  
  
    private Object object;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
}
