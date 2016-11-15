package com.xuequ.cmoc;

import java.io.Serializable;

import com.xuequ.cmoc.model.GridBase;

public class RoleQueryVO extends GridBase implements Serializable {

	private static final long serialVersionUID = -4951724878289640248L;

	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
