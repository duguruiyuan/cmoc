package com.xuequ.cmoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("hm")
@Controller
public class HollowManController extends BaseController {

	@RequestMapping(value={"","/"})
	public String index(Model model) {
		return wechatRedirect(model, "hm");
	}
}
