package com.xuequ.cmoc.core.wechat.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.xuequ.cmoc.core.wechat.common.Constants;
import com.xuequ.cmoc.core.wechat.hander.WechatHander;
import com.xuequ.cmoc.core.wechat.message.InputMessage;
import com.xuequ.cmoc.core.wechat.message.OutputMessage;
import com.xuequ.cmoc.core.wechat.utils.SerializeXmlUtil;
import com.xuequ.cmoc.core.wechat.utils.SignUtil;
import com.xuequ.cmoc.core.wechat.utils.WechatModel;
import com.xuequ.cmoc.core.wechat.utils.WechatUtils;
import com.xuequ.cmoc.utils.HttpClientUtils;
import com.xuequ.cmoc.utils.RequestUtil;
import com.xuequ.cmoc.utils.TextUtil;

@RequestMapping("wechat")
@Controller
public class WechatController {
	
	private static Logger logger = LoggerFactory.getLogger(WechatController.class);
	
	@Autowired
	private WechatHander wechatHander;
	
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
		if(StringUtils.isBlank(Constants.BASEPATH)) Constants.BASEPATH = RequestUtil.getBasePath(request);
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
		try {
			// 将POST流转换为XStream对象  
	        XStream xs = SerializeXmlUtil.createXstream();  
	        xs.processAnnotations(InputMessage.class);  
	        xs.processAnnotations(OutputMessage.class);  
	        // 将指定节点下的xml节点数据映射为对象  
	        xs.alias("xml", InputMessage.class);  
	        // 将xml内容转换为InputMessage对象  
	        InputMessage inputMsg = (InputMessage) xs.fromXML(xmlMsg);  
			OutputMessage outputMsg = wechatHander.respMessage(inputMsg);
			String respStr = xs.toXML(outputMsg);
	        logger.info("outMesg={}", respStr);
	        response.getWriter().write(respStr);
		} catch (Exception e) {
			logger.error("--wechat respMessage, error={}", e);
		}
	}
	
}
