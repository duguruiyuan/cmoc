package com.xuequ.cmoc.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuequ.cmoc.common.Configuration;

public final class RequestUtil {

	/**
	 * 获取项目原路径
	 * @param request
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request) {
		return request.getScheme() + "://"
				+ request.getServerName() + 
				(Configuration.getInstance().getEnv().equals("development") ? (":" + request.getServerPort()) : "")
				+ request.getContextPath();
	}
	
	public static String getCookieValue(HttpServletRequest request, String name) {  
	    Cookie cookies[] = request.getCookies();  
	    if (cookies != null) {  
	        for (Cookie cookie : cookies) {  
	            if (!cookie.getName().equals(name))  
	                continue;  
	            return cookie.getValue();  
	        }  
	    }  
	    return null;  
	}  
	
	public static void saveCookie(HttpServletResponse response, String name, String value, int maxAge){  
	    Cookie cookie = new Cookie(name, value);  
	    cookie.setMaxAge(maxAge);  
	    response.addCookie(cookie);
	}  
}
