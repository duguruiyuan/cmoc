package com.xuequ.cmoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController{

	@RequestMapping(value = {"", "/"})
	public String index() {
		return "index";
	}
	
	@RequestMapping("class")
	public String classz() {
		return "class";
	}
	
	@RequestMapping("my")
	public String my() {
		return "my";
	}
	
	@RequestMapping("about")
	public String about() {
		return "about";
	}
}
