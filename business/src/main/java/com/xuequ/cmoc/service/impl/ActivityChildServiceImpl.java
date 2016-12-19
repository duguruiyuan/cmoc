package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.ActivityChildMapper;
import com.xuequ.cmoc.model.ActivityChild;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.IActivityChildService;
import com.xuequ.cmoc.view.ActivityChildView;

@Service("activityFamilyService")
public class ActivityChildServiceImpl implements IActivityChildService {
	
	@Autowired
	private ActivityChildMapper activityChildMapper;

	@Override
	public List<ActivityChildView> selectListByPage(Page<ActivityChildView> page) {
		return activityChildMapper.selectListByPage(page);
	}

	@Override
	public List<ActivityChildView> selectListByMarineId(Integer marineId) {
		return activityChildMapper.selectListByMarineId(marineId);
	}

	@Override
	public int updateFamilyImg(String imgUrl, Integer id) {
		ActivityChild child = new ActivityChild();
		child.setChildImg(imgUrl);
		child.setId(id);
		return activityChildMapper.updateByPrimaryKeySelective(child);
	}

	@Override
	public int addAndUpdateChild(ActivityChild child) {
		return activityChildMapper.updateByPrimaryKeySelective(child);
	}

	@Override
	public ActivityChildView selectById(Integer id) {
		return activityChildMapper.selectById(id);
	}

	@Override
	public ActivityChild selectByPrimaryKey(Integer id) {
		return activityChildMapper.selectByPrimaryKey(id);
	}

}
