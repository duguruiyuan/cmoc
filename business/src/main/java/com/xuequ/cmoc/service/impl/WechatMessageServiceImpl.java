package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.WechatReceiveMessageMapper;
import com.xuequ.cmoc.model.WechatReceiveMessage;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.IWechatMessageService;

@Service("wechatMessageService")
public class WechatMessageServiceImpl implements IWechatMessageService {

	@Autowired
	private WechatReceiveMessageMapper wechatReceiveMessageMapper;
	
	@Override
	public int addReceiveMessage(WechatReceiveMessage message) {
		return wechatReceiveMessageMapper.insertSelective(message);
	}

	@Override
	public List<WechatReceiveMessage> selectListByPage(Page<WechatReceiveMessage> page) {
		return wechatReceiveMessageMapper.selectListByPage(page);
	}

	@Override
	public int selectCountByParam(WechatReceiveMessage message) {
		return wechatReceiveMessageMapper.selectCountByParam(message);
	}

	@Override
	public int updateByParam(WechatReceiveMessage record) {
		return wechatReceiveMessageMapper.updateByPrimaryKeySelective(record);
	}


}
