package com.xuequ.cmoc.common.enums;

public enum ProductTypeEnum {

	ACTIVITY("ACTIVITY", "活动"),
	COURSE("COURSE", "课程");
	
	private final String code;
	
	private final String desc;
	
	private ProductTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDesc(String code) {
		for(ActivityTypeEnum activityType : ActivityTypeEnum.values()) {
			if(activityType.getCode().equals(code)) {
				return activityType.getDesc();
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
}
