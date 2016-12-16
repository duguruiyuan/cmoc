package com.xuequ.cmoc.common.enums;

public enum SignResource {

	ONLINE("ONLINE", "线上报名"),
	SCHOOL("SCHOOL", "学校报名");
	
	private final String code;
	
	private final String desc;
	
	private SignResource(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDesc(String code) {
		for(SignResource signResource : SignResource.values()) {
			if(signResource.getCode().equals(code)) {
				return signResource.getDesc();
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
