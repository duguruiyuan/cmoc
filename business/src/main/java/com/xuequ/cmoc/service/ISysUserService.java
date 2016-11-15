package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.reqVo.AddAndUpdateUserVO;
import com.xuequ.cmoc.view.SysUserInfo;

public interface ISysUserService {

	/**
	 * 分页查询用户
	 * @param page
	 * @return
	 */
	public List<SysUser> selectListByPage(Page<SysUser> page);
	
	/**
	 * 新增修改用户
	 */
	public int addAndUpdateUser(AddAndUpdateUserVO vo, SysUser sysUser);
	
	/**
	 * 查询角色信息
	 * @auther 胡启萌
	 * @Date 2016年11月15日
	 * @param idRole
	 * @return
	 */
	public SysUserInfo selectUserInfo(Integer idUser);
	
}
