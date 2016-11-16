package com.xuequ.cmoc.view;

import java.io.Serializable;
import java.util.List;

import com.xuequ.cmoc.model.SysRole;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.model.SysUserRoleRel;

public class SysUserInfo extends SysUser implements Serializable {

	private static final long serialVersionUID = -2728147859552162099L;
	
	private List<SysUserRoleRel> relList;

	public List<SysUserRoleRel> getRelList() {
		return relList;
	}

	public void setRelList(List<SysUserRoleRel> relList) {
		this.relList = relList;
	}

	
}
