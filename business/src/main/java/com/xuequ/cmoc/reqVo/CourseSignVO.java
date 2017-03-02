package com.xuequ.cmoc.reqVo;

import com.xuequ.cmoc.model.ChildSignInfo;

public class CourseSignVO extends ChildSignInfo {

	private static final long serialVersionUID = 5743949453539622570L;

	private String openid;
	
	private String productType;
	
	private String channel;
	
	private String wechatNum;
	
	private String headImg;
	
	private String city;
	
	private String nickName;
	
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getWechatNum() {
		return wechatNum;
	}

	public void setWechatNum(String wechatNum) {
		this.wechatNum = wechatNum;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
