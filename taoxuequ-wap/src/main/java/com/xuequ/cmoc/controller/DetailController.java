package com.xuequ.cmoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DetailController {

	/**
	 * 课程详情
	 * @param id 课程编号
	 * @return
	 */
	@RequestMapping("course/detail/{id}")
	public String courseDetail(@PathVariable String id, Model model) {
		model.addAttribute("type", id);
		return "course/detail";
	}
}
