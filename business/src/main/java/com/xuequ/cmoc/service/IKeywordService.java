package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.model.WechatSendMessage;

public interface IKeywordService {
	
	List<WechatSendMessage> selectListByParams(String keyword);
}
