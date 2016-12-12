package com.xuequ.cmoc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.common.Const;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.dao.HollowManInfoMapper;
import com.xuequ.cmoc.model.HollowManInfo;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.IHollowManService;
import com.xuequ.cmoc.view.ActivityHmSignView;
import com.xuequ.cmoc.view.HollowManInfoView;
import com.xuequ.cmoc.view.HollowManTakeView;

@Service("hollowManService")
public class HollowManServiceImpl implements IHollowManService {

	@Autowired
	private HollowManInfoMapper hollowManInfoMapper;
	
	@Override
	public List<HollowManInfoView> selectByPage(Page<HollowManInfoView> page) {
		return hollowManInfoMapper.selectByPage(page);
	}

	@Override
	public HollowManInfo selectByOpenid(String openid) {
		return hollowManInfoMapper.selectByOpenid(openid);
	}

	@Override
	public RspResult addAndUpdateHollowMan(HollowManInfo info, SysUser user) {
		if(info.getId() == null) {
			if(user != null) {
				info.setCreater(user.getUserName());
			}else {
				info.setCreater(Const.SYS_USER);
			}
			int count = hollowManInfoMapper.selectCountByOpenid(info.getOpenid());
			if(count > 0) {
				return new RspResult(StatusEnum.ALREADY_REGISTER);
			}
			hollowManInfoMapper.insertSelective(info);
		}else {
			if(user != null) {
				info.setUpdater(user.getUserName());
			}else {
				info.setUpdater(Const.SYS_USER);
			}
			info.setUpdateTime(new Date());
			hollowManInfoMapper.updateByPrimaryKeySelective(info);
		}
		return new RspResult(StatusEnum.SUCCESS);
	}

	@Override
	public int updateAuditRegHm(List<Integer> ids, Integer isActive, String reason) {
		if(isActive == 1) {
			return hollowManInfoMapper.updateAuditActiveHm(ids, reason);
		}
		return hollowManInfoMapper.updateAuditDeleteHm(ids, reason);
	}

	@Override
	public List<HollowManTakeView> selectHmTakeListByHmId(Integer hmId) {
		return hollowManInfoMapper.selectHmTakeListByHmId(hmId);
	}

	@Override
	public List<HollowManInfo> selectListByIds(List<Integer> ids) {
		return hollowManInfoMapper.selectListByIds(ids);
	}

}
