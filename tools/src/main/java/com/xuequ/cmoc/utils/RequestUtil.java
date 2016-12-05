package com.xuequ.cmoc.utils;

import javax.servlet.http.HttpServletRequest;

public final class RequestUtil {

	/**
	 * 获取项目原路径
	 * @param request
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request) {
		return request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
	}
}
