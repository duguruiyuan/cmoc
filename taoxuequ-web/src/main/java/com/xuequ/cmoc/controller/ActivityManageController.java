package com.xuequ.cmoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xuequ.cmoc.model.ActivityInfo;

/**
 * 活动管理
 * @author 胡启萌
 * @Date 2016年11月14日
 *
 */
@RequestMapping("activity")
@Controller
public class ActivityManageController {

	/**
	 * 活动管理页
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @return
	 */
	@RequestMapping("manage")
	public String manage() {
		return "activity/index";
	}
	
	/**
	 * 新增活动
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @param info
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody Object addActivity(ActivityInfo info) {
		return null;
	}
	
	/**
	 * 活动信息修改
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @param info
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody Object updateActivity(ActivityInfo info) {
		return null;
	}
	
	/**
	 * 活动查询
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @return
	 */
	@RequestMapping("json/query")
	@ResponseBody Object manageQuery() {
		return null;
	}
	
	/**
	 * 学生用户导入
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @return
	 */
	@RequestMapping("user/import")
	ModelAndView userImport() {
		ModelAndView mv = new ModelAndView();
		
		return mv;
	}
	
	
}
