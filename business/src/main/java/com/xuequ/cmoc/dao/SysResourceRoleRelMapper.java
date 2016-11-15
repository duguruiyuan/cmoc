package com.xuequ.cmoc.dao;

import java.util.List;

import com.xuequ.cmoc.model.SysResourceRoleRel;
import com.xuequ.cmoc.reqVo.AddAndUpdateRoleVO;

public interface SysResourceRoleRelMapper {
    int deleteByPrimaryKey(Integer idResourceRoleRel);

    int insert(SysResourceRoleRel record);

    int insertSelective(SysResourceRoleRel record);

    SysResourceRoleRel selectByPrimaryKey(Integer idResourceRoleRel);

    int updateByPrimaryKeySelective(SysResourceRoleRel record);

    int updateByPrimaryKey(SysResourceRoleRel record);
    
    int deleteByIdRole(Integer idRole);
    
    int insertBatch(AddAndUpdateRoleVO vo);
    
    List<SysResourceRoleRel> selectListByIdRole(Integer idRole);
}