package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.model.CourseInfo;
import com.xuequ.cmoc.page.Page;

public interface ICourseService {

	List<CourseInfo> selectListByPage(Page<CourseInfo> page);
	
	CourseInfo selectByPrimaryKey(Integer id);
}
