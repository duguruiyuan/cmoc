package com.xuequ.cmoc;

import com.xuequ.cmoc.core.wechat.controller.WechatController;

public class TestService {
	
	public static void main(String[] args) {
		try {
			String xmlMsg = "<xml><ToUserName><![CDATA[gh_8767bd1fa0d5]]></ToUserName><FromUserName><![CDATA[oXDCCs3AjlFVcKWtJybY54gqP9hI]]></FromUserName><CreateTime>1480411900</CreateTime><MsgType><![CDATA[voice]]></MsgType><MediaId><![CDATA[8ER0jxoX9SBUgPiZHbGcuM8tQZuqetAymWy4J7CG4oZmPkN6SJ61S7BDKmFVRrLw]]></MediaId><Format><![CDATA[amr]]></Format><MsgId>6358320695526822047</MsgId><Recognition><![CDATA[]]></Recognition></xml>";	        
			WechatController.respMessage(xmlMsg, null);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
