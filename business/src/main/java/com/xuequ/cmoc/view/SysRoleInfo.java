package com.xuequ.cmoc.view;

import java.io.Serializable;
import java.util.List;

import com.xuequ.cmoc.model.SysResource;
import com.xuequ.cmoc.model.SysResourceRoleRel;
import com.xuequ.cmoc.model.SysRole;

public class SysRoleInfo extends SysRole implements Serializable{

	private static final long serialVersionUID = -9040155826366134183L;
	
	private List<SysResourceRoleRel> relList;
	
	public List<SysResourceRoleRel> getRelList() {
		return relList;
	}

	public void setRelList(List<SysResourceRoleRel> relList) {
		this.relList = relList;
	}

}
