package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.WechatKeywordMapper;
import com.xuequ.cmoc.dao.WechatSendMessageMapper;
import com.xuequ.cmoc.model.WechatKeyword;
import com.xuequ.cmoc.model.WechatSendMessage;
import com.xuequ.cmoc.service.IContentManageService;

@Service("contentManageService")
public class ContentManageServiceImpl implements IContentManageService {

	@Autowired
	private WechatKeywordMapper wechatKeywordMapper;
	@Autowired
	private WechatSendMessageMapper wechatSendMessageMapper;
	
	@Override
	public List<WechatSendMessage> selectList(Integer keyId, String keyword) {
		return wechatSendMessageMapper.selectListByParams(keyId, keyword);
	}

	@Override
	public List<WechatKeyword> selectList(String keyword) {
		return wechatKeywordMapper.selectListByParams(keyword);
	}

	@Override
	public WechatKeyword selectByKeyword(String keyword) {
		return wechatKeywordMapper.selectByKeyword(keyword);
	}

}
