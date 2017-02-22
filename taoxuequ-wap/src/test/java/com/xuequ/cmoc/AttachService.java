package com.xuequ.cmoc;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.xstream.XStream;
import com.xuequ.cmoc.base.BaseTest;
import com.xuequ.cmoc.core.wechat.hander.WechatHander;
import com.xuequ.cmoc.core.wechat.message.InputMessage;
import com.xuequ.cmoc.core.wechat.message.OutputMessage;
import com.xuequ.cmoc.core.wechat.utils.SerializeXmlUtil;

public class AttachService extends BaseTest {
	
	private Logger logger = LoggerFactory.getLogger(AttachService.class);

	@Autowired
	private WechatHander wechatHander;
	
	@Test
	public void upload() {
		try {
			String xmlMsg = "<xml><ToUserName><![CDATA[gh_c171dcbc9956]]></ToUserName><FromUserName><![CDATA[oqyqUwq_YY84qjFWUtn6Ti4XIROE]]></FromUserName><CreateTime>1487755107</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[CLICK]]></Event><EventKey><![CDATA[transfer_customer_service]]></EventKey></xml>";
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
