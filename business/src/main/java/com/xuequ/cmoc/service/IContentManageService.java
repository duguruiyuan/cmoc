package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.model.ImgGroup;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.model.WechatKeyword;
import com.xuequ.cmoc.model.WechatSendMessage;

public interface IContentManageService {

	List<WechatSendMessage> selectList(Integer keyId, String keyword);
	
	List<WechatKeyword> selectList(String keyword);
	
	WechatKeyword selectByKeyword(String keyword);
	
	List<ImgGroup> selectListByParam(ImgGroup group);
	
	int addUpdateImgGroup(ImgGroup group, SysUser user);
	
	ImgGroup selectImgGroupById(Integer id);
}
