package com.xuequ.cmoc.dao;

import com.xuequ.cmoc.model.SysUser;

public interface SysUserMapper {

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String idUser);

    int updateByPrimaryKeySelective(SysUser record);

    SysUser selectUser(String userAccount, String password);
}