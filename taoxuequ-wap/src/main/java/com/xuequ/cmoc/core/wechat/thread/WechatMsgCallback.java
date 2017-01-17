package com.xuequ.cmoc.core.wechat.thread;

import com.xuequ.cmoc.model.WechatReceiveMessage;

public class WechatMsgCallback {

	private String path;
	
	private WechatReceiveMessage message;
	
    public WechatMsgCallback(String path, WechatReceiveMessage message) { 
    	this.path = path;
    	this.message = message;
    } 

    public void execute() { 
        WechatMsgExecutor callback = new WechatMsgExecutor(path, message); 
        Thread t = new Thread(callback); 
        t.start(); 
    } 

}
