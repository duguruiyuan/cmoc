package com.xuequ.cmoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ListController extends BaseController{

	@RequestMapping("course/list")
	public String courseList() {
		
		return "course/list";
	}
}
