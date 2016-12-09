package com.xuequ.cmoc.dao;

import java.util.List;

import com.xuequ.cmoc.model.CourseInfo;
import com.xuequ.cmoc.page.Page;

public interface CourseInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseInfo record);

    int insertSelective(CourseInfo record);

    CourseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseInfo record);

    int updateByPrimaryKeyWithBLOBs(CourseInfo record);

    int updateByPrimaryKey(CourseInfo record);
    
    List<CourseInfo> selectListByPage(Page<CourseInfo> page);
    
}