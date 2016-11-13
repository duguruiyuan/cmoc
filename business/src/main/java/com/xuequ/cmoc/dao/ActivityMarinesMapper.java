package com.xuequ.cmoc.dao;

import com.xuequ.cmoc.model.ActivityMarines;

public interface ActivityMarinesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityMarines record);

    int insertSelective(ActivityMarines record);

    ActivityMarines selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityMarines record);

    int updateByPrimaryKey(ActivityMarines record);
}