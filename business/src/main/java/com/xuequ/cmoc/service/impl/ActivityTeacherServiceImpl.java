package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.ActivityTeacherMapper;
import com.xuequ.cmoc.model.ActivityTeacher;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.IActivityTeacherService;
import com.xuequ.cmoc.view.ActivityTeacherView;

@Service("activityTeacherService")
public class ActivityTeacherServiceImpl implements IActivityTeacherService {
	
	@Autowired
	private ActivityTeacherMapper activityTeacherMapper;
	@Override
	public List<ActivityTeacherView> selectListByPage(Page<ActivityTeacherView> page) {
		return activityTeacherMapper.selectListByPage(page);
	}

}
