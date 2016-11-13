package com.xuequ.cmoc.dao;

import com.xuequ.cmoc.model.ActivityHmSign;

public interface ActivityHmSignMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityHmSign record);

    int insertSelective(ActivityHmSign record);

    ActivityHmSign selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityHmSign record);

    int updateByPrimaryKey(ActivityHmSign record);
}