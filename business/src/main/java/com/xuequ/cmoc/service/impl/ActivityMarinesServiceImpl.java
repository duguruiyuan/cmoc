package com.xuequ.cmoc.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.common.Const;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.dao.ActivityHmSignMapper;
import com.xuequ.cmoc.dao.ActivityInfoMapper;
import com.xuequ.cmoc.dao.ActivityMarinesMapper;
import com.xuequ.cmoc.dao.HollowManInfoMapper;
import com.xuequ.cmoc.dao.MarineSupportMapper;
import com.xuequ.cmoc.exception.ExpirationException;
import com.xuequ.cmoc.model.ActivityHmSign;
import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.model.ActivityMarines;
import com.xuequ.cmoc.model.MarineSupport;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.IActivityMarinesService;
import com.xuequ.cmoc.utils.DateUtil;
import com.xuequ.cmoc.view.ActivityMarinesView;

@Service("activityMarinesService")
public class ActivityMarinesServiceImpl implements IActivityMarinesService {

	@Autowired
	private ActivityMarinesMapper activityMarinesMapper;
	@Autowired
	private ActivityHmSignMapper activityHmSignMapper;
	@Autowired
	private MarineSupportMapper marineSupportMapper;
	@Autowired
	private ActivityInfoMapper activityInfoMapper;
	
	@Override
	public List<ActivityMarinesView> selectListByPage(Page<ActivityMarinesView> page) {
		return activityMarinesMapper.selectListByPage(page);
	}

	@Override
	public List<ActivityMarines> selectListByActivityId(Integer activityId) {
		return activityMarinesMapper.selectListByActivityId(activityId);
	}

	@Override
	public ActivityMarinesView selectById(Integer marineId) {
		return activityMarinesMapper.selectById(marineId);
	}

	@Override
	public int updateMarineImg(String imgUrl, Integer id) {
		return activityMarinesMapper.updateMarineImg(imgUrl, id);
	}

	@Override
	public ActivityMarines selectByPrimaryKey(Integer id) {
		return activityMarinesMapper.selectByPrimaryKey(id);
	}

	@Override
	public int addUpdateMarines(ActivityMarines marines) {
		return activityMarinesMapper.updateByPrimaryKeySelective(marines);
	}

	@Override
	public RspResult updateHmBindMarine(Integer marineId, String openid) {
		ActivityHmSign hmSign = activityHmSignMapper.selectForSign(marineId, openid);
		if(hmSign == null) {
			return new RspResult(StatusEnum.ACTIVITY_NON_SIGN);
		}
		hmSign.setMarineId(marineId);
		hmSign.setUpdater(Const.SYS_USER);
		hmSign.setUpdateTime(new Date());
		int count = activityHmSignMapper.updateBindMarine(hmSign);
		if(count <= 0) {
			throw new ExpirationException(StatusEnum.EXPIRED_DATA.getMsg());
		}
		hmSign = activityHmSignMapper.selectForSign(marineId, openid);
		return new RspResult(StatusEnum.SUCCESS, hmSign);
	}

	@Override
	public List<ActivityMarinesView> selectMarineTeam(Integer activityId) {
		return activityMarinesMapper.selectMarineTeam(activityId);
	}

	@Override
	public int updateByPrimaryKeySelective(ActivityMarines marines) {
		return activityMarinesMapper.updateByPrimaryKeySelective(marines);
	}

	@Override
	public ActivityMarinesView selectMarineByHmOpenid(String openid) {
		return activityMarinesMapper.selectMarineByHmOpenid(openid);
	}

	@Override
	public int addMarineReadnum(Integer id) {
		return activityMarinesMapper.addMarineReadnum(id);
	}

	@Override
	public RspResult addMarineVotes(Integer marineId, String openid) {
		ActivityMarines marines = activityMarinesMapper.selectByPrimaryKey(marineId);
		ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(marines.getActivityId());
		Date date = new Date();
		if(DateUtil.compare(date, activityInfo.getEndDate()) == 1) {
			return new RspResult(StatusEnum.ACTIVITY_OVER, marines);
		}else {
			if(DateUtil.compare(date, activityInfo.getStartDate()) < 0) {
				return new RspResult(StatusEnum.ACTIVITY_NON_START, marines);
			}
			int count = marineSupportMapper.selectCountForSupport(openid, marineId);
			if(count > 0) {
				return new RspResult(StatusEnum.MARINE_SUPPORT_HAD, marines);
			}
			MarineSupport support = new MarineSupport();
			support.setActivityId(marines.getActivityId());
			support.setMarineId(marines.getId());
			support.setOpenid(openid);
			marineSupportMapper.insertSelective(support);
			activityMarinesMapper.addMarineVotes(marineId);
			marines.setVotes(marines.getVotes().intValue() + 1);
			return new RspResult(StatusEnum.SUCCESS, marines);
		}
		
	}

	
	
	

}
