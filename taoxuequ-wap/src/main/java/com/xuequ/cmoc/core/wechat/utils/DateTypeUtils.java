package com.xuequ.cmoc.core.wechat.utils;

public final class DateTypeUtils {

	/**
	 * 获取微信场景值id
	 * @auther 胡启萌
	 * @Date 2016年12月3日
	 * @param type 类型 1代表战队绑定
	 * @param id 
	 * @return
	 */
	public static Long getSceneId(int type, String id) {
		return Long.parseLong(type + id);
	}
	
	public static void main(String[] args) {
		try {
			String eventKey = "101";
			System.out.println(eventKey.indexOf("0"));
			System.out.println(Integer.parseInt(eventKey.substring(0, eventKey.indexOf("0"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
