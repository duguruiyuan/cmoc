package com.xuequ.cmoc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.model.ImgGroup;
import com.xuequ.cmoc.reqVo.ParentInfoVO;
import com.xuequ.cmoc.service.IParentInfoService;
import com.xuequ.cmoc.view.ChildActRecordView;

@RequestMapping("user")
@Controller
public class UserController extends BaseController{
	
	@Autowired
	private IParentInfoService parentInfoService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * 用户绑定
	 * @auther 胡启萌
	 * @Date 2016年11月21日
	 * @return
	 */
	@RequestMapping("bind")
	public String bind(Model model) {
		String openid = request.getParameter("id");
		int count = parentInfoService.selectCountByOpenid(openid);
		if(count > 0) {
			List<ChildActRecordView> list = parentInfoService.selectChildActRecord(openid);
			model.addAttribute("list", list);
		}
		model.addAttribute("isBind", count > 0 ? true : false);
		model.addAttribute("openid", openid);
		ImgGroup group = new ImgGroup();
		group.setPosition("1");
		group.setShelves(1);
		model.addAttribute("topBannerList",contentManageService.selectListByParam(group));
		return "user/bind";
	}
	
	@RequestMapping("parent/bind")
	public @ResponseBody Object parentBind(ParentInfoVO vo) {
		try {
			RspResult result = parentInfoService.addParentBind(vo);
			return result;
		} catch (Exception e) {
			logger.error("--parentBind, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
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
		String openid = request.getParameter("id");
		model.addAttribute("list", null);
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
		ImgGroup group = new ImgGroup();
		group.setPosition("1");
		group.setShelves(1);
		model.addAttribute("topBannerList",contentManageService.selectListByParam(group));
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
		ImgGroup group = new ImgGroup();
		group.setPosition("1");
		group.setShelves(1);
		model.addAttribute("topBannerList",contentManageService.selectListByParam(group));
		return "user/collection";
	}
	
}
