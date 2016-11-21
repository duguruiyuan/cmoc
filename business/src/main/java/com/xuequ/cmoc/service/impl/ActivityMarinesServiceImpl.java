package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.ActivityMarinesMapper;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.IActivityMarinesService;
import com.xuequ.cmoc.view.ActivityMarinesView;

@Service("activityMarinesService")
public class ActivityMarinesServiceImpl implements IActivityMarinesService {

	@Autowired
	private ActivityMarinesMapper activityMarinesMapper;
	
	@Override
	public List<ActivityMarinesView> selectListByPage(Page<ActivityMarinesView> page) {
		return activityMarinesMapper.selectListByPage(page);
	}

}
