package com.xuequ.cmoc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.RoleQueryVO;
import com.xuequ.cmoc.model.Grid;
import com.xuequ.cmoc.model.SysMenu;
import com.xuequ.cmoc.model.SysResource;
import com.xuequ.cmoc.model.SysRole;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.IRoleService;

/**
 * 权限管理
 * @author 胡启萌
 * @Date 2016年11月14日
 *
 */
@RequestMapping("power")
@Controller
public class PowerController {
	
	@Autowired
	private IRoleService roleService;

	/**
	 * 角色管理页
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @return
	 */
	@RequestMapping("role")
	public String role() {
		return "power/role";
	}
	
	/**
	 * 角色查询
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @param menu
	 * @return
	 */
	@RequestMapping("role/json/query")
	@ResponseBody Object roleQuery(RoleQueryVO vo) {
		Page<SysRole> page = new Page<SysRole>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Grid grid = new Grid();
		paramMap.put("roleName", vo.getRoleName());
		page.setParams(paramMap);
		page.setPageNo(vo.getPage());
		page.setPageSize(vo.getRows());
		List<SysRole> list = roleService.selectListByPage(page);
		grid.setRows(list);
		grid.setTotal(page.getTotalRecord());
		return grid;
	}
	
	/**
	 * 新增角色
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @param role
	 * @return
	 */
	@RequestMapping("role/json/add")
	@ResponseBody Object roleAdd(SysRole role) {
		return null;
	}
	
	/**
	 * 修改角色
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @return
	 */
	@RequestMapping("role/json/update")
	@ResponseBody Object roleUpdate() {
		return null;
	}
	
	/**
	 * 用户管理页
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @return
	 */
	@RequestMapping("user")
	public String user() {
		return "power/user";
	}
	
	/**
	 * 用户查询
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @param menu
	 * @return
	 */
	@RequestMapping("user/json/query")
	@ResponseBody Object userQuery(SysMenu menu) {
		return null;
	}
	
	/**
	 * 新增用户
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @param user
	 * @return
	 */
	@RequestMapping("user/json/add")
	@ResponseBody Object userAdd(SysUser user) {
		return null;
	}
	
	/**
	 * 修改用户信息
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @param user
	 * @return
	 */
	@RequestMapping("user/json/update")
	@ResponseBody Object userUpdate(SysUser user) {
		return null;
	}
	
	/**
	 * 菜单管理页
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @return
	 */
	@RequestMapping("menu")
	public String menu() {
		return "power/menu";
	}
	
	/**
	 * 新增菜单
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @param resource
	 * @return
	 */
	@RequestMapping("menu/json/add")
	@ResponseBody Object menuAdd(SysResource resource) {
		return null;
	}
	
	/**
	 * 修改菜单信息
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @param resource
	 * @return
	 */
	@RequestMapping("menu/json/update")
	@ResponseBody Object menuUpdate(SysResource resource) {
		return null;
	}
	
	/**
	 * 菜单查询
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @param menu
	 * @return
	 */
	@RequestMapping("menu/json/query")
	@ResponseBody Object menuQuery(SysMenu menu) {
		return null;
	}
	
}
