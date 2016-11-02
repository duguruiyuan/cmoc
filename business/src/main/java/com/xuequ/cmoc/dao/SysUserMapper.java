package com.xuequ.cmoc.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.xuequ.cmoc.model.SysUser;

@MapperScan
public interface SysUserMapper {

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String idUser);

    int updateByPrimaryKeySelective(SysUser record);

    SysUser selectUser(@Param("userAccount")String userAccount, @Param("password")String password);
}