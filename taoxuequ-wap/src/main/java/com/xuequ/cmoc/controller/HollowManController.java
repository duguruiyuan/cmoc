package com.xuequ.cmoc.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("hm")
@Controller
public class HollowManController extends BaseController {

	@RequestMapping(value={"","/"})
	public String index(Model model) {
		String code = request.getParameter("code");
		if(StringUtils.isBlank(code)) {
			
		}else {
			
		}
		return "hm";
	}
}
