package com.xuequ.cmoc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.common.Constants;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.model.BuyRecord;
import com.xuequ.cmoc.model.CourseInfo;
import com.xuequ.cmoc.model.Grid;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.ICourseService;
import com.xuequ.cmoc.view.CourseBuyerView;
import com.xuequ.cmoc.vo.BuyerQueryVO;
import com.xuequ.cmoc.vo.CourseQueryVO;
import com.xuequ.cmoc.vo.CourseSubmitVO;

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
	
	@RequestMapping("buy/record")
	public String buyRecord(){
		return "course/buyRecord";
	}
	
	@RequestMapping("json/buyRecord/list")
	@ResponseBody Object buyRecordList(BuyerQueryVO vo) {
		try {
			Page<CourseBuyerView> page = new Page<>();
			page.setPageNo(vo.getPage());
			page.setPageSize(vo.getRows());
			page.setParams(vo);
			List<CourseBuyerView> list = courseService.selectBuyRecordByPage(page);
			Grid grid = new Grid();
			grid.setRows(list);
			grid.setTotal(page.getTotalRecord());
			return grid;
		} catch (Exception e) {
			logger.error("--buyRecordList, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	@RequestMapping("json/list/query")
	@ResponseBody Object jsonListQuery(CourseQueryVO vo) {
		try {
			Page<CourseInfo> page = new Page<>();
			page.setPageNo(vo.getPage());
			page.setPageSize(vo.getRows());
			page.setParams(vo);
			List<CourseInfo> list = courseService.selectListByPage(page);
			Grid grid = new Grid();
			grid.setRows(list);
			grid.setTotal(page.getTotalRecord());
			return grid;
		} catch (Exception e) {
			logger.error("--jsonListQuery, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	@RequestMapping("json/addUpdate")
	@ResponseBody Object addUpdate(CourseSubmitVO vo) {
		try {
			SysUser user = (SysUser) session.getAttribute(Constants.APP_USER);
			courseService.addAndUpdateCourse(vo, user);
			return new RspResult(StatusEnum.SUCCESS);
		} catch (Exception e) {
			logger.error("--addUpdate, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	@RequestMapping("json/edit")
	public String edit(Model model) {
		String courseId = request.getParameter("id");
		model.addAttribute("courseId", courseId);
		return "course/edit";
	}
	
	@RequestMapping("json/detail/query")
	@ResponseBody Object courseDetail(Integer courseId) {
		return courseService.selectByPrimaryKey(courseId);
	}
	
	@RequestMapping("json/edit/shelves")
	@ResponseBody Object editShelves(Integer shelves, Integer id) {
		try {
			courseService.updateShelves(shelves, id);
			return new RspResult(StatusEnum.SUCCESS);
		} catch (Exception e) {
			logger.error("--editShelves, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
}
