package com.xuequ.cmoc;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.xuequ.cmoc.core.wechat.message.ArticleItem;
import com.xuequ.cmoc.core.wechat.message.MediaListReq;
import com.xuequ.cmoc.core.wechat.message.MaterialMedia.NewsItem;
import com.xuequ.cmoc.core.wechat.message.MaterialMedia.NewsMedia;
import com.xuequ.cmoc.core.wechat.message.MaterialMedia.NewsTotItem;
import com.xuequ.cmoc.core.wechat.template.Data_Add;
import com.xuequ.cmoc.core.wechat.template.Data_Clazz;
import com.xuequ.cmoc.core.wechat.template.Data_First;
import com.xuequ.cmoc.core.wechat.template.Data_Keyword;
import com.xuequ.cmoc.core.wechat.template.Data_Remark;
import com.xuequ.cmoc.core.wechat.template.Data_Time;
import com.xuequ.cmoc.core.wechat.template.OutputTemateData;
import com.xuequ.cmoc.core.wechat.template.TemplateDate;
import com.xuequ.cmoc.core.wechat.utils.WechatUtils;
import com.xuequ.cmoc.utils.HttpClientUtils;
import com.xuequ.cmoc.utils.JsonUtils;
import com.xuequ.cmoc.utils.StringUtil;
import com.xuequ.cmoc.utils.TextUtil;

public class TestService {

	public static void main(String[] args) {
		
		BigDecimal num = new BigDecimal("3844000000000");
		String str = new DecimalFormat("地球和月球的距离：#,##,###,###米").format(num);
		System.out.println(str);
	}
	
	public static void getMedia() {
		try {
			List<ArticleItem> newsList = new ArrayList<>();
			String acc = "_CFAoCZxtVKojwjM4W8HuU7XJiAxaxXzKCZ2haWjCidqE98CR6jC2SzWzXtN3nPCf9eqj1pva10irP4Ge8SDXQXIU7GdmyQszj9EG-yGRuKJzAxml67yhnQKEPSwA0gLPRHbAFAYAQ";//WechatUtils.getWechatModel().getAccessToken();
			String url = TextUtil.format("https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token={0}", acc);
			String result = HttpClientUtils.postJson(url, new MediaListReq());
			NewsMedia newsMedia = JsonUtils.jsonToObject(result, NewsMedia.class);
			for(NewsTotItem totItem : newsMedia.getItem()) {
				List<NewsItem> list = totItem.getContent().getNews_item();
				if(!StringUtil.isNullOrEmpty(list) && list.size() == 1) {
					NewsItem newsItem = list.get(0);
					ArticleItem item = new ArticleItem();
					item.setTitle(newsItem.getTitle());
			        item.setDescription(newsItem.getDigest());
			        item.setPicUrl(newsItem.getThumb_url());
			        item.setUrl(WechatUtils.getShortUrl(newsItem.getUrl()));
			        newsList.add(item);
				}
			}
			System.out.println(JSONObject.toJSONString(newsList));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static OutputTemateData sign() {
		OutputTemateData outputData = new OutputTemateData();
		outputData.setTouser("oqyqUwq_YY84qjFWUtn6Ti4XIROE");
		outputData.setTemplate_id("H_vbGJctM_HBwxTUnyaoWD6JWJdBdYBTi0Do_s9PSWo");
		TemplateDate templateDate = new TemplateDate();
		Data_First first = new Data_First();
		first.setValue("恭喜你报名成功\n欢迎你来参加创客大赛活动");
		Data_Clazz clazz  = new Data_Clazz();
		clazz.setValue("佛山创客大赛");
		Data_Time time = new Data_Time();
		time.setValue("12月1号-1月9号");
		Data_Add add = new Data_Add();
		add.setValue("佛山创客大厦一楼\n\n");
		outputData.setData(templateDate);
		Data_Remark remark = new Data_Remark();
		remark.setValue("欢迎使用陶学趣公众号");
		templateDate.setClazz(clazz);
		templateDate.setFirst(first);
		templateDate.setTime(time);
		templateDate.setRemark(remark);
		templateDate.setAdd(add);
		return outputData;
	}
	
	public static OutputTemateData regMsg() {
		OutputTemateData outputData = new OutputTemateData();
		outputData.setTouser("oqyqUwq_YY84qjFWUtn6Ti4XIROE");
		outputData.setTemplate_id("NglAMte28uh8R-4Bj9wZRK3xIYZPk8IKCjJgaGkwYoI");
		TemplateDate templateDate = new TemplateDate();
		Data_First first = new Data_First();
		first.setValue("尊敬的胡启萌,你好！恭喜你注册成功\n");
		Data_Keyword keyword1 = new Data_Keyword();
		keyword1.setValue("胡启萌\n");
		Data_Keyword keyword2 = new Data_Keyword();
		keyword2.setValue("13681984045\n\n");
		Data_Remark remark = new Data_Remark();
		remark.setValue("欢迎使用陶学趣公众号");
		templateDate.setFirst(first);
		templateDate.setKeyword1(keyword1);
		templateDate.setKeyword2(keyword2);
		templateDate.setRemark(remark);
		outputData.setData(templateDate);
		return outputData;
	}
}
