package com.xuequ.cmoc.core.wechat.thread;

import com.xuequ.cmoc.core.wechat.utils.FileUtil;
import com.xuequ.cmoc.model.WechatReceiveMessage;

public class WechatMsgExecutor implements Runnable {
	
	private String path;
	
	private WechatReceiveMessage message;
	
	public WechatMsgExecutor(String path, WechatReceiveMessage message) {
		this.path = path;
		this.message = message;
	}

	@Override
	public void run() {
		try { 
			FileUtil.downloadWechatFile(path, message, false);
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
	}

}
