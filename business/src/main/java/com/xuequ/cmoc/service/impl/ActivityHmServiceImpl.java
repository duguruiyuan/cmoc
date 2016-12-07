package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.ActivityHmSignMapper;
import com.xuequ.cmoc.model.ActivityHmSign;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.IActivityHmService;
import com.xuequ.cmoc.view.ActivityHmSignView;

@Service("activityHmService")
public class ActivityHmServiceImpl implements IActivityHmService {

	@Autowired
	private ActivityHmSignMapper activityHmSignMapper;
	
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

}
