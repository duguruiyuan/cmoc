package com.xuequ.cmoc.dao;

import java.util.List;

import com.xuequ.cmoc.model.ActivityTeacher;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.ActivityTeacherView;

public interface ActivityTeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityTeacher record);

    int insertSelective(ActivityTeacher record);

    ActivityTeacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityTeacher record);

    int updateByPrimaryKey(ActivityTeacher record);
    
    List<ActivityTeacherView> selectListByPage(Page<ActivityTeacherView> page);
}