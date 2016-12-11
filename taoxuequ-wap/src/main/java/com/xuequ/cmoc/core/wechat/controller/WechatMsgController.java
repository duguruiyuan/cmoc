package com.xuequ.cmoc.core.wechat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xuequ.cmoc.common.Const;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.controller.BaseController;
import com.xuequ.cmoc.core.wechat.common.Constants;
import com.xuequ.cmoc.core.wechat.template.Data_First;
import com.xuequ.cmoc.core.wechat.template.Data_Keyword;
import com.xuequ.cmoc.core.wechat.template.Data_Remark;
import com.xuequ.cmoc.core.wechat.template.OutputTemateData;
import com.xuequ.cmoc.core.wechat.template.TemplateDate;
import com.xuequ.cmoc.core.wechat.utils.WechatUtils;
import com.xuequ.cmoc.model.AuditReqVO;
import com.xuequ.cmoc.model.HollowManInfo;
import com.xuequ.cmoc.service.IHollowManService;
import com.xuequ.cmoc.utils.HttpClientUtils;
import com.xuequ.cmoc.utils.RequestUtil;
import com.xuequ.cmoc.utils.StringUtil;
import com.xuequ.cmoc.utils.TextUtil;

@RequestMapping("wechatmsg")
@Controller
public class WechatMsgController extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(WechatMsgController.class);

	@Autowired
	private IHollowManService hollowManService;
	
	@RequestMapping(value="hm/reg", method = RequestMethod.POST, consumes="application/json")  
    @ResponseBody Object hmReg(@RequestBody AuditReqVO vo) { 
		if(StringUtils.isBlank(Constants.BASEPATH)) Constants.BASEPATH = RequestUtil.getBasePath(request);
		try {
			if(vo != null) {
				List<Integer> list = new ArrayList<>();
				for(String string : vo.getIds().split(",")) {
					list.add(Integer.valueOf(string));
				}
				List<HollowManInfo> hmList = hollowManService.selectListByIds(list);
				if(!StringUtil.isNullOrEmpty(hmList) && vo.getStatus() == 1) {
					regMsgSend(hmList);
				}
				return new RspResult(StatusEnum.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("--hmReg, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
		
	}
	
	private static void regMsgSend(List<HollowManInfo> list) {
		String url = TextUtil.format(WechatConfigure.getInstance().getTemplateMsg(), 
				WechatUtils.getAccessToken());
		for(HollowManInfo info : list) {
			OutputTemateData outputData = new OutputTemateData();
			outputData.setTouser(info.getOpenid());
			outputData.setTemplate_id("NglAMte28uh8R-4Bj9wZRK3xIYZPk8IKCjJgaGkwYoI");
			TemplateDate templateDate = new TemplateDate();
			Data_First first = new Data_First();
			first.setValue(info.getHmName() + ",你好！恭喜你注册成功\n");
			Data_Keyword keyword1 = new Data_Keyword();
			keyword1.setValue(info.getHmName()+"\n");
			Data_Keyword keyword2 = new Data_Keyword();
			keyword2.setValue(info.getHmMobile() + "\n");
			Data_Remark remark = new Data_Remark();
			remark.setValue("点击详情进入个人中心申请带队吧\n\n欢迎使用陶学趣公众号");
			outputData.setUrl(Constants.BASEPATH + "/my");
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
				logger.error("--hm reg send, id={}, error={}", info.getId(), e);
			}
		}
	}
}
