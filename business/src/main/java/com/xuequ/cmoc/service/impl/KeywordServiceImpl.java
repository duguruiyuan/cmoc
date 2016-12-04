package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.WechatSendMessageMapper;
import com.xuequ.cmoc.model.WechatSendMessage;
import com.xuequ.cmoc.service.IKeywordService;

@Service("keywordService")
public class KeywordServiceImpl implements IKeywordService {

	@Autowired
	private WechatSendMessageMapper wechatSendMessageMapper;
	
	@Override
	public List<WechatSendMessage> selectListByParams(String keyword) {
		return wechatSendMessageMapper.selectListByParams(null, keyword);
	}

}
