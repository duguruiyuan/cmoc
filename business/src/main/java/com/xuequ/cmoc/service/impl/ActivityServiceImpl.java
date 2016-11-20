package com.xuequ.cmoc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.ActivityInfoMapper;
import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
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

}
