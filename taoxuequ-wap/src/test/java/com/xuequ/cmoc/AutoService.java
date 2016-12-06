package com.xuequ.cmoc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thoughtworks.xstream.XStream;
import com.xuequ.cmoc.core.wechat.hander.WechatHander;
import com.xuequ.cmoc.core.wechat.message.InputMessage;
import com.xuequ.cmoc.core.wechat.message.OutputMessage;
import com.xuequ.cmoc.core.wechat.utils.SerializeXmlUtil;
import com.xuequ.cmoc.core.wechat.utils.WechatUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class AutoService {
	
	@Autowired
	private WechatHander wechatHander;
	
	@Test
	public void test() {
		try {
			String xmlMsg = "<xml><ToUserName><![CDATA[gh_c171dcbc9956]]></ToUserName><FromUserName><![CDATA[oqyqUwq_YY84qjFWUtn6Ti4XIROE]]></FromUserName><CreateTime>1480993433</CreateTime><MsgType><![CDATA[shortvideo]]></MsgType><MediaId><![CDATA[c6VcRU7qlVxIyVmxEVwVx0r2PWlXGZ1uq9AhqIgq2i3hjXeRfvotIk23SjGWWJqV]]></MediaId><ThumbMediaId><![CDATA[4PD2t_A3od7z812OyB6GVkVt1fsE_Nc5mwmApZMo-C_G-NEnGTsEPj48Ydw2m6Pk]]></ThumbMediaId><MsgId>6360818360834828701</MsgId></xml>";
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
	        System.out.println("outMesg={}" + respStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
