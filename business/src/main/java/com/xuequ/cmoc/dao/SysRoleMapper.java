package com.xuequ.cmoc.dao;

import java.util.List;

import com.xuequ.cmoc.model.SysRole;
import com.xuequ.cmoc.page.Page;

public interface SysRoleMapper {

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer idRole);

    int updateByPrimaryKeySelective(SysRole record);
    
    List<SysRole> selectListByPage(Page<SysRole> page);

}