package com.xuequ.cmoc.dao;

import java.util.List;

import com.xuequ.cmoc.model.SysResource;

public interface SysResourceMapper {

    int insertSelective(SysResource record);

    SysResource selectByPrimaryKey(Integer idResource);

    int updateByPrimaryKeySelective(SysResource record);

    List<SysResource> selectByUserId(String userId);
    
    List<SysResource> selectAll();
    
}