package com.xuequ.cmoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("login")
@Controller
public class LoginController {

	@RequestMapping("/get")
	public @ResponseBody Object get() {
		return "taoxuequ-wap";
	}
}
