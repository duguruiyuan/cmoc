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
	
	private String oauth2Base;
	
	private String oauth2Userinfo;
	
	private String userInfo;
	
	private String snsUserInfo;
	
	private String qrcodeTicket;
	
	private String showQrcode;
	
	private String mediaDownload;
	
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
			configure.setOauth2Base(prop.getString("oauth2_base"));
			configure.setOauth2Userinfo(prop.getString("oauth2_userinfo"));
			configure.setUserInfo(prop.getString("user_info"));
			configure.setSnsUserInfo(prop.getString("sns_user_info"));
			configure.setQrcodeTicket(prop.getString("qrcode_ticket"));
			configure.setShowQrcode(prop.getString("show_qrcode"));
			configure.setMediaDownload(prop.getString("media_download"));
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

	public String getOauth2Base() {
		return oauth2Base;
	}

	public void setOauth2Base(String oauth2Base) {
		this.oauth2Base = oauth2Base;
	}

	public String getOauth2Userinfo() {
		return oauth2Userinfo;
	}

	public void setOauth2Userinfo(String oauth2Userinfo) {
		this.oauth2Userinfo = oauth2Userinfo;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getSnsUserInfo() {
		return snsUserInfo;
	}

	public void setSnsUserInfo(String snsUserInfo) {
		this.snsUserInfo = snsUserInfo;
	}

	public String getQrcodeTicket() {
		return qrcodeTicket;
	}

	public void setQrcodeTicket(String qrcodeTicket) {
		this.qrcodeTicket = qrcodeTicket;
	}

	public String getShowQrcode() {
		return showQrcode;
	}

	public void setShowQrcode(String showQrcode) {
		this.showQrcode = showQrcode;
	}

	public String getMediaDownload() {
		return mediaDownload;
	}

	public void setMediaDownload(String mediaDownload) {
		this.mediaDownload = mediaDownload;
	}

	public static WechatConfigure getConfigure() {
		return configure;
	}

	public static void setConfigure(WechatConfigure configure) {
		WechatConfigure.configure = configure;
	}
	

}
