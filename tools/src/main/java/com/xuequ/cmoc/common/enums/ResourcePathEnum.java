package com.xuequ.cmoc.common.enums;

public enum ResourcePathEnum {
	IMGE("IMAGE","/imagextra"),
	FILE("FILE", "/filextra"),
	VIDEO("VIDEO", "/videoxtra"),;
	
	private final String code;
	
	private final String desc;
	
	private ResourcePathEnum(String code, String desc) {
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
