package com.xuequ.cmoc.dao;

import com.xuequ.cmoc.model.ActivityInfo;

public interface ActivityInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityInfo record);

    int insertSelective(ActivityInfo record);

    ActivityInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityInfo record);

    int updateByPrimaryKey(ActivityInfo record);
}