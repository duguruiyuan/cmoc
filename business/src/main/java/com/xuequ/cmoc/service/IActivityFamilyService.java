package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.model.ActivityChild;
import com.xuequ.cmoc.model.ChildSignInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.ActivityChildView;
import com.xuequ.cmoc.view.ActivityFamilyView;

public interface IActivityFamilyService {

	/**
	 * 分页查询名单信息
	 * @param page
	 * @return
	 */
	public List<ActivityChildView> selectListByPage(Page<ActivityChildView> page);

	List<ActivityChildView> selectListByMarineId(Integer marineId);
	
	public int updateFamilyImg(String imgUrl, Integer id);
	
	public int addAndUpdateSignChild(ChildSignInfo child);
	
	ActivityChildView selectById(Integer id);
	
	ActivityChild selectByPrimaryKey(Integer id);
}
