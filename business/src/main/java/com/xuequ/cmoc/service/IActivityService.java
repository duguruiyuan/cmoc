package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.reqVo.ActivityNamelistVO;
import com.xuequ.cmoc.view.ActivityInfoView;

public interface IActivityService {

	public List<ActivityInfoView> selectListByPage(Page<ActivityInfoView> page);

	public ActivityInfo selectById(Integer id);
	
	public void addAndUpdateActivity(ActivityInfo vo, SysUser user);

	public RspResult addActivityNamelist(List<ActivityNamelistVO> list, SysUser user);
}
