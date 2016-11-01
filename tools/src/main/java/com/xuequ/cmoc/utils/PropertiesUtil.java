package com.xuequ.cmoc.utils;

import java.util.Properties;

/**
 * 系统属性文件配置
 * @author 胡启萌
 * @date 2015年9月22日
 */
public class PropertiesUtil {
	
	private static Properties properties;

	public static void setProperties(Properties properties) {
		PropertiesUtil.properties = properties;
	}
	
	public static String getProperty(String key) {
		return PropertiesUtil.properties.getProperty(key);
	}

}
