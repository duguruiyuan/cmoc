package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.model.WechatReceiveMessage;
import com.xuequ.cmoc.page.Page;

public interface IWechatMessageService {

	public int addReceiveMessage(WechatReceiveMessage message);
	
	public List<WechatReceiveMessage> selectListByPage(Page<WechatReceiveMessage> page);
	
	public int selectCountByParam(WechatReceiveMessage message);
	
	int updateByParam(WechatReceiveMessage record);
}

