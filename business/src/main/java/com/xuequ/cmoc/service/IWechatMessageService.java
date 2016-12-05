package com.xuequ.cmoc.service;

import com.xuequ.cmoc.model.WechatReceiveMessage;

public interface IWechatMessageService {

	public int addReceiveMessage(WechatReceiveMessage message);
}
