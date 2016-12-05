package com.xuequ.cmoc.common.enums;

public enum WechatReqMsgType {
	
	TEXT("text", "文本"),
	IMAGE("image", "图片"),
	VOICE("voice", "语音"),
	VIDEO("video", "视频"),
	SHORTVIDEO("shortvideo", "小视频"),
	LOCATION("location", "地理位置"),
	LINK("link","链接");
	
	private final String code;
	
	private final String desc;
	
	private WechatReqMsgType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static String getDesc(String code) {
		for(WechatReqMsgType type : WechatReqMsgType.values()) {
			if (type.getCode().equals(code)) {
				return type.getDesc();
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
