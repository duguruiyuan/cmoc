package com.xuequ.cmoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.vo.CourseParamsVO;

@RequestMapping("ueditor")
@Controller
public class UeditorController extends BaseController {

	@RequestMapping("course/edit")
	@ResponseBody Object courseEdit(CourseParamsVO vo) {
		return null;
	}
	
	
}
