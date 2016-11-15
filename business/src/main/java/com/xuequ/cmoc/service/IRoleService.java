package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.model.SysRole;
import com.xuequ.cmoc.page.Page;

public interface IRoleService {
	
	/**
	 * 分页查询角色
	 * @param page
	 * @return
	 */
	public List<SysRole> selectListByPage(Page<SysRole> page);
	
}
