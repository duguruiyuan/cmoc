package com.xuequ.cmoc.dao;

import com.xuequ.cmoc.model.ActivityResource;

public interface ActivityResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityResource record);

    int insertSelective(ActivityResource record);

    ActivityResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityResource record);

    int updateByPrimaryKey(ActivityResource record);
}