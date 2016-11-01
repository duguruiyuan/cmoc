package com.xuequ.cmoc.dao;

import com.xuequ.cmoc.model.SysRole;

public interface SysRoleMapper {

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer idRole);

    int updateByPrimaryKeySelective(SysRole record);

}