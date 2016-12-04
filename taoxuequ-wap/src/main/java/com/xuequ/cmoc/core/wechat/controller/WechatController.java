package com.xuequ.cmoc.core.wechat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thoughtworks.xstream.XStream;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.core.wechat.message.ArticleItem;
import com.xuequ.cmoc.core.wechat.message.ImageMessage;
import com.xuequ.cmoc.core.wechat.message.InputMessage;
import com.xuequ.cmoc.core.wechat.message.OutputMessage;
import com.xuequ.cmoc.core.wechat.message.VideoMessage;
import com.xuequ.cmoc.core.wechat.utils.MessageUtil;
import com.xuequ.cmoc.core.wechat.utils.SerializeXmlUtil;
import com.xuequ.cmoc.core.wechat.utils.SignUtil;
import com.xuequ.cmoc.core.wechat.utils.WechatModel;
import com.xuequ.cmoc.core.wechat.utils.WechatUtils;
import com.xuequ.cmoc.model.ActivityHmSign;
import com.xuequ.cmoc.model.WechatSendMessage;
import com.xuequ.cmoc.service.IActivityMarinesService;
import com.xuequ.cmoc.service.IKeywordService;
import com.xuequ.cmoc.utils.DateUtil;
import com.xuequ.cmoc.utils.HttpClientUtils;
import com.xuequ.cmoc.utils.PropertiesUtil;
import com.xuequ.cmoc.utils.StringUtil;
import com.xuequ.cmoc.utils.TextUtil;

@RequestMapping("wechat")
@Controller
public class WechatController {
	
	private static Logger logger = LoggerFactory.getLogger(WechatController.class);
	@Autowired
	private IActivityMarinesService activityMarinesService;
	@Autowired
	private IKeywordService keywordService;
	
	/**
	 * 微信令牌校验，用户消息接收
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/token")
	public void token(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
		boolean isGet = request.getMethod().toLowerCase().equals("get");
		logger.info("-----isGet={}", isGet);
		if(isGet) {
			// 微信加密签名
	        String signature = request.getParameter("signature");
	        // 随机字符串
	        String echostr = request.getParameter("echostr");
	        // 时间戳
	        String timestamp = request.getParameter("timestamp");
	        // 随机数
	        String nonce = request.getParameter("nonce");
	        logger.info("---------signature={}" + signature + "--echostr={}" + echostr + 
	        		"--timestamp={}"+timestamp + "--nonce={}" + nonce);
	        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
	        	response.getWriter().print(echostr);
	        }
		}else {
			acceptMessage(request, response);
		}
		
	}
	
	@RequestMapping("oauth/access")
	public @ResponseBody Object getOpenid(HttpServletRequest request) {
		try{
			String code = request.getParameter("code");
			WechatConfigure configure = WechatConfigure.getInstance();
			String url = TextUtil.format(configure.getUserAccessToken(), 
					new String[]{configure.getAppid(), configure.getAppsecret(), code});
			return HttpClientUtils.doGet(url);
		}catch(Exception e) {
			logger.error("获取用户openid错误,{}", e);
		}
		return null;
	}
	
	@RequestMapping("oauth/refrese")
	public @ResponseBody Object refreseOpenid(HttpServletRequest request) {
		try{
			String fefreshToken = request.getParameter("refresh_token");
			return WechatUtils.getOpenidByRefreshToken(fefreshToken);
		}catch(Exception e) {
			logger.error("刷新获取用户openid错误,{}", e);
		}
		return null;
	}

	@RequestMapping("/signature")
	public @ResponseBody Object signature(WechatModel vo) {
		WechatModel model = WechatUtils.getWechatModel();
		model.setUrl(vo.getUrl());
		model.setTimestamp(new Date().getTime());
		model.setAppid(WechatConfigure.getInstance().getAppid());
		model.setNonceStr("0ilHiz0BU97");
		model.setSignature(SignUtil.getSignature(model));
		return new RspResult(StatusEnum.SUCCESS, model);
	}
	
	private void acceptMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {  
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		// 处理接收消息  
        ServletInputStream in = request.getInputStream();  
        // 将流转换为字符串  
        StringBuilder xmlMsg = new StringBuilder();  
        byte[] b = new byte[4096];  
        for (int n; (n = in.read(b)) != -1;) {  
            xmlMsg.append(new String(b, 0, n, "UTF-8"));  
        } 
        respMessage(xmlMsg.toString(), response);
    }
	
	public void respMessage(String xmlMsg, HttpServletResponse response) throws IOException{
		logger.info("--wechat reqMessage, params={}", xmlMsg);
		// 将POST流转换为XStream对象  
        XStream xs = SerializeXmlUtil.createXstream();  
        xs.processAnnotations(InputMessage.class);  
        xs.processAnnotations(OutputMessage.class);  
        // 将指定节点下的xml节点数据映射为对象  
        xs.alias("xml", InputMessage.class);  
        // 将xml内容转换为InputMessage对象  
        InputMessage inputMsg = (InputMessage) xs.fromXML(xmlMsg);  
  
        String servername = inputMsg.getToUserName();// 服务端  
        String custermname = inputMsg.getFromUserName();// 客户端  
//      long createTime = inputMsg.getCreateTime();// 接收时间  
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
            ImageMessage images = new ImageMessage();  
            images.setMediaId(inputMsg.getMediaId());
            outputMsg.setImage(images);
            outputMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
        }// 视频消息
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
			VideoMessage videoMessage = new VideoMessage();
			videoMessage.setMediaId(inputMsg.getMediaId());
			videoMessage.setTitle("abc");
			videoMessage.setDescription("芳草无，贪念何必花一枝");
			outputMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VIDEO);
			outputMsg.setVideo(videoMessage);
		}else {
        	// 语音消息
    		if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
    			List<ArticleItem> list = new ArrayList<>();
    	        outputMsg.setArticles(list);
    	        outputMsg.setArticleCount(list.size());
    	        outputMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
    		}
    		// 视频消息
    		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
    			respContent = "您发送的是视频消息！";
    		}
    		// 地理位置消息
    		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
    			respContent = "您发送的是地理位置消息！";
    		}
    		// 链接消息
    		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
    			respContent = "您发送的是链接消息！";
    		}
    		// 事件推送
    		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
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
            logger.info("outMesg={}", xs.toXML(outputMsg));
            response.getWriter().write(xs.toXML(outputMsg)); 
		}
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
		if(MessageUtil.BIND_TYPE_MARINE == type) {
			Integer marineId = Integer.parseInt(eventKey.substring(eventKey.indexOf("0"), eventKey.length())); 
			RspResult rspResult = activityMarinesService.updateHmBindMarine(marineId, openid);
			String content = null;
			if(StatusEnum.SUCCESS.getCode().equals(rspResult.getCode())) {
				content = PropertiesUtil.getProperty("hm_regiter_success");
				ActivityHmSign hmSign = (ActivityHmSign) rspResult.getData();
				String url = "/manage/marine?mid=" + hmSign.getActivityId() + "&hid=" + hmSign.getHmId();
				content = TextUtil.format(content, new String[]{hmSign.getActivityName(), hmSign.getHmName(), 
						hmSign.getMarineName(), String.valueOf(hmSign.getMarineId()), 
						DateUtil.dateToStr(hmSign.getUpdateTime(), DateUtil.DEFAULT_DATE_FORMAT), url});
			}else {
				content = rspResult.getMsg();
			}
			outputMsg.setContent(content);
			outputMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		}
	}
	
}
