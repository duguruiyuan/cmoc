package com.xuequ.cmoc.dao;

import com.xuequ.cmoc.model.CourseBuyerInfo;

public interface CourseBuyerInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseBuyerInfo record);

    int insertSelective(CourseBuyerInfo record);

    CourseBuyerInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseBuyerInfo record);

    int updateByPrimaryKey(CourseBuyerInfo record);
}