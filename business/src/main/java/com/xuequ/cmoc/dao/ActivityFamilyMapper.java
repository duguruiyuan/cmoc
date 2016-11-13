package com.xuequ.cmoc.dao;

import com.xuequ.cmoc.model.ActivityFamily;

public interface ActivityFamilyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityFamily record);

    int insertSelective(ActivityFamily record);

    ActivityFamily selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityFamily record);

    int updateByPrimaryKey(ActivityFamily record);
}