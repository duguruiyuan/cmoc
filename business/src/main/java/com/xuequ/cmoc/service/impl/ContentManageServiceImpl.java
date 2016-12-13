package com.xuequ.cmoc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.ImgGroupMapper;
import com.xuequ.cmoc.dao.WechatKeywordMapper;
import com.xuequ.cmoc.dao.WechatSendMessageMapper;
import com.xuequ.cmoc.model.ImgGroup;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.model.WechatKeyword;
import com.xuequ.cmoc.model.WechatSendMessage;
import com.xuequ.cmoc.service.IContentManageService;

@Service("contentManageService")
public class ContentManageServiceImpl implements IContentManageService {

	@Autowired
	private WechatKeywordMapper wechatKeywordMapper;
	@Autowired
	private WechatSendMessageMapper wechatSendMessageMapper;
	@Autowired
	private ImgGroupMapper imgGroupMapper;
	
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

	@Override
	public List<ImgGroup> selectListByParam(ImgGroup group) {
		return imgGroupMapper.selectListByParam(group);
	}

	@Override
	public int addUpdateImgGroup(ImgGroup group, SysUser user) {
		if(group.getId() == null){
			group.setCreater(user.getUserName());
			group.setCreateUserId(user.getIdUser());
			group.setCreateTime(new Date());
			return imgGroupMapper.insertSelective(group);
		}
		group.setUpdater(user.getUserName());
		group.setUpdateUserId(user.getIdUser());
		group.setUpdateTime(new Date());
		return imgGroupMapper.updateByPrimaryKey(group);
	}

	@Override
	public ImgGroup selectImgGroupById(Integer id) {
		return imgGroupMapper.selectByPrimaryKey(id);
	}

}
