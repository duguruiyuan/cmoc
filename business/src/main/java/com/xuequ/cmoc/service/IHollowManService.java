package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.model.HollowManInfo;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.HollowManInfoView;

public interface IHollowManService {
	
	/**
	 * 分页查询透明人信息
	 * @param page
	 * @return
	 */
	List<HollowManInfoView> selectByPage(Page<HollowManInfoView> page);
	/**
	 * 根据openid查询透明人信息
	 * @auther 胡启萌
	 * @Date 2016年12月3日
	 * @param openid
	 * @return
	 */
	HollowManInfo selectByOpenid(String openid);

	RspResult addAndUpdateHollowMan(HollowManInfo info, SysUser user);
}
