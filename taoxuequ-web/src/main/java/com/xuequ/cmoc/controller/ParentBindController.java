package com.xuequ.cmoc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.model.ParentInfo;

/**
 * 家长绑定管理
 * @author 胡启萌
 * @Date 2016年11月14日
 *
 */
@RequestMapping("parent")
public class ParentBindController extends BaseController {

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
	@ResponseBody Object jsonBindQuery(ParentInfo info) {
		return null;
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
	@ResponseBody Object jsonBuyRecord(ParentInfo info) {
		return null;
	}
}
