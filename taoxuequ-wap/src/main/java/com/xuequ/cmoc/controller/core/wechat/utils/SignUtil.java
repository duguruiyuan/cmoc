package com.xuequ.cmoc.controller.core.wechat.utils;

import java.util.Arrays;

import com.xuequ.cmoc.common.WechatConfigure;

public class SignUtil {
	
	/**
	 * 校验消息是否从公众号来
	 * @auther 胡启萌
	 * @Date 2016年11月28日
	 * @param signature 微信加密签名
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @return
	 */
	public static boolean checkSignature(String signature, 
			String timestamp, String nonce) {
		String[] str = {WechatConfigure.getInstance().getToken() , timestamp, nonce };
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        // SHA1加密
        String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
        // 确认请求来至微信
        if (digest.equals(signature)) {
        	return true;
        }
        return false;
	}

}
