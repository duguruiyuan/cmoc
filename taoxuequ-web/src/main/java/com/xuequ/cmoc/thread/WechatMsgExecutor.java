package com.xuequ.cmoc.thread;

import com.xuequ.cmoc.utils.HttpClientUtils;

public class WechatMsgExecutor implements Runnable {
	
	private String url;
	
	private Object obj;
	
	public WechatMsgExecutor(String url, Object obj) {
		this.url = url;
		this.obj = obj;
	}

	@Override
	public void run() {
		try { 
            HttpClientUtils.postJson(url, obj);
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
	}

}
