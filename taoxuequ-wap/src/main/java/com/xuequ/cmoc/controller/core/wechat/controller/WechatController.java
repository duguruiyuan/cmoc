package com.xuequ.cmoc.controller.core.wechat.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.controller.core.wechat.utils.SHA1;
import com.xuequ.cmoc.controller.core.wechat.utils.WechatModel;
import com.xuequ.cmoc.controller.core.wechat.utils.WechatUtils;
import com.xuequ.cmoc.utils.HttpClientUtils;
import com.xuequ.cmoc.utils.TextUtil;

@RequestMapping("wechat")
@Controller
public class WechatController {
	Logger logger = Logger.getLogger(WechatController.class);
	
	@RequestMapping("openid")
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
	
	@RequestMapping("refrese/openid")
	public @ResponseBody Object refreseOpenid(HttpServletRequest request) {
		try{
			String refresh_token = request.getParameter("refresh_token");
			WechatConfigure configure = WechatConfigure.getInstance();
			String url = TextUtil.format(configure.getUserFefreshToken(), 
					new String[]{configure.getAppid(), refresh_token});
			return HttpClientUtils.doGet(url);
		}catch(Exception e) {
			logger.error("刷新获取用户openid错误,{}", e);
		}
		return null;
	}
	
	@RequestMapping("redirect/{id}")
	public String wechat(@PathVariable String id, Model model) {
		model.addAttribute("shareKey", id);
		model.addAttribute("appid", WechatConfigure.getInstance().getAppid());
		return "wechat";
	}

	@RequestMapping("/token")
	public void token(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
		// 微信加密签名
        String signature = request.getParameter("signature");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        logger.info(">>=signature:" + signature + ">echostr:" + echostr + ">timestamp:" + timestamp + ">nonce:" + nonce);
        String[] str = {WechatConfigure.getInstance().getToken() , timestamp, nonce };
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        // SHA1加密
        String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
        logger.info(">>=digest" + digest);
        // 确认请求来至微信
        if (digest.equals(signature)) {
        	response.getWriter().print(echostr);
        }
	}
	
	@RequestMapping("/tokens")
	public @ResponseBody Object accessToken() {
		WechatModel model = WechatUtils.getWechatModel();
		if(StringUtils.isBlank(model.getAccessToken())) {
			return new RspResult(StatusEnum.ACCESS_TOKEN_FAIL);
		}else if(StringUtils.isBlank(model.getJsapiTicket())) {
			return new RspResult(StatusEnum.JSAPI_TICKET_FAIL);
		}
		return new RspResult(StatusEnum.SUCCESS, model);
	}
	
	
	@RequestMapping("/signature")
	public @ResponseBody Object signature(WechatModel vo) {
		WechatModel model = WechatUtils.getWechatModel();
		model.setUrl(vo.getUrl());
		model.setTimestamp(new Date().getTime());
		model.setAppid(WechatConfigure.getInstance().getAppid());
		model.setNonceStr("0ilHiz0BU97");
		model.setSignature(getSignature(model));
		return new RspResult(StatusEnum.SUCCESS, model);
	}
	
	// 获得js signature
	public static String getSignature(WechatModel vo) {
	    /****
	     * 对 jsapi_ticket、 timestamp 和 nonce 按字典排序 对所有待签名参数按照字段名的 ASCII
	     * 码从小到大排序（字典序）后，使用 URL 键值对的格式（即key1=value1&key2=value2…）拼接成字符串
	     * string1。这里需要注意的是所有参数名均为小写字符。 接下来对 string1 作 sha1 加密，字段名和字段值都采用原始值，不进行
	     * URL 转义。即 signature=sha1(string1)。
	     * **如果没有按照生成的key1=value&key2=value拼接的话会报错
	     */
	    String[] paramArr = new String[] { "jsapi_ticket=" + vo.getJsapiTicket(),
	            "timestamp=" + vo.getTimestamp(), "noncestr=" + vo.getNonceStr(), "url=" + vo.getUrl() };
	    Arrays.sort(paramArr);
	    // 将排序后的结果拼接成一个字符串
	    String content = paramArr[0].concat("&"+paramArr[1]).concat("&"+paramArr[2])
	            .concat("&"+paramArr[3]);
	    String gensignature = null;
	    try {
	        MessageDigest md = MessageDigest.getInstance("SHA-1");
	        // 对拼接后的字符串进行 sha1 加密
	        byte[] digest = md.digest(content.toString().getBytes());
	        gensignature = byteToStr(digest);
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    // 将 sha1 加密后的字符串与 signature 进行对比
	    if (gensignature != null) {
	        return gensignature;// 返回signature
	    } else {
	        return "false";
	    }
	}
	 
	/**
	 * 将字节数组转换为十六进制字符串
	 *
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
	    String strDigest = "";
	    for (int i = 0; i < byteArray.length; i++) {
	        strDigest += byteToHexStr(byteArray[i]);
	    }
	    return strDigest;
	}
	 
	/**
	 * 将字节转换为十六进制字符串
	 *
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
	    char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
	            'B', 'C', 'D', 'E', 'F' };
	    char[] tempArr = new char[2];
	    tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
	    tempArr[1] = Digit[mByte & 0X0F];
	    String s = new String(tempArr);
	    return s;
	}
	
}
