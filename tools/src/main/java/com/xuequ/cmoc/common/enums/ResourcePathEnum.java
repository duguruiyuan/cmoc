package com.xuequ.cmoc.common.enums;

public enum ResourcePathEnum {
	IMGE("IMAGE","/imgextra"),
	FILE("FILE", "/filextra"),
	VIDEO("VIDEO", "/videoxtra"),;
	
	private final String code;
	
	private final String value;
	
	private ResourcePathEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}
	
	
}
