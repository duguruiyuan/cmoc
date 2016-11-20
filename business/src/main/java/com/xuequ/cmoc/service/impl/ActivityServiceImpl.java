package com.xuequ.cmoc.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.dao.ActivityInfoMapper;
import com.xuequ.cmoc.model.ActivityFamily;
import com.xuequ.cmoc.model.ActivityHmSign;
import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.model.ActivityMarines;
import com.xuequ.cmoc.model.ActivityTeacher;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.reqVo.ActivityNamelistVO;
import com.xuequ.cmoc.service.IActivityService;
import com.xuequ.cmoc.utils.DateUtil;
import com.xuequ.cmoc.view.ActivityInfoView;

@Service("activityService")
public class ActivityServiceImpl implements IActivityService {

	@Autowired
	private ActivityInfoMapper activityInfoMapper;
	
	@Override
	public List<ActivityInfoView> selectListByPage(Page<ActivityInfoView> page) {
		return activityInfoMapper.selectListByPage(page);
	}

	@Override
	public ActivityInfo selectById(Integer id) {
		return activityInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public void addAndUpdateActivity(ActivityInfo vo, SysUser sysUser) {
		vo.setYear(DateUtil.getYear(vo.getStartDate()));
		vo.setMonth(DateUtil.getMonth(vo.getStartDate()));
		if(vo.getId() == null) {
			vo.setCreaterUserId(sysUser.getIdUser());
			vo.setCreater(sysUser.getUserName());
			vo.setCreateTime(new Date());
			activityInfoMapper.insertSelective(vo);
		}else {
			vo.setUpdaterUserId(sysUser.getIdUser());
			vo.setUpdater(sysUser.getUserName());
			vo.setUpdaterTime(new Date());
			activityInfoMapper.updateByPrimaryKeySelective(vo);
		}
	}

	@Override
	public RspResult addActivityNamelist(List<ActivityNamelistVO> list, SysUser user) {
		ActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(list.get(0).getActivityId());
		if(activityInfo == null) {
			return new RspResult(StatusEnum.ACTIVITY_NOT_EXISTS);
		}
		if(activityInfo.getIsDelete().equals("Y")) {
			return new RspResult(StatusEnum.ACTIVITY_INVALID);
		}
		if(activityInfo.getEndDate() != null && 
				DateUtil.compare(new Date(), activityInfo.getEndDate()) >= 0) {
			return new RspResult(StatusEnum.ACTIVITY_OVER);
		}
		List<ActivityFamily> familyList;
		List<ActivityHmSign> hmSignList;
		List<ActivityTeacher> teacherList;
		List<ActivityMarines> marinesList;
		for(ActivityNamelistVO vo : list) {
			
		}
		return null;
	}

}
