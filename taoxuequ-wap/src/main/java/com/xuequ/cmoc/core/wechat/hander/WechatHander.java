package com.xuequ.cmoc.core.wechat.hander;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuequ.cmoc.common.Const;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.common.enums.WechatReqMsgType;
import com.xuequ.cmoc.core.wechat.common.Constants;
import com.xuequ.cmoc.core.wechat.message.ArticleItem;
import com.xuequ.cmoc.core.wechat.message.InputMessage;
import com.xuequ.cmoc.core.wechat.message.OutputMessage;
import com.xuequ.cmoc.core.wechat.utils.FileUtil;
import com.xuequ.cmoc.core.wechat.utils.MessageUtil;
import com.xuequ.cmoc.model.ActivityHmSign;
import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.model.WechatReceiveMessage;
import com.xuequ.cmoc.model.WechatSendMessage;
import com.xuequ.cmoc.service.IActivityHmService;
import com.xuequ.cmoc.service.IActivityMarinesService;
import com.xuequ.cmoc.service.IActivityService;
import com.xuequ.cmoc.service.IKeywordService;
import com.xuequ.cmoc.service.IWechatMessageService;
import com.xuequ.cmoc.utils.BeanUtils;
import com.xuequ.cmoc.utils.DateUtil;
import com.xuequ.cmoc.utils.PropertiesUtil;
import com.xuequ.cmoc.utils.StringUtil;
import com.xuequ.cmoc.utils.TextUtil;

@Component
public class WechatHander {
	
	@Autowired
	private IActivityMarinesService activityMarinesService;
	@Autowired
	private IKeywordService keywordService;
	@Autowired
	private IActivityHmService activityHmService;
	@Autowired
	private IWechatMessageService wechatMessageService;
	@Autowired
	private IActivityService activityService;

	public OutputMessage respMessage(InputMessage inputMsg) throws Exception{
		String servername = inputMsg.getToUserName();// 服务端  
        String custermname = inputMsg.getFromUserName();// 客户端  
        Long returnTime = Calendar.getInstance().getTimeInMillis() / 1000;// 返回时间  
        
        // 取得消息类型  
        String msgType = inputMsg.getMsgType();  
        OutputMessage outputMsg = new OutputMessage();  
        outputMsg.setFromUserName(servername);  
        outputMsg.setToUserName(custermname);  
        outputMsg.setCreateTime(returnTime);  
        String respContent = null;
        // 根据消息类型获取对应的消息内容  
        if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
        	reqMessageTypeTextOutput(outputMsg, inputMsg);
        } // 获取并返回多图片消息  
        else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
        	insertWechatReceiveMessage(outputMsg, inputMsg);
        }// 视频消息
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
			insertWechatReceiveMessage(outputMsg, inputMsg);
		}
		else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {// 语音消息
			insertWechatReceiveMessage(outputMsg, inputMsg);
		}// 地理位置消息
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
			insertWechatReceiveMessage(outputMsg, inputMsg);
		}// 链接消息
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
			insertWechatReceiveMessage(outputMsg, inputMsg);
		}
		else {
    		// 事件推送
    		if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
    			// 事件类型
    			String eventType = inputMsg.getEvent();
    			// 关注
    			if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
    				respContent = "谢谢您的关注！";
    			}
    			// 取消关注
    			else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
    				// TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
    			}
    			// 扫描带参数二维码
    			else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
    				String eventKey = inputMsg.getEventKey();
    				eventTypeScanOutPut(outputMsg, eventKey, inputMsg.getFromUserName());
    			}
    			// 上报地理位置
    			else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
    				// TODO 处理上报地理位置事件
    			}
    			// 自定义菜单
    			else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
    				// TODO 处理菜单点击事件
    			}
    		}else {
    			outputMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                outputMsg.setContent(respContent);
    		}
		}
        return outputMsg;
	}
	
	
	
	private void insertWechatReceiveMessage(OutputMessage outputMsg, 
			InputMessage inputMsg) {
		ActivityHmSign hmSign = activityHmService.selectForMessage(inputMsg.getFromUserName());
    	if(hmSign == null) {
    		outputMsg.setContent("提示：您不是透明人或近期未参加活动\n\n欢迎使用陶学趣公众号");
    	}else {
    		WechatReceiveMessage message = BeanUtils.copyAs(inputMsg, WechatReceiveMessage.class);
    		if(message != null) {
    			String fileType = message.getMsgType();
    			if(WechatReqMsgType.IMAGE.getCode().equals(fileType) || 
    					WechatReqMsgType.VIDEO.getCode().equals(fileType) ||
    					WechatReqMsgType.SHORTVIDEO.getCode().equals(fileType) ||
    					WechatReqMsgType.VOICE.getCode().equals(fileType)) {
    				String path = DateUtil.getYear(new Date()) + Const.SEPARATOR + hmSign.getActivityId();
    				String sysUrl = FileUtil.downloadWechatFile(path, message, false);
    				message.setSysUrl(sysUrl);
    				if(WechatReqMsgType.VIDEO.getCode().equals(fileType) || 
    						WechatReqMsgType.SHORTVIDEO.getCode().equals(fileType)) {
    					String picUrl = FileUtil.downloadWechatFile(path, message, true);
    					message.setPicUrl(picUrl);
    				}
    			}
    			message.setHmSignId(hmSign.getId());
    			message.setSysCreateTime(new Date());
    			message.setCreateTime(new Date(inputMsg.getCreateTime()));
    			wechatMessageService.addReceiveMessage(message);
    			String content = PropertiesUtil.getProperty("hm_send_message");
    			content = TextUtil.format(content, new String[]{
    					WechatReqMsgType.getDesc(inputMsg.getMsgType()), hmSign.getHmName(),
    					Constants.BASEPATH + "/live", Constants.BASEPATH + "/live/marine/detail/" + hmSign.getMarineId(),
    					hmSign.getMarineName(), Constants.BASEPATH + "/hm/manage/marine"});
    			outputMsg.setContent(content);
    		}
    	}
    	outputMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
	}
	
	/**
	 * 文本消息响应
	 * @auther 胡启萌
	 * @Date 2016年12月4日
	 * @param outputMsg
	 * @param inputMsg
	 */
	private void reqMessageTypeTextOutput(OutputMessage outputMsg, InputMessage inputMsg) {
		List<WechatSendMessage> list = keywordService.selectListByParams(inputMsg.getContent());
		if(!StringUtil.isNullOrEmpty(list)) {
			if(MessageUtil.RESP_MESSAGE_TYPE_NEWS.equals(list.get(0).getMsgtype())) {
				outputMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				List<ArticleItem> newsList = new ArrayList<>();
				for(WechatSendMessage message : list) {
	    			ArticleItem item = new ArticleItem();
	    	        item.setTitle(message.getTitle());
	    	        item.setDescription(message.getDescription());
	    	        item.setPicUrl(message.getPicurl());
	    	        item.setUrl(message.getUrl());
	    	        newsList.add(item);
				}
				outputMsg.setArticleCount(newsList.size());
				outputMsg.setArticles(newsList);
			}
		}else {
			outputMsg.setContent("欢迎光临逃学趣");
	        outputMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		}
	}
	
	/**
	 * 扫描带参数二维码响应
	 * @auther 胡启萌
	 * @Date 2016年12月4日
	 * @param outputMsg
	 * @param eventKey
	 * @param openid
	 */
	private void eventTypeScanOutPut(OutputMessage outputMsg, String eventKey, String openid) {
		int type = Integer.parseInt(eventKey.substring(0, eventKey.indexOf("0"))); 
		String content = null;
		if(MessageUtil.BIND_TYPE_MARINE == type) {
			Integer marineId = Integer.parseInt(eventKey.substring(eventKey.indexOf("0"), eventKey.length())); 
			RspResult rspResult = activityMarinesService.updateHmBindMarine(marineId, openid);
			if(StatusEnum.SUCCESS.getCode().equals(rspResult.getCode())) {
				content = PropertiesUtil.getProperty("hm_regiter_success");
				ActivityHmSign hmSign = (ActivityHmSign) rspResult.getData();
				String url = Constants.BASEPATH + "/hm/manage/marine";
				String[] strings = new String[]{hmSign.getActivityName(), hmSign.getHmName(), 
						hmSign.getMarineName(), String.valueOf(hmSign.getMarineId()), 
						DateUtil.dateToStr(hmSign.getUpdateTime(), DateUtil.DEFAULT_DATE_FORMAT), url};
				content = TextUtil.format(content, strings);
			}else {
				content = "提示绑定失败：" + rspResult.getMsg();
			}
		}else if(MessageUtil.ACTIVITY_SIGN == type) {
			Integer activityId = Integer.parseInt(eventKey.substring(eventKey.indexOf("0"), eventKey.length()));
			ActivityInfo activityInfo = activityService.selectByPrimaryKey(activityId);
			Date currDate = new Date();
			if(DateUtil.compare(activityInfo.getEndDate(), currDate) != 1) {
				content = PropertiesUtil.getProperty("sign_activity_end");
				String url = Constants.BASEPATH + "/live/marine/list/" + activityId;
				content = TextUtil.format(content, new String[]{url, activityInfo.getActivityName()});
			}else {
				if(DateUtil.compare(activityInfo.getStartDate(), currDate) != -1) {
					content = PropertiesUtil.getProperty("sign_activity_begining");
					String url = Constants.BASEPATH + "/live/marine/list/" + activityId;
					content = TextUtil.format(content, new String[]{url, activityInfo.getActivityName()});
				}else {
					RspResult result = activityHmService.addSign(activityId, openid);
					if(StatusEnum.SUCCESS.getCode().equals(result.getCode())) {
						content = PropertiesUtil.getProperty("sign_activity_succ");
						content = TextUtil.format(content, new String[]{activityInfo.getActivityName(),
								DateUtil.dateToStr(currDate, DateUtil.DEFAULT_DATE_FORMAT)});
					}else {
						content = PropertiesUtil.getProperty("sign_activity_fail");
						content = TextUtil.format(content, new String[]{result.getMsg(), 
								activityInfo.getActivityName(),
								DateUtil.dateToStr(currDate, DateUtil.DEFAULT_DATE_FORMAT)});
					}
				}
			}
		}
		if(StringUtils.isNotBlank(content)) {
			outputMsg.setContent(content);
			outputMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		}
	}
}
