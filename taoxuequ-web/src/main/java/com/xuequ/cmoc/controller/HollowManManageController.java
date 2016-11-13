package com.xuequ.cmoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.model.HollowManInfo;

@RequestMapping("hm")
@Controller
public class HollowManManageController {

	@RequestMapping("query")
	public String query() {
		return "hm/query";
	}
	
	@RequestMapping("resource/query")
	public String resourceQuery() {
		return "hm/resource/query";
	}
	
	@RequestMapping("json/query")
	@ResponseBody Object jsonQuery(HollowManInfo info) {
		return null;
	}
	
	@RequestMapping("resource/json/query")
	@ResponseBody Object resourceJsonQuery(HollowManInfo info) {
		return null;
	}
}
