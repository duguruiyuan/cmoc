package com.xuequ.cmoc.core.wechat.utils;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.xuequ.cmoc.common.Configuration;
import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.model.WechatSnsToken;
import com.xuequ.cmoc.model.WechatSnsUserInfo;
import com.xuequ.cmoc.model.WechatUserInfo;
import com.xuequ.cmoc.utils.HttpClientUtils;
import com.xuequ.cmoc.utils.JsonUtils;
import com.xuequ.cmoc.utils.StringUtil;
import com.xuequ.cmoc.utils.TextUtil;

/**
 * 微信utils
 * @author 胡启萌
 * @date 2016年12月1日
 */
public class WechatUtils {
	
	private static Logger logger = LoggerFactory.getLogger(WechatUtils.class);
	
	//公众号的全局唯一票据
	private final static String ACCESS_TOKEN = "access_token";
	//公众号用于调用微信JS-SDK接口的临时票据
	private final static String JSAPI_TICKET = "jsapi_ticket";
	
	public static WechatModel getWechatModel() {
		return new WechatModel(getAccessToken(), getJsapiTicket());
	}
	
	/**
	 * 获取access_token
	 * @return
	 */
	public static String getAccessToken() {
		WechatGlobalValue globalValue = WechatGlobalMap.get(ACCESS_TOKEN); 
		if(globalValue != null) {
			return globalValue.getValue();
		}
		return getToken(ACCESS_TOKEN);
	}
	
	/**
	 * 获取jsapi-ticket
	 * @return
	 */
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
			logger.info("获取的access_token>>" + json.toJSONString());
			if(json != null) {
				String access_token = json.getString("access_token");
				int expires_in = json.getIntValue("expires_in");
				WechatGlobalMap.put(ACCESS_TOKEN, access_token, expires_in);
				return access_token;
			}
		} catch (IOException e) {
			logger.error("--createAccessToken, error={}", e);
		}
		return null;
	}
	
	private static String createJsapiTicket(String accessToken) {
		WechatConfigure configure = WechatConfigure.getInstance();
		String url = TextUtil.format(configure.getJsapiTicket(), accessToken);
		try {
			JSONObject json = JSONObject.parseObject(HttpClientUtils.doGet(url));
			logger.info("获取的jsapi_ticket>>" + json.toJSONString());
			if(json != null) {
				String access_token = json.getString("ticket");
				int expires_in = json.getIntValue("expires_in");
				WechatGlobalMap.put(JSAPI_TICKET, access_token, expires_in);
				return access_token;
			}
		} catch (IOException e) {
			logger.error("--createJsapiTicket, error={}", e);
		}
		return null;
	}
	
	/**
	 * 获取用户基本信息
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	public static WechatUserInfo getUserInfo(String accessToken, String openid) {
		String url = TextUtil.format(WechatConfigure.getInstance().getUserInfo(), 
				new String[]{accessToken, openid});
		logger.info("--getUserInfo, url={}", url);
		try {
			String resultStr = HttpClientUtils.doGet(url);
			logger.info("--getUserInfo, result={}", resultStr);
			JSONObject jsonObject = JSONObject.parseObject(resultStr);
			if(StringUtils.isNotBlank(jsonObject.getString("errcode"))) return null;
			return JsonUtils.jsonToObject(jsonObject.toString(), WechatUserInfo.class);
		} catch (IOException e) {
			logger.error("--getUserInfo, error={}", e);
		}
		return null;
	}
	
	/**
	 * 获取用户授权的信息
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	public static WechatSnsUserInfo getSnsUserInfo(String accessToken, String openid) {
		String url = TextUtil.format(WechatConfigure.getInstance().getSnsUserInfo(), 
				new String[]{accessToken, openid});
		logger.info("--getSnsUserInfo, url={}", url);
		try {
			String resultStr = HttpClientUtils.doGet(url);
			logger.info("--getSnsUserInfo, result={}", resultStr);
			JSONObject jsonObject = JSONObject.parseObject(resultStr);
			if(StringUtils.isNotBlank(jsonObject.getString("errcode"))) return null;
			return JsonUtils.jsonToObject(jsonObject.toString(), WechatSnsUserInfo.class);
		} catch (IOException e) {
			logger.error("--getSnsUserInfo, error={}", e);
		}
		return null;
	}
	
	/**
	 * 根据授权code获取openid信息
	 * @param code
	 * @return
	 */
	public static WechatSnsToken getOpenidByCode(String code) {
		WechatConfigure configure = WechatConfigure.getInstance();
		String url = TextUtil.format(configure.getUserAccessToken(), 
				new String[]{configure.getAppid(), configure.getAppsecret(), code});
		logger.info("--getOpenidByCode, url={}", url);
		try {
			String resultStr = HttpClientUtils.doGet(url);
			logger.info("--getOpenidByCode, result={}", resultStr);
			JSONObject jsonObject = JSONObject.parseObject(resultStr);
			if(StringUtils.isNotBlank(jsonObject.getString("errcode"))) return null;
			return JsonUtils.jsonToObject(jsonObject.toString(), WechatSnsToken.class);
		} catch (IOException e) {
			logger.error("--getOpenidByCode, error={}", e);
		}
		return null;
	}
	
	public static WechatSnsToken getOpenidByRefreshToken(String refreshToken) {
		WechatConfigure configure = WechatConfigure.getInstance();
		String url = TextUtil.format(configure.getUserFefreshToken(), 
				new String[]{configure.getAppid(), refreshToken});
		logger.info("--getOpenidByFefreshToken, url={}", url);
		try {
			String resultStr = HttpClientUtils.doGet(url);
			logger.info("--getOpenidByFefreshToken, result={}", resultStr);
			JSONObject jsonObject = JSONObject.parseObject(resultStr);
			if(StringUtils.isNotBlank(jsonObject.getString("errcode"))) return null;
			return JsonUtils.jsonToObject(jsonObject.toString(), WechatSnsToken.class);
		} catch (IOException e) {
			logger.error("--getOpenidByFefreshToken, error={}", e);
		}
		return null;
	}
	
}
