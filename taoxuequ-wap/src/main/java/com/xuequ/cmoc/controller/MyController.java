package com.xuequ.cmoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("my")
@Controller
public class MyController {
	
	@RequestMapping("course/list")
	public String courseList(Model model) {
		return "my";
	}

}
