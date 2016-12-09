package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.model.HollowManInfo;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.HollowManInfoView;
import com.xuequ.cmoc.view.HollowManTakeView;

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
	/**
	 * 新增或修改同名信息
	 * @param info
	 * @param user
	 * @return
	 */
	RspResult addAndUpdateHollowMan(HollowManInfo info, SysUser user);
	/**
	 * 审核注册透明人
	 * @param ids
	 * @param isActive
	 * @param reason
	 * @return
	 */
	RspResult updateAuditRegHm(List<Integer> ids, Integer isActive, String reason);
	/**
	 * 根据透明编号查询带看记录
	 * @param hmId
	 * @return
	 */
	List<HollowManTakeView> selectHmTakeListByHmId(Integer hmId);
}
