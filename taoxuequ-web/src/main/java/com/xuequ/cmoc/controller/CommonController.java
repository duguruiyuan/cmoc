package com.xuequ.cmoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("common")
public class CommonController {

	@RequestMapping("home")
	public String home() {
		return "home";
	}
}
