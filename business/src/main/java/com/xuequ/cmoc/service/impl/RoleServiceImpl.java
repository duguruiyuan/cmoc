package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.SysRoleMapper;
import com.xuequ.cmoc.model.SysRole;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Override
	public List<SysRole> selectListByPage(Page<SysRole> page) {
		return sysRoleMapper.selectListByPage(page);
	}

}
