package com.xuequ.cmoc.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.xuequ.cmoc.model.SysRole;

@MapperScan
public interface SysRoleMapper {

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer idRole);

    int updateByPrimaryKeySelective(SysRole record);

}