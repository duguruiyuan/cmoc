package com.xuequ.cmoc.common;

import java.io.Serializable;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.xuequ.cmoc.utils.RequestUtil;

public class Configuration implements Serializable {

	private static final long serialVersionUID = -6442108412998953656L;

	private String env;
	
	private String appName;
	
	private String version;
	
	private String imgUrl;
	
	private String serverIp;
	
	private String wechatAttention;
	
	private static Configuration config;
	
	public synchronized static Configuration getInstance() {
		if(config == null) {
			config = new Configuration();
			config.init();
		}
		return config;
	}
	
	private void init() {
	    PropertiesConfiguration prop = new PropertiesConfiguration();
        prop.setEncoding("utf-8");
		try {
			prop.load("global.properties");
			config.setEnv(prop.getString("env"));
			config.setAppName(prop.getString("appName"));
			config.setVersion(prop.getString("version"));
			config.setServerIp(RequestUtil.getServerIp());
			prop.load("config.properties");
			config.setImgUrl(prop.getString(config.getEnv() + "_visited.url"));
			config.setWechatAttention(prop.getString("wechat_attention"));
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getWechatAttention() {
		return wechatAttention;
	}

	public void setWechatAttention(String wechatAttention) {
		this.wechatAttention = wechatAttention;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	
}
