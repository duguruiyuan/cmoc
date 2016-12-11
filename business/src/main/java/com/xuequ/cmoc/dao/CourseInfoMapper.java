package com.xuequ.cmoc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuequ.cmoc.model.CourseInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.CourseListView;

public interface CourseInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseInfo record);

    int insertSelective(CourseInfo record);

    CourseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseInfo record);

    int updateByPrimaryKeyWithBLOBs(CourseInfo record);

    int updateByPrimaryKey(CourseInfo record);
    
    List<CourseInfo> selectListByPage(Page<CourseInfo> page);
    
    int updateShelves(@Param("shelves")Integer shelves, @Param("id")Integer id);
    
    int delCourse(Integer id);
    
    List<CourseListView> selectShelvesList();
    
    CourseListView selectDetailById(Integer id);
}