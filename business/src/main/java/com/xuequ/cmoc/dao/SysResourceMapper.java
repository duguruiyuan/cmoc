package com.xuequ.cmoc.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.xuequ.cmoc.model.SysResource;

@MapperScan
public interface SysResourceMapper {

    int insertSelective(SysResource record);

    SysResource selectByPrimaryKey(Integer idResource);

    int updateByPrimaryKeySelective(SysResource record);

    List<SysResource> selectByUserId(String userId);
    
    List<SysResource> selectAll();
}