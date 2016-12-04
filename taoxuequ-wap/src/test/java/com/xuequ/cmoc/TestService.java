package com.xuequ.cmoc;

import com.alibaba.fastjson.JSONObject;
import com.xuequ.cmoc.core.wechat.controller.WechatController;
import com.xuequ.cmoc.core.wechat.model.WechatActionInfo;
import com.xuequ.cmoc.core.wechat.model.WechatQrcodeReq;
import com.xuequ.cmoc.core.wechat.model.WechatQrcodeRsp;
import com.xuequ.cmoc.core.wechat.model.WechatScene;
import com.xuequ.cmoc.core.wechat.utils.DateTypeUtils;
import com.xuequ.cmoc.utils.HttpClientUtils;
import com.xuequ.cmoc.utils.JsonUtils;

public class TestService {
	
	public static void main(String[] args) {
		try {
//			WechatModel model = WechatUtils.getWechatModel();
			String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=gFGMCC3YQWt8-wenxa4dlVc-_vQRQ5CRlfWdT9qP4GnrYdL2cnyu6AaBuCbwwbWDGviTHe9T7z0vgh3pkB47TFP5yTcrlNaQ-vG9iMN9AUe-5Z-tlqcNudVZ6P_TkmMXQNYhAGAKHS";
			WechatQrcodeReq req = new WechatQrcodeReq();
			WechatActionInfo info = new WechatActionInfo();
			WechatScene scene = new WechatScene();
			Long time = DateTypeUtils.getSceneId(1,1);
			System.out.println("--------------------------time:" + time);
			scene.setScene_id(time);
			info.setScene(scene);
			req.setAction_info(info);
			req.setExpire_seconds(604800L);
			req.setAction_name("QR_SCENE");
			String response = HttpClientUtils.postJson(url, req);
			System.out.println("--response={}" + response);
			JSONObject jsonObject = JSONObject.parseObject(response);
			if(jsonObject.getString("errcode") == null) {
				WechatQrcodeRsp rsp = JsonUtils.jsonToObject(response, WechatQrcodeRsp.class);
				String url1 = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + rsp.getTicket();
				System.out.println(HttpClientUtils.doGet(url1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void wechatXml() throws Exception{
		String xmlMsg = "<xml><ToUserName><![CDATA[gh_8767bd1fa0d5]]></ToUserName><FromUserName><![CDATA[oXDCCs3AjlFVcKWtJybY54gqP9hI]]></FromUserName><CreateTime>1480411900</CreateTime><MsgType><![CDATA[voice]]></MsgType><MediaId><![CDATA[8ER0jxoX9SBUgPiZHbGcuM8tQZuqetAymWy4J7CG4oZmPkN6SJ61S7BDKmFVRrLw]]></MediaId><Format><![CDATA[amr]]></Format><MsgId>6358320695526822047</MsgId><Recognition><![CDATA[]]></Recognition></xml>";	        
		WechatController.respMessage(xmlMsg, null);
	}

}
