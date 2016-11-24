package com.xuequ.cmoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.model.HollowManInfo;

/**
 * 透明人管理
 * @author 胡启萌
 * @Date 2016年11月14日
 *
 */
@RequestMapping("hm")
@Controller
public class HollowManManageController extends BaseController{

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
	@ResponseBody Object jsonQuery(HollowManInfo info) {
		return null;
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
