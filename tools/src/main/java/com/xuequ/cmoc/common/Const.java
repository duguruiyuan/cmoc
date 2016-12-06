package com.xuequ.cmoc.common;

import java.util.Map;

import com.xuequ.cmoc.utils.PropertiesUtil;

public final class Const {

	public static final String SEPARATOR = "/";
	
	public static final String REPLACE_SEPARATOR = "__";
	
	public static final String DEFAULT_IMG_SUFFIX = "jpg";
	
	public static final String DOT = ".";
	
	public static String rootPath = PropertiesUtil.getProperty(Configuration.getInstance().getEnv() + "_resource.url");

	public static final String SYS_USER = "admin";
	
}
