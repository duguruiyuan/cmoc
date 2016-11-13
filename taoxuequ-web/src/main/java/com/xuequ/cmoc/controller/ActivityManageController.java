package com.xuequ.cmoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xuequ.cmoc.model.ActivityInfo;

@RequestMapping("activity")
@Controller
public class ActivityManageController {

	@RequestMapping("manage")
	public String manage() {
		return "activity/query";
	}
	
	@RequestMapping("add")
	@ResponseBody Object addActivity(ActivityInfo info) {
		return null;
	}
	
	@RequestMapping("update")
	@ResponseBody Object updateActivity(ActivityInfo info) {
		return null;
	}
	
	@RequestMapping("manage/json/query")
	@ResponseBody Object manageQuery() {
		return null;
	}
	
	@RequestMapping("user/import")
	ModelAndView userImport() {
		ModelAndView mv = new ModelAndView();
		
		return mv;
	}
}
