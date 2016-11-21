package com.xuequ.cmoc.dao;

import com.xuequ.cmoc.model.ParentInfo;

public interface ParentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParentInfo record);

    int insertSelective(ParentInfo record);

    ParentInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParentInfo record);

    int updateByPrimaryKey(ParentInfo record);
}