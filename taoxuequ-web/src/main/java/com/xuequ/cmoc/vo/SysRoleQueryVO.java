package com.xuequ.cmoc.vo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.xuequ.cmoc.model.GridBase;

public class SysRoleQueryVO extends GridBase implements Serializable {

	private static final long serialVersionUID = -4951724878289640248L;

	private String roleName;

	public String getRoleName() {
		return StringUtils.isNotBlank(roleName) ? roleName : null;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
