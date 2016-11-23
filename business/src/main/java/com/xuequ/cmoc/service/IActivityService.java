package com.xuequ.cmoc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.reqVo.ActivityNamelistVO;
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
	
	public List<ActivityInfo> selectListByParam(Page<ActivityInfo> page);
	
	ActivityResourceTypeView selectForUpload1(Integer activityId, String marineName, String childName);
    
    ActivityResourceTypeView selectForUpload(Integer activityId, String name);

    public ActivityInfo selectByPrimaryKey(Integer id);
    
    int updateActivityImg(String activityImg, Integer id);
}
