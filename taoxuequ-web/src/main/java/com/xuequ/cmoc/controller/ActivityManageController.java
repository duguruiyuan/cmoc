package com.xuequ.cmoc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xuequ.cmoc.common.Constants;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.model.Grid;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.IActivityService;
import com.xuequ.cmoc.view.ActivityInfoView;
import com.xuequ.cmoc.vo.ActivityQueryVO;
import com.xuequ.cmoc.vo.ActivitySubmitVO;

/**
 * 活动管理
 * @author 胡启萌
 * @Date 2016年11月14日
 *
 */
@RequestMapping("activity")
@Controller
public class ActivityManageController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(ActivityManageController.class);

	@Autowired
	private IActivityService activityService;
	
	/**
	 * 活动管理页
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @return
	 */
	@RequestMapping("manage")
	public String manage() {
		return "activity/index";
	}
	
	@RequestMapping("namelist")
	public String namelist() {
		return "activity/namelist";
	}
	
	/**
	 * 活动查询
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @return
	 */
	@RequestMapping("json/query")
	@ResponseBody Object manageQuery(ActivityQueryVO vo) {
		Page<ActivityInfoView> page = new Page<ActivityInfoView>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("activityType", vo.getActivityType());
		paramMap.put("activityName", vo.getActivityName());
		paramMap.put("startDate", vo.getStartDate());
		paramMap.put("endDate", vo.getEndDate());
		Grid grid = new Grid();
		page.setParams(paramMap);
		page.setPageNo(vo.getPage());
		page.setPageSize(vo.getRows());
		List<ActivityInfoView> list = activityService.selectListByPage(page);
		grid.setRows(list);
		grid.setTotal(page.getTotalRecord());
		return grid;
	}
	
	@RequestMapping("json/queryById")
	@ResponseBody Object queryById(@RequestParam("id") Integer id){
		return activityService.selectById(id);
	}
	
	@RequestMapping("json/addUpdate")
	@ResponseBody Object addUpdate(ActivitySubmitVO vo){
		try {
			SysUser sysUser = (SysUser) session.getAttribute(Constants.APP_USER);
			activityService.addAndUpdateActivity(vo, sysUser);
			return new RspResult(StatusEnum.success);
		} catch (Exception e) {
			logger.error("----addUpdate, error={}", e);
		}
		return new RspResult(StatusEnum.fail);
	}
	
	/**
	 * 学生用户导入
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @return
	 */
	@RequestMapping("namelist/import")
	@ResponseBody Object namelistImport(@RequestParam(value="id") Integer id, 
			@RequestParam(value="files",required=false) MultipartFile buildInfos) {
		try {
			
			return new RspResult(StatusEnum.success);
		} catch (Exception e) {
			logger.error("--namelistImport, error={}", e);
		}
		return new RspResult(StatusEnum.fail);
	}
	
	@RequestMapping("json/namelist/query")
	@ResponseBody Object namelistQuery(ActivityQueryVO vo) {
		Page<ActivityInfoView> page = new Page<ActivityInfoView>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("activityType", vo.getActivityType());
		paramMap.put("activityName", vo.getActivityName());
		paramMap.put("startDate", vo.getStartDate());
		paramMap.put("endDate", vo.getEndDate());
		Grid grid = new Grid();
		page.setParams(paramMap);
		page.setPageNo(vo.getPage());
		page.setPageSize(vo.getRows());
		List<ActivityInfoView> list = activityService.selectListByPage(page);
		grid.setRows(list);
		grid.setTotal(page.getTotalRecord());
		return grid;
	}
	
	
}
