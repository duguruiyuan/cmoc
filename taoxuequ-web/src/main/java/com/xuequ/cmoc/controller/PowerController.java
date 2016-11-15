package com.xuequ.cmoc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.event.MenuListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.RoleQueryVO;
import com.xuequ.cmoc.auth.AppUser;
import com.xuequ.cmoc.common.Constants;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.model.Grid;
import com.xuequ.cmoc.model.SysMenu;
import com.xuequ.cmoc.model.SysResource;
import com.xuequ.cmoc.model.SysRole;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.reqVo.AddAndUpdateRoleVO;
import com.xuequ.cmoc.service.IRoleService;

/**
 * 权限管理
 * @author 胡启萌
 * @Date 2016年11月14日
 *
 */
@RequestMapping("power")
@Controller
public class PowerController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(PowerController.class);
	
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
	@RequestMapping("role/json/addUpdate")
	@ResponseBody Object roleAdd(AddAndUpdateRoleVO vo) {
		try {
			SysUser sysUser = (SysUser) session.getAttribute(Constants.APP_USER);
			roleService.addAndUpdateRole(vo, sysUser);
			return StatusEnum.success;
		} catch (Exception e) {
			logger.error("----roleAdd, error={}", e);
		}
		return StatusEnum.fail;
	}
	
	/**
	 * 获取角色信息
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @return
	 */
	@RequestMapping("role/json/queryRoleInfo")
	@ResponseBody Object roleUpdate(@RequestParam("idRole") Integer idRole) {
		return roleService.selectRoleInfo(idRole);
	}
	
	/**
	 * 查询所有菜单
	 * @auther 胡启萌
	 * @Date 2016年11月15日
	 * @return
	 */
	@RequestMapping("/json/menuList")
	@ResponseBody Object menuList(){
		return roleService.selectResourceAll();
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
