package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.model.ActivityChild;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.ActivityChildView;

public interface IActivityChildService {

	/**
	 * 分页查询名单信息
	 * @param page
	 * @return
	 */
	List<ActivityChildView> selectListByPage(Page<ActivityChildView> page);

	List<ActivityChildView> selectListByMarineId(Integer marineId);
	
	int updateFamilyImg(String imgUrl, Integer id);
	
	int addAndUpdateChild(ActivityChildView view);
	
	ActivityChildView selectById(Integer id);
	
	ActivityChild selectByPrimaryKey(Integer id);
	
	ActivityChildView selectByChildId(Integer childId);
}
