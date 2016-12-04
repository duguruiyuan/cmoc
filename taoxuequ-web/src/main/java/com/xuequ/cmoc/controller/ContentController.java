package com.xuequ.cmoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("content")
@Controller
public class ContentController extends BaseController{

	@RequestMapping("img/group")
	private String imgGroup() {
		return "content/imgGroup";
	}
	
	@RequestMapping("article/edit")
	private String articleEdit() {
		return "content/articleEdit";
	}
	
	@RequestMapping("keyword")
	public String index() {
		return "content/keyword";
	}
}
