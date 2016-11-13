package com.xuequ.cmoc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.model.SysMenu;
import com.xuequ.cmoc.model.SysResource;
import com.xuequ.cmoc.model.SysRole;
import com.xuequ.cmoc.model.SysUser;

@RequestMapping("power")
public class PowerController {

	@RequestMapping("role")
	public String role() {
		return "power/role";
	}
	
	@RequestMapping("role/add")
	@ResponseBody Object roleAdd(SysRole role) {
		return null;
	}
	
	@RequestMapping("role/update")
	@ResponseBody Object roleUpdate() {
		return null;
	}
	
	@RequestMapping("user")
	public String user() {
		return "power/user";
	}
	
	@RequestMapping("user/add")
	@ResponseBody Object userAdd(SysUser user) {
		return null;
	}
	
	@RequestMapping("user/update")
	@ResponseBody Object userUpdate(SysUser user) {
		return null;
	}
	
	@RequestMapping("menu")
	public String menu() {
		return "power/menu";
	}
	
	@RequestMapping("menu/add")
	@ResponseBody Object menuAdd(SysResource resource) {
		return null;
	}
	
	@RequestMapping("menu/update")
	@ResponseBody Object menuUpdate(SysResource resource) {
		return null;
	}
	
	@RequestMapping("menu/json/query")
	@ResponseBody Object menuQuery(SysMenu menu) {
		return null;
	}
	
	@RequestMapping("user/json/query")
	@ResponseBody Object userQuery(SysMenu menu) {
		return null;
	}
	
	@RequestMapping("role/json/query")
	@ResponseBody Object roleQuery(SysMenu menu) {
		return null;
	}
}
