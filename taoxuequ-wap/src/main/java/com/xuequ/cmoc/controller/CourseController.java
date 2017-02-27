package com.xuequ.cmoc.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.stat.TableStat.Mode;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.core.wechat.common.Constants;
import com.xuequ.cmoc.dao.ChildSignInfoMapper;
import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.model.ChildSignInfo;
import com.xuequ.cmoc.model.CourseInfo;
import com.xuequ.cmoc.model.ImgGroup;
import com.xuequ.cmoc.model.ParentInfo;
import com.xuequ.cmoc.model.ProductOrder;
import com.xuequ.cmoc.model.WechatUserInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.reqVo.CourseSignVO;
import com.xuequ.cmoc.service.IActivityService;
import com.xuequ.cmoc.service.IChildSignInfoService;
import com.xuequ.cmoc.service.ICourseService;
import com.xuequ.cmoc.service.IProductOrderService;
import com.xuequ.cmoc.utils.OrderEncryptUtils;
import com.xuequ.cmoc.view.CourseBuyerView;
import com.xuequ.cmoc.view.CourseListView;
import com.xuequ.cmoc.vo.CourseQueryVO;

@RequestMapping("course")
@Controller
public class CourseController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(CourseController.class);
	@Autowired
	private ICourseService courseService;
	@Autowired
	private IActivityService activityService;
	@Autowired
	private IProductOrderService productOrderService;
	@Autowired
	private IChildSignInfoService childSignInfoService;
	
	@RequestMapping(value={"","/","list"})
	public String courseList(Model model) {
		ImgGroup group = new ImgGroup();
		group.setPosition("1");
		group.setShelves(1);
		model.addAttribute("topBannerList",contentManageService.selectListByParam(group));
		return "course/list";
	}
	
	@RequestMapping("list/query")
	public @ResponseBody Object courseListQuery(CourseQueryVO vo) {
		Page<CourseListView> page = new Page<CourseListView>();
		page.setPageNo(vo.getPage());
		page.setPageSize(vo.getRows());
		page.setParams(vo);
		List<CourseListView> list = courseService.selectShelvesSignByPage(page);
		page.setResults(list);
		return page;
	}
	
	@RequestMapping("detail/{courseId}")
	public String detail(Model model, @PathVariable Integer courseId) throws UnsupportedEncodingException {
		CourseListView courseInfo = courseService.selectCourseDetail(courseId);
		model.addAttribute("course", courseInfo);
		model.addAttribute("desc", new String(courseInfo.getCourseDetails(),"UTF-8"));
		if(courseInfo.getSignWay() == 1) {
			model.addAttribute("schuduList", courseService.selectScheduActivityInfoByCourseId(courseId));
		}
		return "course/detail";
	}
	
	@RequestMapping("json/buyer")
	public @ResponseBody Object courseBuyer(CourseQueryVO vo) {
		try {
			Page<CourseBuyerView> page = new Page<CourseBuyerView>();
			page.setPageNo(vo.getPage());
			page.setPageSize(3);
			page.setParams(vo);
			List<CourseBuyerView> list = courseService.selectCourseBuyerByPage(page);
			page.setResults(list);
			return page;
		} catch (Exception e) {
			logger.error("--courseBuyer, error={}", e);
		}
		return null;
	}

	@RequestMapping("sign/{courseId}")
	public String sign(Model model, @PathVariable Integer courseId) {
		WechatUserInfo userInfo = getWechatUserInfo();
		model.addAttribute(Constants.WECHAT_USERINFO, userInfo);
		CourseInfo courseInfo = courseService.selectByPrimaryKey(courseId);
		ParentInfo buyerInfo = courseService.selectByOpenid("aaaa");
		model.addAttribute("course", courseInfo);
		model.addAttribute("buyer", buyerInfo);
		return "course/sign";
	}
	
	@RequestMapping("sign/{courseId}/{activityId}")
	public String groupSign(Model model, @PathVariable Integer courseId, 
			@PathVariable Integer activityId) {
		WechatUserInfo userInfo = getWechatUserInfo();
		model.addAttribute(Constants.WECHAT_USERINFO, userInfo);
		ActivityInfo activityInfo = activityService.selectById(activityId);
		CourseInfo courseInfo = courseService.selectByPrimaryKey(courseId);
		model.addAttribute("activityInfo", activityInfo);
		model.addAttribute("course", courseInfo);
		return "course/groupSign";
	}
	
	@RequestMapping("json/groupOrder/create")
	@ResponseBody Object jsonGroupCreate(CourseSignVO vo){
		WechatUserInfo userInfo = getWechatUserInfo();
		vo.setOpenid(userInfo.getOpenid());
		vo.setHeadImg(userInfo.getHeadimgurl());
		vo.setCity(userInfo.getCountry() + " " + userInfo.getCity());
		CourseSignVO view = courseService.addUPdateOrder(vo);
		Map<String, Object> map = new HashMap<>();
		map.put("orderNo", view.getOrderNo());
		map.put("pId", vo.getProductId());
		map.put("aId", vo.getActivityId());
		map.put("productType", view.getProductType());
		return new RspResult(StatusEnum.SUCCESS, OrderEncryptUtils.getSignUrl(map));
	}
	
	@RequestMapping("signorder/create")
	@ResponseBody Object signOrderCreate(CourseSignVO vo) {
		try {
			WechatUserInfo userInfo = getWechatUserInfo();
			vo.setOpenid(userInfo.getOpenid());
			vo.setHeadImg(userInfo.getHeadimgurl());
			vo.setCity(userInfo.getCountry() + " " + userInfo.getCity());
			CourseSignVO view = courseService.addUPdateOrder(vo);
			Map<String, Object> map = new HashMap<>();
			map.put("orderNo", view.getOrderNo());
			map.put("productType", view.getProductType());
			return new RspResult(StatusEnum.SUCCESS, OrderEncryptUtils.getSignUrl(map));
		} catch (Exception e) {
			logger.error("--signOrderCreate, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	@RequestMapping("group/create")
	public String groupCreate(Model model) {
		String orderNo = request.getParameter("orderNo");
		ProductOrder productOrder = productOrderService.selectByOrderNo(orderNo);
		CourseInfo courseInfo = courseService.selectByPrimaryKey(Integer.valueOf(productOrder.getProductId()));
		int members = childSignInfoService.selectCountByOrderNo(orderNo);
		model.addAttribute("course", courseInfo);
		model.addAttribute("orderNo", orderNo);
		model.addAttribute("members", members);
		return "course/groupCreate";
	}
	
	@RequestMapping("group/merber")
	public String groupMerber(Model model, ChildSignInfo info) {
		ProductOrder productOrder = productOrderService.selectByOrderNo(info.getOrderNo());
		CourseInfo courseInfo = courseService.selectByPrimaryKey(Integer.valueOf(productOrder.getProductId()));
		ChildSignInfo childSignInfo = childSignInfoService.selectById(id);
		model.addAttribute("course", courseInfo);
		model.addAttribute("orderNo", info.getOrderNo());
		return "course/groupCreate";
	}
	
	@RequestMapping("json/group/addMember")
	@ResponseBody Object groupAddMember(ChildSignInfo info){
		WechatUserInfo userInfo = getWechatUserInfo();
		ProductOrder productOrder = productOrderService.selectByOrderNo(info.getOrderNo());
		info.setActivityId(productOrder.getActivityId());
		info.setProductId(productOrder.getProductId());
		childSignInfoService.addAndUpdate(info, userInfo);
		return new RspResult(StatusEnum.SUCCESS, productOrder.getOrderNo());
	}
	
	@RequestMapping("group/add")
	public String groupAdd(Model model) {
		String orderNo = request.getParameter("oNo");
		ProductOrder productOrder = productOrderService.selectByOrderNo(orderNo);
		CourseInfo courseInfo = courseService.selectByPrimaryKey(Integer.valueOf(productOrder.getProductId()));
		List<ChildSignInfo> childList = childSignInfoService.selectListByOrderNo(orderNo);
		model.addAttribute("course", courseInfo);
		model.addAttribute("childList", childList);
		model.addAttribute("isPayed", productOrder.getOrderStatus().equals("000") ? 1 : 0);
		return "course/groupAdd";
	}
	
	@RequestMapping("group/poster")
	public String groupPoster(Model model) {
		String orderNum = request.getParameter("oid");
		model.addAttribute("orderNum", orderNum);
		WechatUserInfo userInfo = getWechatUserInfo();
		model.addAttribute(Constants.WECHAT_USERINFO, userInfo);
		return "course/groupPoster";
	}
	
	@RequestMapping("group/orderList")
	public String groupOrderList(Model model) {
		return "course/groupOrderList";
	}
}
