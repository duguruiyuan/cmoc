package com.xuequ.cmoc.common.enums;

public enum ImgTypeEnum {
	ACTIVITY("ACTIVITY","活动"),
	MARINE_AND_MEMBER("MARINE_AND_MEMBER", "战队和队员"),
	MARINE("MARINE", "战队"),
	MEMBER("MEMBER", "队员");
	
	private final String code;
	
	private final String desc;
	
	private ImgTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
