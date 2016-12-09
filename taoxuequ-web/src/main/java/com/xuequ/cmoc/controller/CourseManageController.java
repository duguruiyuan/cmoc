package com.xuequ.cmoc.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.model.CourseInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.ICourseService;
import com.xuequ.cmoc.vo.CourseQueryVO;

@RequestMapping("course")
@Controller
public class CourseManageController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(CourseManageController.class);

	@Autowired
	private ICourseService courseService;
	
	@RequestMapping("manage")
	public String manage() {
		return "course/manage";
	}
	
	@RequestMapping("json/list/query")
	@ResponseBody Object jsonListQuery(CourseQueryVO vo) {
		try {
			Page<CourseInfo> page = new Page<>();
			page.setPageNo(vo.getPage());
			page.setPageSize(vo.getRows());
			page.setParams(vo);
			List<CourseInfo> list = courseService.selectListByPage(page);
			return new RspResult(StatusEnum.SUCCESS, list);
		} catch (Exception e) {
			logger.error("--jsonListQuery, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	@RequestMapping("json/edit")
	public String edit(Model model) {
		String courseId = request.getParameter("id");
		if(StringUtils.isNotBlank(courseId)) {
			model.addAttribute("course", courseService.selectByPrimaryKey(Integer.parseInt(courseId)));
		}
		return "course/edit";
	}
}
