package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.model.ActivityMarines;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.ActivityMarinesView;

public interface IActivityMarinesService {
	
	public List<ActivityMarinesView> selectListByPage(Page<ActivityMarinesView> page);

	public List<ActivityMarines> selectListByActivityId(Integer activityId);

	public ActivityMarines selectById(Integer marineId);

}
