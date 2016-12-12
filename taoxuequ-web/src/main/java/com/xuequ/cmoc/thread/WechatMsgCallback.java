package com.xuequ.cmoc.thread;

public class WechatMsgCallback {

	private String url;
	
	private Object obj;
	
    public WechatMsgCallback(String url, Object obj) { 
        this.url = url;
        this.obj = obj;
    } 

    public void execute() { 
        WechatMsgExecutor callback = new WechatMsgExecutor(url, obj); 
        Thread t = new Thread(callback); 
        t.start(); 
    } 

}
