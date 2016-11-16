package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.model.SysResource;

public interface ISysMenuService {
	
	/**
	 *查询所有菜单
	 * @return
	 */
	List<SysResource> queryAllMenu();

}
