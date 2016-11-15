package com.xuequ.cmoc.reqVo;

import java.io.Serializable;
import java.util.List;

public class AddAndUpdateRoleVO implements Serializable{
	
	private static final long serialVersionUID = 3884688102611441929L;

	private Integer idRole;
	
	private String roleName;
	
	private String roleDesc;
	
	private List<Integer> menuIds;

	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public List<Integer> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<Integer> menuIds) {
		this.menuIds = menuIds;
	}
	
	

}
