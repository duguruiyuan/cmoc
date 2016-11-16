package com.xuequ.cmoc.dao;

import java.util.List;

import com.xuequ.cmoc.model.SysUserRoleRel;
import com.xuequ.cmoc.reqVo.AddAndUpdateUserVO;

public interface SysUserRoleRelMapper {
    int deleteByPrimaryKey(Integer idUserRoleRel);

    int insert(SysUserRoleRel record);

    int insertSelective(SysUserRoleRel record);

    SysUserRoleRel selectByPrimaryKey(Integer idUserRoleRel);

    int updateByPrimaryKeySelective(SysUserRoleRel record);

    int updateByPrimaryKey(SysUserRoleRel record);
    
    List<SysUserRoleRel> selectListByIdUser(Integer idUser);
    
    int deleteByIdUser(Integer idUser);
    
    int insertBatch(AddAndUpdateUserVO vo);
}