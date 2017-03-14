package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.model.ChildSignInfo;
import com.xuequ.cmoc.model.WechatUserInfo;

public interface IChildSignInfoService {

	int selectCountByOrderNo(String orderNo);
	
	void addAndUpdate(ChildSignInfo info, WechatUserInfo userInfo);
	
	List<ChildSignInfo> selectListByOrderNo(String orderNo);
	
	ChildSignInfo selectById(Integer id);
	
	ChildSignInfo selectByParam(String orderNo, String openid);
	
	int insertCourseSignNamelist(List<ChildSignInfo> childList, Integer orderId);
	
	int updateByPrimaryKey(ChildSignInfo signInfo);
}
