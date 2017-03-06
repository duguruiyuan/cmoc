package com.xuequ.cmoc;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.core.wechat.common.NewsTypeEnum;
import com.xuequ.cmoc.core.wechat.message.ArticleItem;
import com.xuequ.cmoc.core.wechat.message.MediaListReq;
import com.xuequ.cmoc.core.wechat.message.MaterialMedia.NewsItem;
import com.xuequ.cmoc.core.wechat.message.MaterialMedia.NewsMedia;
import com.xuequ.cmoc.core.wechat.message.MaterialMedia.NewsTotItem;
import com.xuequ.cmoc.core.wechat.utils.WechatGlobalMap;
import com.xuequ.cmoc.core.wechat.utils.WechatGlobalValue;
import com.xuequ.cmoc.utils.HttpClientUtils;
import com.xuequ.cmoc.utils.JsonUtils;
import com.xuequ.cmoc.utils.MD5Util;
import com.xuequ.cmoc.utils.StringUtil;
import com.xuequ.cmoc.utils.TextUtil;

public class TestUnit {

	public static void main(String[] args) throws IOException {

		String type = NewsTypeEnum.NEWS_INFO.getCode();
		WechatGlobalValue globalValue = WechatGlobalMap.get(type); 
		if(globalValue != null) {
			System.out.println("not null");
		}
		String url = TextUtil.format(WechatConfigure.getInstance().getMediaCount(), 
				"8y6GNw-BPq-_LI4z3bkWVw4SPCHydL-PqHzU5NiopLtKEcxGqJott82WuojznG6kgbqdXT4n4Ic9tFPRFKs7gEZEVdKBGX3ZSSMUCt4wJQa5ZYls2U9Fx3aReM3BvLC_OFSjAJACTG");
		String resultStr = HttpClientUtils.doGet(url);
		JSONObject jsonObject = JSONObject.parseObject(resultStr);
		int newsCount = jsonObject.getIntValue("news_count");
		int page = (int)Math.ceil(Double.valueOf(newsCount)/Double.valueOf(19));
		List<ArticleItem> newsInfoList = new ArrayList<>();
		List<ArticleItem> schoolCaseList = new ArrayList<>();
		List<ArticleItem> followTweetList = new ArrayList<>();
		for(int i = page; i >= 1; i--) {
			url = TextUtil.format(WechatConfigure.getInstance().getMediaList(), 
					"8y6GNw-BPq-_LI4z3bkWVw4SPCHydL-PqHzU5NiopLtKEcxGqJott82WuojznG6kgbqdXT4n4Ic9tFPRFKs7gEZEVdKBGX3ZSSMUCt4wJQa5ZYls2U9Fx3aReM3BvLC_OFSjAJACTG");
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
			        	newsInfoList.add(item);
			        	continue;
			        }
			        if(author.equals(NewsTypeEnum.SCHOOL_CASE.getDesc()) && schoolCaseList.size() < 5) {
			        	schoolCaseList.add(item);
			        	continue;
			        }
			        if(author.equals(NewsTypeEnum.FOLLOW_TWEET.getDesc()) && followTweetList.size() < 3) {
			        	followTweetList.add(item);
			        	continue;
			        }
				}
			}
		}
		WechatGlobalMap.put(NewsTypeEnum.NEWS_INFO.getCode(), newsInfoList, 24*60*60);
		WechatGlobalMap.put(NewsTypeEnum.SCHOOL_CASE.getCode(), schoolCaseList, 24*60*60);
		WechatGlobalMap.put(NewsTypeEnum.FOLLOW_TWEET.getCode(), followTweetList, 24*60*60);
		if(type.equals(NewsTypeEnum.NEWS_INFO.getCode())) {
        	System.out.println(JSONObject.toJSONString(newsInfoList));
        } else if(type.equals(NewsTypeEnum.SCHOOL_CASE.getCode())) {
        	System.out.println(JSONObject.toJSONString(schoolCaseList));
        } else if(type.equals(NewsTypeEnum.FOLLOW_TWEET.getCode())) {
        	System.out.println(JSONObject.toJSONString(followTweetList));
        }
		
	}

}
