package com.xuequ.cmoc.dao;

import com.xuequ.cmoc.model.SysUserRoleRel;

public interface SysUserRoleRelMapper {
    int deleteByPrimaryKey(Integer idUserRoleRel);

    int insert(SysUserRoleRel record);

    int insertSelective(SysUserRoleRel record);

    SysUserRoleRel selectByPrimaryKey(Integer idUserRoleRel);

    int updateByPrimaryKeySelective(SysUserRoleRel record);

    int updateByPrimaryKey(SysUserRoleRel record);
}