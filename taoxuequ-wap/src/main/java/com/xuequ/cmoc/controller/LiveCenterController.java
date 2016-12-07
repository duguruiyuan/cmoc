package com.xuequ.cmoc.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.logging.Log;
import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.model.Grid;
import com.xuequ.cmoc.model.WechatReceiveMessage;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.IActivityFamilyService;
import com.xuequ.cmoc.service.IActivityMarinesService;
import com.xuequ.cmoc.service.IActivityService;
import com.xuequ.cmoc.service.IWechatMessageService;
import com.xuequ.cmoc.vo.ActivityQueryVO;
import com.xuequ.cmoc.vo.MarineLiveQueryVO;

@RequestMapping("live")
@Controller
public class LiveCenterController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(LiveCenterController.class);
	
	@Autowired
	private IActivityService activityService;
	@Autowired
	private IActivityMarinesService activityMarinesService;
	@Autowired
	private IActivityFamilyService activityFamilyService;
	@Autowired
	private IWechatMessageService wechatMessageService;

	@RequestMapping(value={"","/"})
	public String liveCenter(Model model) {
		return "live/liveCenter";
	}
	
	@RequestMapping("/json/activity/query")
	public @ResponseBody Object activityListQuery(ActivityQueryVO vo) {
		vo.setStartDate(new Date());
		Page<ActivityInfo> page = new Page<>();
		page.setPageNo(vo.getPage());
		page.setPageSize(vo.getRows());
		page.setParams(vo);
		List<ActivityInfo> list = activityService.selectListByParam(page);
		Grid grid = new Grid();
		grid.setRows(list);
		grid.setTotal(page.getTotalRecord());
		return grid;
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
	
	@RequestMapping("marine/resource/query")
	public @ResponseBody Object marineImageQuery(MarineLiveQueryVO vo) {
		try {
			Page<WechatReceiveMessage> page = new Page<WechatReceiveMessage>();
			page.setPageNo(vo.getPage());
			page.setPageSize(3);
			page.setParams(vo);
			List<WechatReceiveMessage> list = wechatMessageService.selectListByPage(page);
			page.setResults(list);
			return page;
		} catch (Exception e) {
			logger.error("--marineImageQuery, error={}", e);
		}
		return null;
	}
	
}
