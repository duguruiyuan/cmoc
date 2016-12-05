package com.xuequ.cmoc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.core.wechat.message.OutputMessage;
import com.xuequ.cmoc.core.wechat.utils.MessageUtil;
import com.xuequ.cmoc.model.ActivityHmSign;
import com.xuequ.cmoc.service.IActivityHmService;
import com.xuequ.cmoc.service.IActivityMarinesService;
import com.xuequ.cmoc.service.IKeywordService;
import com.xuequ.cmoc.service.IWechatMessageService;
import com.xuequ.cmoc.utils.DateUtil;
import com.xuequ.cmoc.utils.PropertiesUtil;
import com.xuequ.cmoc.utils.TextUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class AutoService {
	@Autowired
	private IActivityMarinesService activityMarinesService;
	@Autowired
	private IKeywordService keywordService;
	@Autowired
	private IActivityHmService activityHmService;
	@Autowired
	private IWechatMessageService wechatMessageService;
	@Test
	public void test() {
		try {
			String eventKey = "1015";
			String openid = "oqyqUwq_YY84qjFWUtn6Ti4XIROE";
			OutputMessage outputMsg = new OutputMessage();
			int type = Integer.parseInt(eventKey.substring(0, eventKey.indexOf("0"))); 
			if(MessageUtil.BIND_TYPE_MARINE == type) {
				Integer marineId = Integer.parseInt(eventKey.substring(eventKey.indexOf("0"), eventKey.length())); 
				RspResult rspResult = activityMarinesService.updateHmBindMarine(marineId, openid);
				String content = null;
				if(StatusEnum.SUCCESS.getCode().equals(rspResult.getCode())) {
					content = PropertiesUtil.getProperty("hm_regiter_success");
					ActivityHmSign hmSign = (ActivityHmSign) rspResult.getData();
					String url = "/hm/manage/marine?mid=" + hmSign.getActivityId() + "&hid=" + hmSign.getHmId();
					String[] strings = new String[]{hmSign.getActivityName(), hmSign.getHmName(), 
							hmSign.getMarineName(), String.valueOf(hmSign.getMarineId()), 
							DateUtil.dateToStr(hmSign.getUpdateTime(), DateUtil.DEFAULT_DATE_FORMAT), url};
					content = TextUtil.format(content, strings);
				}else {
					content = "提示绑定失败：" + rspResult.getMsg();
				}
				outputMsg.setContent(content);
				outputMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
