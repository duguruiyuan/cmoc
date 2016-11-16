package com.xuequ.cmoc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.SysResourceMapper;
import com.xuequ.cmoc.dao.SysResourceRoleRelMapper;
import com.xuequ.cmoc.dao.SysRoleMapper;
import com.xuequ.cmoc.model.SysResource;
import com.xuequ.cmoc.model.SysResourceRoleRel;
import com.xuequ.cmoc.model.SysRole;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.reqVo.AddAndUpdateRoleVO;
import com.xuequ.cmoc.service.IRoleService;
import com.xuequ.cmoc.utils.BeanUtils;
import com.xuequ.cmoc.view.SysRoleInfo;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysResourceRoleRelMapper sysResourceRoleRelMapper;
	@Autowired
	private SysResourceMapper sysResourceMapper;
	
	@Override
	public List<SysRole> selectListByPage(Page<SysRole> page) {
		return sysRoleMapper.selectListByPage(page);
	}

	@Override
	public int addAndUpdateRole(AddAndUpdateRoleVO vo, SysUser sysUser) {
		SysRole role = BeanUtils.copyAs(vo, SysRole.class);
		if(vo.getIdRole() == null) {
			role.setCreator(sysUser.getUserAccount());
			role.setCreateTime(new Date());
			role.setRoleName(vo.getRoleName());
			role.setRoleDesc(vo.getRoleDesc());
			sysRoleMapper.insertSelective(role);
		}else {
			role.setIdRole(vo.getIdRole());
			role.setLastUpdator(sysUser.getUserAccount());
			role.setLastUpdateTime(new Date());
			role.setRoleName(vo.getRoleName());
			role.setRoleDesc(vo.getRoleDesc());
			sysRoleMapper.updateByPrimaryKeySelective(role);
			sysResourceRoleRelMapper.deleteByIdRole(role.getIdRole());
		}
		vo.setIdRole(role.getIdRole());
		return sysResourceRoleRelMapper.insertBatch(vo);
	}

	@Override
	public SysRoleInfo selectRoleInfo(Integer idRole) {
		SysRole sysRole = sysRoleMapper.selectByPrimaryKey(idRole);
		SysRoleInfo info = BeanUtils.copyAs(sysRole, SysRoleInfo.class);
		List<SysResourceRoleRel> relList = sysResourceRoleRelMapper.selectListByIdRole(idRole);
		info.setRelList(relList);
		return info;
	}

	@Override
	public List<SysRole> selectSysRoleAll() {
		return sysRoleMapper.selectRoleAll();
	}

}
