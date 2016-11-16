package com.xuequ.cmoc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;

public interface SysUserMapper {

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer idUser);

    int updateByPrimaryKeySelective(SysUser record);

    SysUser selectUser(@Param("userAccount")String userAccount, @Param("password")String password);
    
    List<SysUser> selectListByPage(Page page);
}