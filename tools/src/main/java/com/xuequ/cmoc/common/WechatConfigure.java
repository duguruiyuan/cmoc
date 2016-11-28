package com.xuequ.cmoc.common;

import java.io.Serializable;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class WechatConfigure implements Serializable {

	private static final long serialVersionUID = 3341735121186898804L;
	private String token;
	
	private String appid;
	
	private String appsecret;
	
	private String grantType;
	
	private String accessToken;
	
	private String jsapiTicket;
	
	private String userAccessToken;
	
	private String userFefreshToken;
	
	private static WechatConfigure configure;
	
	public synchronized static WechatConfigure getInstance() {
		if(configure == null) {
			configure = new WechatConfigure();
			configure.init();
		}
		return configure;
	}
	
	private void init() {
		PropertiesConfiguration prop = new PropertiesConfiguration();
		prop.setEncoding("utf-8");
		try {
			prop.load("wechat.properties");
			configure.setAppid(prop.getString("appid"));
			configure.setAppsecret(prop.getString("appsecret"));
			configure.setGrantType(prop.getString("grant_type"));
			configure.setToken(prop.getString("token"));
			configure.setAccessToken(prop.getString("access_token"));
			configure.setJsapiTicket(prop.getString("jsapi_ticket"));
			configure.setUserAccessToken(prop.getString("user_access_token"));
			configure.setUserFefreshToken(prop.getString("user_refresh_token"));
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public String getUserAccessToken() {
		return userAccessToken;
	}

	public void setUserAccessToken(String userAccessToken) {
		this.userAccessToken = userAccessToken;
	}

	public String getUserFefreshToken() {
		return userFefreshToken;
	}

	public void setUserFefreshToken(String userFefreshToken) {
		this.userFefreshToken = userFefreshToken;
	}

	public static WechatConfigure getConfigure() {
		return configure;
	}

	public static void setConfigure(WechatConfigure configure) {
		WechatConfigure.configure = configure;
	}
	

}