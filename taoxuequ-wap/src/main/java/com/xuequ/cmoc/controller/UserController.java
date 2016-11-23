package com.xuequ.cmoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("user")
@Controller
public class UserController extends BaseController{
	
	/**
	 * 用户绑定
	 * @auther 胡启萌
	 * @Date 2016年11月21日
	 * @return
	 */
	@RequestMapping("bind")
	public String bind() {
		return "user/bind";
	}
	
	/**
	 * 用户积分查询兑换
	 * @auther 胡启萌
	 * @Date 2016年11月21日
	 * @param model
	 * @return
	 */
	@RequestMapping("points")
	public String points(Model model) {
		return "user/points";
	}
	
	/**
	 * 用户购买记录查询
	 * @auther 胡启萌
	 * @Date 2016年11月21日
	 * @param model
	 * @return
	 */
	@RequestMapping("buyRecord")
	public String buyRecord(Model model) {
		return "user/buyRecord";
	}
	
	/**
	 * 个人收藏查询
	 * @auther 胡启萌
	 * @Date 2016年11月21日
	 * @param model
	 * @return
	 */
	@RequestMapping("collection")
	public String collection(Model model) {
		return "user/collection";
	}
	
}
