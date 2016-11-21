package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.ActivityFamilyMapper;
import com.xuequ.cmoc.model.ActivityFamily;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.IActivityFamilyService;
import com.xuequ.cmoc.view.ActivityFamilyView;

@Service("activityFamilyService")
public class ActivityFamilyServiceImpl implements IActivityFamilyService {
	
	@Autowired
	private ActivityFamilyMapper activityFamilyMapper;

	@Override
	public List<ActivityFamilyView> selectListByPage(Page<ActivityFamilyView> page) {
		return activityFamilyMapper.selectListByPage(page);
	}

	@Override
	public List<ActivityFamily> selectListByMarineId(Integer marineId) {
		return activityFamilyMapper.selectListByMarineId(marineId);
	}

}
