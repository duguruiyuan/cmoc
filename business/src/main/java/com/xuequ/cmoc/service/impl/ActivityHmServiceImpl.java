package com.xuequ.cmoc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.common.Const;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.dao.ActivityHmSignMapper;
import com.xuequ.cmoc.dao.HollowManInfoMapper;
import com.xuequ.cmoc.model.ActivityHmSign;
import com.xuequ.cmoc.model.HollowManInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.IActivityHmService;
import com.xuequ.cmoc.utils.BeanUtils;
import com.xuequ.cmoc.utils.DateUtil;
import com.xuequ.cmoc.view.ActivityHmSignView;

@Service("activityHmService")
public class ActivityHmServiceImpl implements IActivityHmService {

	@Autowired
	private ActivityHmSignMapper activityHmSignMapper;
	@Autowired
	private HollowManInfoMapper hollowManInfoMapper;
	
	@Override
	public List<ActivityHmSignView> selectListByPage(Page<ActivityHmSignView> page) {
		return activityHmSignMapper.selectListByPage(page);
	}

	@Override
	public ActivityHmSign selectForMessage(String openid) {
		return activityHmSignMapper.selectForMessage(openid);
	}

	@Override
	public List<ActivityHmSignView> selectWithTeamRecord(String openid) {
		return activityHmSignMapper.selectWithTeamRecord(openid);
	}

	@Override
	public RspResult addSign(Integer activityId, String openid) {
		HollowManInfo manInfo = hollowManInfoMapper.selectByOpenid(openid);
		if(manInfo.getIsActive() == 0) {
			return new RspResult(StatusEnum.HM_NON_ACTIVE);
		}
		int count = activityHmSignMapper.selectCountForSign(activityId, manInfo.getId());
		if(count > 0) {
			return new RspResult(StatusEnum.ALREADY_SIGN_ACTIVITY);
		}
		ActivityHmSign hmSign = BeanUtils.copyAs(manInfo, ActivityHmSign.class);
		hmSign.setCreater(Const.SYS_USER);
		hmSign.setCreateTime(new Date());
		hmSign.setActivityId(activityId);
		hmSign.setHmId(manInfo.getId());
		hmSign.setSignDate(DateUtil.getDate(new Date()));
		activityHmSignMapper.insertSelective(hmSign);
		return new RspResult(StatusEnum.SUCCESS);
	}

	@Override
	public List<ActivityHmSignView> selectHmSignForAudit(Page<ActivityHmSignView> page) {
		return activityHmSignMapper.selectHmSignForAudit(page);
	}

	@Override
	public int updateAuditHmSign(List<Integer> list, Integer isEffect, String reason) {
		if(isEffect == 1) {
			return activityHmSignMapper.updateAuditEffectHmSign(list, reason);
		}
		return activityHmSignMapper.updateAuditDeleteHmSign(list, reason);
	}

}
