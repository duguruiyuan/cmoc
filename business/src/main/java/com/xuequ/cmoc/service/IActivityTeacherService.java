package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.ActivityTeacherView;

public interface IActivityTeacherService {

	List<ActivityTeacherView> selectListByPage(Page<ActivityTeacherView> page);
	
}
