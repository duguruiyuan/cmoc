package com.xuequ.cmoc.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuequ.cmoc.common.Configuration;

public final class RequestUtil {
	
	/**
     * 获取服务器IP地址
     * @return
     */
    public static String  getServerIp(){
        String SERVER_IP = null;
        try {
        	Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();  
            InetAddress ip = null;  
            while (netInterfaces.hasMoreElements()) {  
                NetworkInterface ni = (NetworkInterface) netInterfaces  
                        .nextElement();  
                Enumeration enumeration = ni.getInetAddresses();
                if(!enumeration.hasMoreElements()) break;
                ip = (InetAddress) enumeration.nextElement(); 
                SERVER_IP = ip.getHostAddress();  
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()  
                        && ip.getHostAddress().indexOf(":") == -1) {  
                    SERVER_IP = ip.getHostAddress();  
                    break;  
                } else {  
                    ip = null;  
                }  
            }  
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return SERVER_IP;
    }

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
