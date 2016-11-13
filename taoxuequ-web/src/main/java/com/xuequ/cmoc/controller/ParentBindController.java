package com.xuequ.cmoc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.model.ParentInfo;

@RequestMapping("parent")
public class ParentBindController {

	@RequestMapping("bind/query")
	public String bindQuery() {
		return "parent/bindQuery";
	}
	
	@RequestMapping("buy/record")
	public String buyRecord() {
		return "parent/buyRecord";
	}
	
	@RequestMapping("json/bind/query")
	@ResponseBody Object jsonBindQuery(ParentInfo info) {
		return null;
	}
	
	@RequestMapping("json/buy/record")
	@ResponseBody Object jsonBuyRecord(ParentInfo info) {
		return null;
	}
}
