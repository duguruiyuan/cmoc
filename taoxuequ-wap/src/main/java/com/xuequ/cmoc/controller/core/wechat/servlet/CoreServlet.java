package com.xuequ.cmoc.controller.core.wechat.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thoughtworks.xstream.XStream;
import com.xuequ.cmoc.controller.core.wechat.message.ImageMessage;
import com.xuequ.cmoc.controller.core.wechat.message.InputMessage;
import com.xuequ.cmoc.controller.core.wechat.message.OutputMessage;
import com.xuequ.cmoc.controller.core.wechat.utils.MessageUtil;
import com.xuequ.cmoc.controller.core.wechat.utils.SerializeXmlUtil;
import com.xuequ.cmoc.controller.core.wechat.utils.SignUtil;

public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
        	response.getWriter().print(echostr);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
	}
	
	private void acceptMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {  
        // 处理接收消息  
        ServletInputStream in = request.getInputStream();  
        // 将POST流转换为XStream对象  
        XStream xs = SerializeXmlUtil.createXstream();  
        xs.processAnnotations(InputMessage.class);  
        xs.processAnnotations(OutputMessage.class);  
        // 将指定节点下的xml节点数据映射为对象  
        xs.alias("xml", InputMessage.class);  
        // 将流转换为字符串  
        StringBuilder xmlMsg = new StringBuilder();  
        byte[] b = new byte[4096];  
        for (int n; (n = in.read(b)) != -1;) {  
            xmlMsg.append(new String(b, 0, n, "UTF-8"));  
        }  
        // 将xml内容转换为InputMessage对象  
        InputMessage inputMsg = (InputMessage) xs.fromXML(xmlMsg.toString());  
  
        String servername = inputMsg.getToUserName();// 服务端  
        String custermname = inputMsg.getFromUserName();// 客户端  
        long createTime = inputMsg.getCreateTime();// 接收时间  
        Long returnTime = Calendar.getInstance().getTimeInMillis() / 1000;// 返回时间  
  
        // 取得消息类型  
        String msgType = inputMsg.getMsgType();  
        // 根据消息类型获取对应的消息内容  
        if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
            // 文本消息  
            System.out.println("开发者微信号：" + inputMsg.getToUserName());  
            System.out.println("发送方帐号：" + inputMsg.getFromUserName());  
            System.out.println("消息创建时间：" + inputMsg.getCreateTime() + new Date(createTime * 1000l));  
            System.out.println("消息内容：" + inputMsg.getContent());  
            System.out.println("消息Id：" + inputMsg.getMsgId());  
  
            response.getWriter().write(str.toString());  
        }  
        // 获取并返回多图片消息  
        if (msgType.equals(MsgType.Image.toString())) {  
            System.out.println("获取多媒体信息");  
            System.out.println("多媒体文件id：" + inputMsg.getMediaId());  
            System.out.println("图片链接：" + inputMsg.getPicUrl());  
            System.out.println("消息id，64位整型：" + inputMsg.getMsgId());  
  
            OutputMessage outputMsg = new OutputMessage();  
            outputMsg.setFromUserName(servername);  
            outputMsg.setToUserName(custermname);  
            outputMsg.setCreateTime(returnTime);  
            outputMsg.setMsgType(msgType);  
            ImageMessage images = new ImageMessage();  
            images.setMediaId(inputMsg.getMediaId());  
            outputMsg.setImage(images);  
            System.out.println("xml转换：/n" + xs.toXML(outputMsg));  
            response.getWriter().write(xs.toXML(outputMsg));  
  
        }  
    } 

}
