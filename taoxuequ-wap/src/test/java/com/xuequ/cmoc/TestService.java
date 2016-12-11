package com.xuequ.cmoc;

import com.alibaba.fastjson.JSONObject;
import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.core.wechat.template.Data_Add;
import com.xuequ.cmoc.core.wechat.template.Data_Clazz;
import com.xuequ.cmoc.core.wechat.template.Data_First;
import com.xuequ.cmoc.core.wechat.template.Data_Keyword;
import com.xuequ.cmoc.core.wechat.template.Data_Remark;
import com.xuequ.cmoc.core.wechat.template.Data_Time;
import com.xuequ.cmoc.core.wechat.template.OutputTemateData;
import com.xuequ.cmoc.core.wechat.template.TemplateDate;
import com.xuequ.cmoc.utils.HttpClientUtils;
import com.xuequ.cmoc.utils.TextUtil;

public class TestService {

	public static void main(String[] args) {
		String url = TextUtil.format(WechatConfigure.getInstance().getTemplateMsg(), "GMs0UihDEQIs9zMZ2JR3OvTdg-pJ_yzdQkCRN7TfYtYTiAaABJa2LGppaKzWej5XHBj9BDhRIl-C4S8rYr7Ce7fhTyoILd_KjtvFv4VOXHN86VkIwA1E-tXmXkOC_mmKBAHcABAGFM");
//		OutputTemateData outputData = regMsg();
		OutputTemateData outputData = sign();
		try {
			String json = JSONObject.toJSONString(outputData);
			json = json.replace("clazz", "class");
			String result = HttpClientUtils.postStringJosn(url, json);
			System.out.println(result);
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
