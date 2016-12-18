package com.xuequ.cmoc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.model.Grid;
import com.xuequ.cmoc.model.ParentInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.ICourseService;
import com.xuequ.cmoc.service.IParentInfoService;
import com.xuequ.cmoc.view.ChildSignView;
import com.xuequ.cmoc.view.ParentInfoView;
import com.xuequ.cmoc.vo.BuyerQueryVO;
import com.xuequ.cmoc.vo.ParentQueryVO;

/**
 * 家长管理
 * @author 胡启萌
 * @Date 2016年11月14日
 *
 */
@RequestMapping("parent")
@Controller
public class ParentController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(ParentController.class);

	@Autowired
	private IParentInfoService parentInfoService;
	@Autowired
	private ICourseService courseService;
	/**
	 * 家长绑定查询页
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @return
	 */
	@RequestMapping("bind/query")
	public String bindQuery() {
		return "parent/bindQuery";
	}
	
	/**
	 * 查询家长绑定数据
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @param info
	 * @return
	 */
	@RequestMapping("bind/json/query")
	@ResponseBody Object jsonBindQuery(ParentQueryVO vo) {
		try {
			Page<ParentInfo> page = new Page<>();
			page.setPageNo(vo.getPage());
			page.setPageSize(vo.getRows());
			page.setParams(vo);
			List<ParentInfo> list = parentInfoService.selectListByPage(page);
			Grid grid = new Grid();
			grid.setRows(list);
			grid.setTotal(page.getTotalRecord());
			return grid;
		} catch (Exception e) {
			logger.error("--buyRecordList, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	/**
	 * 家长购买记录页
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @return
	 */
	@RequestMapping("buy/record")
	public String buyRecord() {
		return "parent/buyRecord";
	}
	
	/**
	 * 查询家长购买记录
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @param info
	 * @return
	 */
	@RequestMapping("buy/json/record")
	@ResponseBody Object jsonBuyRecord(BuyerQueryVO vo) {
		try {
			Page<ParentInfoView> page = new Page<>();
			page.setPageNo(vo.getPage());
			page.setPageSize(vo.getRows());
			page.setParams(vo);
			List<ParentInfoView> list = parentInfoService.selectBuyTotalByPage(page);
			Grid grid = new Grid();
			grid.setRows(list);
			grid.setTotal(page.getTotalRecord());
			return grid;
		} catch (Exception e) {
			logger.error("--buyRecordList, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	@RequestMapping("buy/json/record/id")
	@ResponseBody Object jsonBuyRecordById(BuyerQueryVO vo) {
		try {
			Page<ChildSignView> page = new Page<>();
			vo.setOrderStatus(StatusEnum.SUCCESS.getCode());
			page.setPageNo(vo.getPage());
			page.setPageSize(Integer.MAX_VALUE - 1);
			page.setParams(vo);
			List<ChildSignView> list = courseService.selectCourseSignByPage(page);
			return new RspResult(StatusEnum.SUCCESS, list);
		} catch (Exception e) {
			logger.error("--buyRecordList, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
}
