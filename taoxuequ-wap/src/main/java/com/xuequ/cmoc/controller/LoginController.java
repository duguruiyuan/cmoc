package com.xuequ.cmoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("login")
@Controller
public class LoginController {

	@RequestMapping(value = {"","/"})
	public String login() {
		return "login";
	}
}
