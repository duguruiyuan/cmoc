package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.ActivityFamilyView;

public interface IActivityFamilyService {

	/**
	 * 分页查询名单信息
	 * @param page
	 * @return
	 */
	public List<ActivityFamilyView> selectListByPage(Page<ActivityFamilyView> page);
}
