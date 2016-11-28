package com.xuequ.cmoc.controller.core.wechat.utils;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.utils.HttpClientUtils;
import com.xuequ.cmoc.utils.TextUtil;

public class WechatUtils {
	
	private static Logger LOG = Logger.getLogger(WechatUtils.class);
	
	private final static String ACCESS_TOKEN = "access_token";
	
	private final static String JSAPI_TICKET = "jsapi_ticket";
	
	public static WechatModel getWechatModel() {
		return new WechatModel(getAccessToken(), getJsapiTicket());
	}
	
	public static String getAccessToken() {
		WechatGlobalValue globalValue = WechatGlobalMap.get(ACCESS_TOKEN); 
		if(globalValue != null) {
			return globalValue.getValue();
		}
		return getToken(ACCESS_TOKEN);
	}
	
	public static String getJsapiTicket() {
		WechatGlobalValue globalValue = WechatGlobalMap.get(JSAPI_TICKET); 
		if(globalValue != null) {
			return globalValue.getValue();
		}
		return getToken(JSAPI_TICKET);
	}
	
	private static String getToken(String key) {
		String accessToken = createAccessToken();
		String jsapiTicket = createJsapiTicket(accessToken);
		if(key.equals(ACCESS_TOKEN)) {
			return accessToken;
		}
		return jsapiTicket;
	}
	
	private static String createAccessToken() {
		WechatConfigure configure = WechatConfigure.getInstance();
		String url = TextUtil.format(configure.getAccessToken(), 
				new String[]{configure.getGrantType(), configure.getAppid(), configure.getAppsecret()});
		try {
			JSONObject json = JSONObject.parseObject(HttpClientUtils.doGet(url));
			LOG.info("获取的access_token>>" + json.toJSONString());
			if(json != null) {
				String access_token = json.getString("access_token");
				int expires_in = json.getIntValue("expires_in");
				WechatGlobalMap.put(ACCESS_TOKEN, access_token, expires_in);
				return access_token;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String createJsapiTicket(String accessToken) {
		WechatConfigure configure = WechatConfigure.getInstance();
		String url = TextUtil.format(configure.getJsapiTicket(), accessToken);
		try {
			JSONObject json = JSONObject.parseObject(HttpClientUtils.doGet(url));
			LOG.info("获取的jsapi_ticket>>" + json.toJSONString());
			if(json != null) {
				String access_token = json.getString("ticket");
				int expires_in = json.getIntValue("expires_in");
				WechatGlobalMap.put(JSAPI_TICKET, access_token, expires_in);
				return access_token;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
