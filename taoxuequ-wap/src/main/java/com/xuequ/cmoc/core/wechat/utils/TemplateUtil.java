package com.xuequ.cmoc.core.wechat.utils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.core.wechat.common.Constants;
import com.xuequ.cmoc.core.wechat.template.Data_Add;
import com.xuequ.cmoc.core.wechat.template.Data_Clazz;
import com.xuequ.cmoc.core.wechat.template.Data_First;
import com.xuequ.cmoc.core.wechat.template.Data_Remark;
import com.xuequ.cmoc.core.wechat.template.Data_Time;
import com.xuequ.cmoc.core.wechat.template.OutputTemateData;
import com.xuequ.cmoc.core.wechat.template.TemplateDate;
import com.xuequ.cmoc.utils.DateUtil;
import com.xuequ.cmoc.utils.HttpClientUtils;
import com.xuequ.cmoc.utils.PropertiesUtil;
import com.xuequ.cmoc.utils.TextUtil;

public class TemplateUtil {
	static Logger logger = LoggerFactory.getLogger(TemplateUtil.class);
	
	public static void activitySignSucessMsg(String orderNo, String activityAddr, String openid, String emerName, String activityName, Date startDate) {
		String url = TextUtil.format(WechatConfigure.getInstance().getTemplateMsg(), 
				WechatUtils.getAccessToken());
		OutputTemateData outputData = new OutputTemateData();
		outputData.setTouser(openid);
		outputData.setTemplate_id(PropertiesUtil.getProperty("activity_sign_sucess_template_id"));
		TemplateDate templateDate = new TemplateDate();
		Data_First first = new Data_First();
		first.setValue(emerName + "家长, 您已经成功提交报名申请：");
		Data_Clazz clazz  = new Data_Clazz();
		clazz.setValue(activityName);
		Data_Time time = new Data_Time();
		time.setValue(DateUtil.dateToStr(startDate, DateUtil.DEFAULT_DATE_FORMAT1));
		Data_Add add = new Data_Add();
		add.setValue(activityAddr);
		outputData.setData(templateDate);
		Data_Remark remark = new Data_Remark();
		remark.setValue("***点击“详情”立即进入队伍管理，邀请队员并完成支付***");
		outputData.setUrl(Constants.BASEPATH + "/course/add?oNo=" + orderNo);
		templateDate.setClazz(clazz);
		templateDate.setFirst(first);
		templateDate.setTime(time);
		templateDate.setRemark(remark);
		templateDate.setAdd(add);
		try {
			String json = JSONObject.toJSONString(outputData);
			json = json.replace("clazz", "class");
			HttpClientUtils.postStringJosn(url, json);
		} catch (Exception e) {
			logger.error("--activitySignSucessMsg, error={}", e);
		}
	}
	
	public static void memberAccessMsg(String orderNo, String openid, String emerName, Integer member) {
		String url = TextUtil.format(WechatConfigure.getInstance().getTemplateMsg(), 
				WechatUtils.getAccessToken());
		OutputTemateData outputData = new OutputTemateData();
		outputData.setTouser(openid);
		outputData.setTemplate_id(PropertiesUtil.getProperty("member_access_template_id"));
		TemplateDate templateDate = new TemplateDate();
		Data_First first = new Data_First();
		first.setValue("亲爱的家长, 您的队伍有新成员加入，目前队伍成员【" + member +"】人。");
		Data_Clazz clazz  = new Data_Clazz();
		clazz.setValue(emerName);
		Data_Time time = new Data_Time();
		time.setValue(DateUtil.dateToStr(new Date(), DateUtil.DEFAULT_DATE_FORMAT));
		outputData.setData(templateDate);
		Data_Remark remark = new Data_Remark();
		remark.setValue("***欢迎使用陶学趣公众号***\n↓↓↓点击【详情】进入队伍管理↓↓↓");
		outputData.setUrl(Constants.BASEPATH + "/course/add?oNo=" + orderNo);
		templateDate.setClazz(clazz);
		templateDate.setFirst(first);
		templateDate.setTime(time);
		templateDate.setRemark(remark);
		try {
			String json = JSONObject.toJSONString(outputData);
			json = json.replace("clazz", "class");
			HttpClientUtils.postStringJosn(url, json);
		} catch (Exception e) {
			logger.error("--activitySignSucessMsg, error={}", e);
		}
	}
	
	public static void accessSucessMsg(Integer childId, String marginName, String openid, String emerName, String orderNo) {
		String url = TextUtil.format(WechatConfigure.getInstance().getTemplateMsg(), 
				WechatUtils.getAccessToken());
		OutputTemateData outputData = new OutputTemateData();
		outputData.setTouser(openid);
		outputData.setTemplate_id(PropertiesUtil.getProperty("member_access_template_id"));
		TemplateDate templateDate = new TemplateDate();
		Data_First first = new Data_First();
		first.setValue("您已经加入<" + marginName +">的队伍。");
		Data_Clazz clazz  = new Data_Clazz();
		clazz.setValue(emerName);
		Data_Time time = new Data_Time();
		time.setValue(DateUtil.dateToStr(new Date(), DateUtil.DEFAULT_DATE_FORMAT));
		outputData.setData(templateDate);
		Data_Remark remark = new Data_Remark();
		remark.setValue("***欢迎使用陶学趣公众号***\n↓↓↓点击【详情】进入报名信息管理↓↓↓");
		outputData.setUrl(Constants.BASEPATH + "/course/add?oNo=" + orderNo + "&cId=" + childId);
		templateDate.setClazz(clazz);
		templateDate.setFirst(first);
		templateDate.setTime(time);
		templateDate.setRemark(remark);
		try {
			String json = JSONObject.toJSONString(outputData);
			json = json.replace("clazz", "class");
			HttpClientUtils.postStringJosn(url, json);
		} catch (Exception e) {
			logger.error("--activitySignSucessMsg, error={}", e);
		}
	}

}
