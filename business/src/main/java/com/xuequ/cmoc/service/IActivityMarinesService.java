package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.model.ActivityMarines;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.ActivityMarinesView;

public interface IActivityMarinesService {
	
	public List<ActivityMarinesView> selectListByPage(Page<ActivityMarinesView> page);

	public List<ActivityMarines> selectListByActivityId(Integer activityId);

	public ActivityMarines selectById(Integer marineId);
	
	public int updateMarineImg(String imgUrl, Integer id);
	
	public ActivityMarines selectByPrimaryKey(Integer id);
	
	public int addUpdateMarines(ActivityMarines marines);
	
	public RspResult updateHmBindMarine(Integer marineId, String openid);
	/**
	 * 查询活动战队、队员信息
	 * @auther 胡启萌
	 * @Date 2016年12月4日
	 * @param activityId
	 * @return
	 */
	List<ActivityMarinesView> selectMarineTeam(Integer activityId);
}
