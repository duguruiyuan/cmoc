package com.xuequ.cmoc.core.wechat.utils;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.common.enums.OrderStatusEnum;
import com.xuequ.cmoc.core.wechat.common.Constants;
import com.xuequ.cmoc.core.wechat.template.Data_Clazz;
import com.xuequ.cmoc.core.wechat.template.Data_First;
import com.xuequ.cmoc.core.wechat.template.Data_Keyword;
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
	
	/**
	 * 提交活动报名申请
	 * @param orderNo
	 * @param activityAddr
	 * @param openid
	 * @param emerName
	 * @param activityName
	 * @param startDate
	 */
	public static void activitySignSucessMsg(String orderNo, String activityAddr, String openid, String emerName, String activityName, Date startDate) {
		String url = TextUtil.format(WechatConfigure.getInstance().getTemplateMsg(), 
				WechatUtils.getAccessToken());
		OutputTemateData outputData = new OutputTemateData();
		outputData.setTouser(openid);
		outputData.setTemplate_id(PropertiesUtil.getProperty("activity_sign_sucess_template_id"));
		TemplateDate templateDate = new TemplateDate();
		Data_First first = new Data_First();
		first.setValue(emerName + "家长, 您已经成功提交报名申请。订单编号" + orderNo);
		
		Data_Keyword keyword1 = new Data_Keyword();
		keyword1.setValue(activityName);
		Data_Keyword keyword2 = new Data_Keyword();
		keyword2.setValue(DateUtil.dateToStr(startDate, DateUtil.DEFAULT_DATE_FORMAT1));
		Data_Keyword keyword3 = new Data_Keyword();
		keyword3.setValue(activityAddr);
		
		Data_Remark remark = new Data_Remark();
		remark.setValue("❀❀❀点击“详情”立即进入队伍管理，邀请队员并完成支付❀❀❀");
		outputData.setUrl(Constants.BASEPATH + "/course/group/add/" + orderNo);
		templateDate.setFirst(first);
		templateDate.setRemark(remark);
		templateDate.setKeyword1(keyword1);
		templateDate.setKeyword2(keyword2);
		templateDate.setKeyword3(keyword3);
		outputData.setData(templateDate);
		try {
			String json = JSONObject.toJSONString(outputData);
			json = json.replace("clazz", "class");
			HttpClientUtils.postStringJosn(url, json);
		} catch (Exception e) {
			logger.error("--activitySignSucessMsg, error={}", e);
		}
	}
	
	/**
	 * 
	 * @param orderNo
	 * @param activityAddr
	 * @param openid
	 * @param emerName
	 * @param activityName
	 * @param startDate
	 */
	public static void activitySignSucessMsgToCustomer(String orderNo, String emerMobile, 
			String emerName, String activityName, String activityNum, Date startDate) {
		String url = TextUtil.format(WechatConfigure.getInstance().getTemplateMsg(), 
				WechatUtils.getAccessToken());
		OutputTemateData outputData = new OutputTemateData();
		outputData.setTouser(PropertiesUtil.getProperty("customer_service_openid"));
		outputData.setTemplate_id(PropertiesUtil.getProperty("course_sign_success_template_id"));
		TemplateDate templateDate = new TemplateDate();
		Data_First first = new Data_First();
		first.setValue("有新的客户预约，订单编号" + orderNo + "，请及时确认");
		
		Data_Keyword keyword1 = new Data_Keyword();
		keyword1.setColor("#173177");
		keyword1.setValue(emerName);
		Data_Keyword keyword2 = new Data_Keyword();
		keyword2.setColor("#173177");
		keyword2.setValue(emerMobile);
		Data_Keyword keyword3 = new Data_Keyword();
		keyword3.setValue(DateUtil.dateToStr(new Date(), DateUtil.DEFAULT_DATE_FORMAT));
		Data_Keyword keyword4 = new Data_Keyword();
		keyword4.setColor("#173177");
		keyword4.setValue(activityName + "|" + activityNum + "|" + DateUtil.dateToStr(startDate, DateUtil.DATE_FORMAT1));
		
		Data_Remark remark = new Data_Remark();
		remark.setValue("❀❀❀每天好心情，加油 加油 加油！❀❀❀");
		templateDate.setFirst(first);
		templateDate.setRemark(remark);
		templateDate.setKeyword1(keyword1);
		templateDate.setKeyword2(keyword2);
		templateDate.setKeyword3(keyword3);
		templateDate.setKeyword4(keyword4);
		outputData.setData(templateDate);
		try {
			String json = JSONObject.toJSONString(outputData);
			json = json.replace("clazz", "class");
			HttpClientUtils.postStringJosn(url, json);
		} catch (Exception e) {
			logger.error("--activitySignSucessMsg, error={}", e);
		}
	}
	
	/**
	 * 成员加入提醒
	 * @param orderNo
	 * @param openid
	 * @param emerName
	 * @param member
	 */
	public static void memberAccessMsg(String orderNo, String orderStatus, String openid, 
			String emerName, Integer member) {
		String url = TextUtil.format(WechatConfigure.getInstance().getTemplateMsg(), 
				WechatUtils.getAccessToken());
		OutputTemateData outputData = new OutputTemateData();
		outputData.setTouser(openid);
		outputData.setTemplate_id(PropertiesUtil.getProperty("member_access_template_id"));
		TemplateDate templateDate = new TemplateDate();
		Data_First first = new Data_First();
		StringBuffer sbr = new StringBuffer();
		if(member == 5) {
			sbr.append("亲爱的家长, 您的队伍人数已满，队伍组建成功了！");
		}else {
			sbr.append("亲爱的家长, 您的队伍有新成员加入，目前队伍成员【" + member +"】人\n");
		}
		if(orderStatus.equals(OrderStatusEnum.PENDING.getCode())) {
			sbr.append("您还未支付，名额有限！您可以公众号回复“客服”联系客服进行支付,谢谢！");
		}
		first.setValue(sbr.toString());
		Data_Keyword keyword1 = new Data_Keyword();
		keyword1.setColor("#173177");
		keyword1.setValue(emerName);
		Data_Keyword keyword2 = new Data_Keyword();
		keyword2.setColor("#173177");
		keyword2.setValue(DateUtil.dateToStr(new Date(), DateUtil.DEFAULT_DATE_FORMAT));
		Data_Remark remark = new Data_Remark();
		remark.setValue("❀❀❀欢迎使用陶学趣公众号***\n↓↓↓点击【详情】进入队伍管理❀❀❀");
		outputData.setUrl(Constants.BASEPATH + "/course/group/add/" + orderNo);
		templateDate.setFirst(first);
		templateDate.setKeyword1(keyword1);
		templateDate.setKeyword2(keyword2);
		templateDate.setRemark(remark);
		outputData.setData(templateDate);
		try {
			String json = JSONObject.toJSONString(outputData);
			json = json.replace("clazz", "class");
			HttpClientUtils.postStringJosn(url, json);
		} catch (Exception e) {
			logger.error("--activitySignSucessMsg, error={}", e);
		}
	}
	
	/**
	 * 订单支付成功提醒
	 * @param orderNo
	 * @param openid
	 * @param emerName
	 * @param member
	 */
	public static void courseOrderPaySucessMsg(Date payDate, String orderNo, String openid, String emerName, String activityName, String activityNum, Date startDate, BigDecimal price) {
		String url = TextUtil.format(WechatConfigure.getInstance().getTemplateMsg(), 
				WechatUtils.getAccessToken());
		OutputTemateData outputData = new OutputTemateData();
		outputData.setTouser(openid);
		outputData.setTemplate_id(PropertiesUtil.getProperty("order_success_template_id"));
		TemplateDate templateDate = new TemplateDate();
		Data_First first = new Data_First();
		first.setValue("亲爱的" + emerName + "家长, 您已经支付成功。");
		Data_Keyword keyword1 = new Data_Keyword();
		keyword1.setColor("#173177");
		keyword1.setValue(orderNo);
		Data_Keyword keyword2 = new Data_Keyword();
		keyword2.setColor("#173177");
		keyword2.setValue(activityName + "|" + activityNum + "|" + DateUtil.dateToStr(new Date(), DateUtil.DATE_FORMAT1));
		Data_Keyword keyword3 = new Data_Keyword();
		keyword3.setColor("#173177");
		keyword3.setValue("人民币" + price + "元");
		Data_Keyword keyword4 = new Data_Keyword();
		keyword4.setColor("#173177");
		keyword4.setValue(DateUtil.dateToStr(payDate, DateUtil.DEFAULT_DATE_FORMAT2));
		Data_Remark remark = new Data_Remark();
		remark.setValue("如果您有什么问题，请直接拨打18027274621或者直接在陶学趣公众号回复“客服”会有客户人员来解答。谢谢！");
		templateDate.setFirst(first);
		templateDate.setKeyword1(keyword1);
		templateDate.setKeyword2(keyword2);
		templateDate.setKeyword3(keyword3);
		templateDate.setKeyword4(keyword4);
		templateDate.setRemark(remark);
		outputData.setData(templateDate);
		try {
			String json = JSONObject.toJSONString(outputData);
			json = json.replace("clazz", "class");
			HttpClientUtils.postStringJosn(url, json);
		} catch (Exception e) {
			logger.error("--courseOrderPaySucessMsg, error={}", e);
		}
	}
	
	public static void accessSucessMsg(Integer childId, String marineName, String openid, String emerName, String orderNo) {
		String url = TextUtil.format(WechatConfigure.getInstance().getTemplateMsg(), 
				WechatUtils.getAccessToken());
		OutputTemateData outputData = new OutputTemateData();
		outputData.setTouser(openid);
		outputData.setTemplate_id(PropertiesUtil.getProperty("member_access_template_id"));
		TemplateDate templateDate = new TemplateDate();
		Data_First first = new Data_First();
		first.setValue("您已经加入<" + marineName +">的队伍。");
		Data_Clazz clazz  = new Data_Clazz();
		clazz.setValue(emerName);
		Data_Time time = new Data_Time();
		time.setValue(DateUtil.dateToStr(new Date(), DateUtil.DEFAULT_DATE_FORMAT));
		outputData.setData(templateDate);
		Data_Remark remark = new Data_Remark();
		remark.setValue("❀❀❀欢迎使用陶学趣公众号❀❀❀\n↓↓↓点击【详情】进入报名信息管理↓↓↓");
		outputData.setUrl(Constants.BASEPATH + "/course/group/merber?oNo=" + orderNo + "&cId=" + childId);
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
