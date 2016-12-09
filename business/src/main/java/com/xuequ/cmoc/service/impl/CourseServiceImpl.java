package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.CourseInfoMapper;
import com.xuequ.cmoc.model.CourseInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.ICourseService;

@Service("courseService")
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private CourseInfoMapper courseInfoMapper;
	
	@Override
	public List<CourseInfo> selectListByPage(Page<CourseInfo> page) {
		return courseInfoMapper.selectListByPage(page);
	}

	@Override
	public CourseInfo selectByPrimaryKey(Integer id) {
		return courseInfoMapper.selectByPrimaryKey(id);
	}

}
