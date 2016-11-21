package com.xuequ.cmoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.service.IActivityFamilyService;
import com.xuequ.cmoc.service.IActivityMarinesService;
import com.xuequ.cmoc.service.IActivityService;

@RequestMapping("live")
@Controller
public class LiveCenterController {
	
	@Autowired
	private IActivityService activityService;
	@Autowired
	private IActivityMarinesService activityMarinesService;
	@Autowired
	private IActivityFamilyService activityFamilyService;

	@RequestMapping(value={"","/"})
	public String liveCenter(Model model) {
		return "live/liveCenter";
	}
	
	public @ResponseBody Object activityListQuery(ActivityInfo vo) {
		return activityService.selectListByParam(page);
	}
	
	@RequestMapping("marine/list/{activityId}")
	public String marineList(Model model, @PathVariable Integer activityId) {
		model.addAttribute("activity", activityService.selectById(activityId));
		model.addAttribute("marines", activityMarinesService.selectListByActivityId(activityId));
		return "live/marinesList";
	}
	
	@RequestMapping("marine/detail/{marineId}")
	public String marineDetail(Model model, @PathVariable Integer marineId) {
		model.addAttribute("marine", activityMarinesService.selectById(marineId));
		model.addAttribute("familys", activityFamilyService.selectListByMarineId(marineId));
		return "live/marineDetail";
	}
	
}
