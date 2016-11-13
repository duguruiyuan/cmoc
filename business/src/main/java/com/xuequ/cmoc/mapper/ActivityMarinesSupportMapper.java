package com.xuequ.cmoc.mapper;

import com.xuequ.cmoc.model.ActivityMarinesSupport;

public interface ActivityMarinesSupportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityMarinesSupport record);

    int insertSelective(ActivityMarinesSupport record);

    ActivityMarinesSupport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityMarinesSupport record);

    int updateByPrimaryKey(ActivityMarinesSupport record);
}