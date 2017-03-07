package com.xuequ.cmoc.core.wechat.common;

public enum NewsTypeEnum {

	NEWS_INFO("newsInfo", "新闻资讯"),
	SCHOOL_CASE("school_Case", "校本案例"),
	FOLLOW_TWEET("follow_tweet", "趣味推文"),
	ABOUT_US("about_us", "了解我们");
	
	NewsTypeEnum(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	private String code;
	
	private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
