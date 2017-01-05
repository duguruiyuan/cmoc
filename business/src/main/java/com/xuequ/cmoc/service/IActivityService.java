package com.xuequ.cmoc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.reqVo.ActivityNamelistVO;
import com.xuequ.cmoc.view.ActivityHmSignView;
import com.xuequ.cmoc.view.ActivityInfoView;
import com.xuequ.cmoc.view.ActivityResourceTypeView;

public interface IActivityService {

	/**
	 * 分页查询活动信息
	 * @param page
	 * @return
	 */
	public List<ActivityInfoView> selectListByPage(Page<ActivityInfoView> page);
	/**
	 * 根据活动id查询活动信息
	 * @param id
	 * @return
	 */
	public ActivityInfo selectById(Integer id);
	
	/**
	 * 新增或变更活动信息
	 * @param vo
	 * @param user
	 */
	public void addAndUpdateActivity(ActivityInfo vo, SysUser user);
	/**
	 * 名单导入
	 * @param list
	 * @param user
	 * @return
	 */
	public RspResult addImportActivityNamelist(List<ActivityNamelistVO> list, SysUser user);
	/**
	 * 分页查询活动信息
	 * @auther 胡启萌
	 * @Date 2016年12月4日
	 * @param page
	 * @return
	 */
	public List<ActivityInfo> selectListByParam(Page<ActivityInfo> page);
	/**
	 * 查询排期的活动
	 * @auther 胡启萌
	 * @Date 2016年12月4日
	 * @param info
	 * @return
	 */
	public List<ActivityInfo> selectScheduActivity(ActivityInfo info);
	/**
	 * 查询进行中的活动
	 * @auther 胡启萌
	 * @Date 2016年12月7日
	 * @param info
	 * @return
	 */
	public List<ActivityInfo> selectBeginingActivity(ActivityInfo info);
	
	ActivityResourceTypeView selectForUpload1(Integer activityId, String marineName, String childName);
    
    ActivityResourceTypeView selectForUpload(Integer activityId, String name);

    public ActivityInfo selectByPrimaryKey(Integer id);
    
    int updateActivityImg(String activityImg, Integer id);
    
    int updateByPrimaryKey(ActivityInfo info);
    
}
