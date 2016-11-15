package com.xuequ.cmoc.view;

import java.io.Serializable;
import java.util.List;

import com.xuequ.cmoc.model.SysRole;
import com.xuequ.cmoc.model.SysUser;

public class SysUserInfo extends SysUser implements Serializable {

	private static final long serialVersionUID = -2728147859552162099L;
	
	private List<SysRole> roleList;

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}
	
	
}
