package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.model.SysResource;
import com.xuequ.cmoc.model.SysRole;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.reqVo.AddAndUpdateRoleVO;
import com.xuequ.cmoc.view.SysRoleInfo;

public interface IRoleService {
	
	/**
	 * 分页查询角色
	 * @param page
	 * @return
	 */
	public List<SysRole> selectListByPage(Page<SysRole> page);
	
	/**
	 * 新增修改角色
	 */
	public int addAndUpdateRole(AddAndUpdateRoleVO vo, SysUser sysUser);
	
	/**
	 * 查询角色信息
	 * @auther 胡启萌
	 * @Date 2016年11月15日
	 * @param idRole
	 * @return
	 */
	public SysRoleInfo selectRoleInfo(Integer idRole);
	
	/**
	 * 
	 * @auther 胡启萌
	 * @Date 2016年11月15日
	 * @param userId
	 * @return
	 */
	public List<SysRole> selectSysRoleAll();
}
