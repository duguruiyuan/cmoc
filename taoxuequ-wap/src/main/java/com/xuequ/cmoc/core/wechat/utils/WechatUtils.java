package com.xuequ.cmoc.core.wechat.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.core.wechat.common.NewsTypeEnum;
import com.xuequ.cmoc.core.wechat.message.ArticleItem;
import com.xuequ.cmoc.core.wechat.message.MediaListReq;
import com.xuequ.cmoc.core.wechat.message.ShorturlReq;
import com.xuequ.cmoc.core.wechat.message.MaterialMedia.NewsItem;
import com.xuequ.cmoc.core.wechat.message.MaterialMedia.NewsMedia;
import com.xuequ.cmoc.core.wechat.message.MaterialMedia.NewsTotItem;
import com.xuequ.cmoc.core.wechat.model.WechatActionInfo;
import com.xuequ.cmoc.core.wechat.model.WechatQrcodeReq;
import com.xuequ.cmoc.core.wechat.model.WechatQrcodeRsp;
import com.xuequ.cmoc.core.wechat.model.WechatScene;
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
			return (String)globalValue.getValue();
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
			return (String)globalValue.getValue();
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
	
	public static WechatQrcodeRsp getQrcode(String qrcodeType, int type, String id) throws Exception{
		WechatModel model = WechatUtils.getWechatModel();
		String url = TextUtil.format(WechatConfigure.getInstance().getQrcodeTicket(), 
				model.getAccessToken());
		WechatQrcodeReq req = new WechatQrcodeReq();
		WechatActionInfo info = new WechatActionInfo();
		WechatScene scene = new WechatScene();
		Long time = DateTypeUtils.getSceneId(type, id);
		scene.setScene_id(time);
		info.setScene(scene);
		req.setAction_info(info);
		if(MessageUtil.QR_SCENE.equals(qrcodeType)) {
			req.setExpire_seconds(2592000L);
		}
		req.setAction_name(qrcodeType);
		String response = HttpClientUtils.postJson(url, req);
		logger.info("---getQrcode result={}", response);
		JSONObject jsonObject = JSONObject.parseObject(response);
		if(jsonObject.getString("errcode") == null) {
			return JsonUtils.jsonToObject(response, WechatQrcodeRsp.class);
		}
		return null;
	}
	
	/**
	 * 获取图文素材
	 * @return
	 */
	public static List<ArticleItem> getMaterialMediaList(String type) {
		try {
			WechatGlobalValue globalValue = WechatGlobalMap.get(type); 
			if(globalValue != null && globalValue.getValue() != null) {
				return (List<ArticleItem>) globalValue.getValue();
			}
			String url = TextUtil.format(WechatConfigure.getInstance().getMediaCount(), 
					getWechatModel().getAccessToken());
			String resultStr = HttpClientUtils.doGet(url);
			JSONObject jsonObject = JSONObject.parseObject(resultStr);
			int newsCount = jsonObject.getIntValue("news_count");
			int page = (int)Math.ceil(Double.valueOf(newsCount)/Double.valueOf(19));
			List<ArticleItem> newsInfoList = new ArrayList<>();
			List<ArticleItem> schoolCaseList = new ArrayList<>();
			List<ArticleItem> followTweetList = new ArrayList<>();
			List<ArticleItem> aboutUsList = new ArrayList<>();
			for(int i = page; i >= 1; i--) {
				url = TextUtil.format(WechatConfigure.getInstance().getMediaList(), 
						getWechatModel().getAccessToken());
				MediaListReq reqParam = new MediaListReq();
				reqParam.setCount(19);
				reqParam.setOffset((page * 19 - 19*(page-i+1)));
				resultStr = HttpClientUtils.postJson(url, reqParam);
				NewsMedia newsMedia = JsonUtils.jsonToObject(resultStr, NewsMedia.class);
				for(NewsTotItem totItem : newsMedia.getItem()) {
					List<NewsItem> list = totItem.getContent().getNews_item();
					if(!StringUtil.isNullOrEmpty(list) && list.size() == 1) {
						NewsItem newsItem = list.get(0);
						ArticleItem item = new ArticleItem();
						item.setTitle(newsItem.getTitle());
				        item.setDescription(newsItem.getDigest());
				        item.setPicUrl(newsItem.getThumb_url());
				        String author = newsItem.getAuthor();
				        author = StringUtils.isNotBlank(author) ? author : "";
				        if(author.equals(NewsTypeEnum.NEWS_INFO.getDesc()) && newsInfoList.size() < 5) {
				        	item.setUrl(getShortUrl(newsItem.getUrl()));
				        	newsInfoList.add(item);
				        	continue;
				        }
				        if(author.equals(NewsTypeEnum.SCHOOL_CASE.getDesc()) && schoolCaseList.size() < 5) {
				        	item.setUrl(getShortUrl(newsItem.getUrl()));
				        	schoolCaseList.add(item);
				        	continue;
				        }
				        if(author.equals(NewsTypeEnum.FOLLOW_TWEET.getDesc()) && followTweetList.size() < 3) {
				        	item.setUrl(getShortUrl(newsItem.getUrl()));
				        	followTweetList.add(item);
				        	continue;
				        }
				        if(author.equals(NewsTypeEnum.ABOUT_US.getDesc())) {
				        	item.setUrl(getShortUrl(newsItem.getUrl()));
				        	aboutUsList.add(item);
				        	continue;
				        }
					}
				}
			}
			WechatGlobalMap.put(NewsTypeEnum.NEWS_INFO.getCode(), newsInfoList, 24*60*60);
			WechatGlobalMap.put(NewsTypeEnum.SCHOOL_CASE.getCode(), schoolCaseList, 24*60*60);
			WechatGlobalMap.put(NewsTypeEnum.FOLLOW_TWEET.getCode(), followTweetList, 24*60*60);
			WechatGlobalMap.put(NewsTypeEnum.ABOUT_US.getCode(), aboutUsList, 24*60*60);
			if(type.equals(NewsTypeEnum.NEWS_INFO.getCode())) {
	        	return newsInfoList;
	        } else if(type.equals(NewsTypeEnum.SCHOOL_CASE.getCode())) {
	        	return schoolCaseList;
	        } else if(type.equals(NewsTypeEnum.FOLLOW_TWEET.getCode())) {
	        	return followTweetList;
	        } else if(type.equals(NewsTypeEnum.ABOUT_US.getCode())) {
	        	return aboutUsList;
	        }
		} catch (IOException e) {
			logger.error("--getOpenidByFefreshToken, error={}", e);
		}
		return new ArrayList<>();
	}
	
	/**
	 * 获取短链接
	 * @param longUrl
	 * @return
	 */
	public static String getShortUrl(String longUrl){
		try {
			WechatModel model = WechatUtils.getWechatModel();
			String url = TextUtil.format(WechatConfigure.getInstance().getLongToShort(), 
					model.getAccessToken());
			ShorturlReq req = new ShorturlReq();
			req.setLong_url(longUrl);
			String response = HttpClientUtils.postJson(url, req);
			JSONObject jsonObject = JSONObject.parseObject(response);
			return jsonObject.getString("short_url");
		} catch (Exception e) {
			logger.error("--getShortUrl,error={}", e);
		}
		return null;
	}
	
}
