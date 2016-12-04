package com.xuequ.cmoc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.model.Grid;
import com.xuequ.cmoc.model.HollowManInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.IHollowManService;
import com.xuequ.cmoc.view.HollowManInfoView;
import com.xuequ.cmoc.vo.HollowManQueryVO;

/**
 * 透明人管理
 * @author 胡启萌
 * @Date 2016年11月14日
 *
 */
@RequestMapping("hm")
@Controller
public class HollowManManageController extends BaseController{
	
	@Autowired
	private IHollowManService hollowManService;

	/**
	 * 透明人查询页
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @return
	 */
	@RequestMapping("query")
	public String query() {
		return "hm/hmQuery";
	}
	

	/**
	 * 查询透明人数据
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @param info
	 * @return
	 */
	@RequestMapping("json/query")
	@ResponseBody Object jsonQuery(HollowManQueryVO vo) {
		Page<HollowManInfoView> page = new Page<HollowManInfoView>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("hmName", vo.getHmName());
		paramMap.put("hmMobile", vo.getHmMobile());
		paramMap.put("idCard", vo.getIdCard());
		paramMap.put("isActive", vo.getIsActive());
		paramMap.put("sex", vo.getSex());
		Grid grid = new Grid();
		page.setParams(paramMap);
		page.setPageNo(vo.getPage());
		page.setPageSize(vo.getRows());
		List<HollowManInfoView> list = hollowManService.selectByPage(page);
		grid.setRows(list);
		grid.setTotal(page.getTotalRecord());
		return grid;
	}
	
	/**
	 * 透明人上传资源查询页
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @return
	 */
	@RequestMapping("resource/query")
	public String resourceQuery() {
		return "hm/resourceQuery";
	}
	
	/**
	 * 查询透明人上传资源数据
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @param info
	 * @return
	 */
	@RequestMapping("resource/json/query")
	@ResponseBody Object resourceJsonQuery(HollowManInfo info) {
		return null;
	}
	
}
