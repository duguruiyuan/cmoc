package com.xuequ.cmoc.dao;

import com.xuequ.cmoc.model.ActivityTeacher;

public interface ActivityTeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityTeacher record);

    int insertSelective(ActivityTeacher record);

    ActivityTeacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityTeacher record);

    int updateByPrimaryKey(ActivityTeacher record);
}