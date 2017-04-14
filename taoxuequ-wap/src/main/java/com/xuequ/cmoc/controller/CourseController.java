package com.xuequ.cmoc.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.OrderStatusEnum;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.core.wechat.common.Constants;
import com.xuequ.cmoc.core.wechat.utils.TemplateUtil;
import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.model.ChildSignInfo;
import com.xuequ.cmoc.model.CourseInfo;
import com.xuequ.cmoc.model.ImgGroup;
import com.xuequ.cmoc.model.ParentInfo;
import com.xuequ.cmoc.model.ProductOrder;
import com.xuequ.cmoc.model.WechatUserInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.pay.wepay.common.WechatBridge;
import com.xuequ.cmoc.pay.wepay.protocol.payProtocol.UnifiedOrderResData;
import com.xuequ.cmoc.reqVo.CourseSignVO;
import com.xuequ.cmoc.service.IActivityService;
import com.xuequ.cmoc.service.IChildSignInfoService;
import com.xuequ.cmoc.service.ICourseService;
import com.xuequ.cmoc.service.IParentInfoService;
import com.xuequ.cmoc.service.IProductOrderService;
import com.xuequ.cmoc.utils.AmountArithUtil;
import com.xuequ.cmoc.utils.ImageUtils;
import com.xuequ.cmoc.utils.OrderEncryptUtils;
import com.xuequ.cmoc.utils.PayUtils;
import com.xuequ.cmoc.view.CourseBuyerView;
import com.xuequ.cmoc.view.CourseGroupOrderView;
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
	@Autowired
	private IParentInfoService parentInfoService;
	
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
	
	/**
	 * 课程购买人信息
	 * @auther 胡启萌
	 * @Date 2017年4月14日
	 * @param vo
	 * @return
	 */
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

	/**
	 * 课程报名详情页
	 * @auther 胡启萌
	 * @Date 2017年4月14日
	 * @param model
	 * @param courseId
	 * @return
	 */
	@RequestMapping("sign/detail/{courseId}")
	public String sign(Model model, @PathVariable Integer courseId) {
		WechatUserInfo userInfo = getWechatUserInfo();
		model.addAttribute(Constants.WECHAT_USERINFO, userInfo);
		CourseInfo courseInfo = courseService.selectByPrimaryKey(courseId);
		ParentInfo buyerInfo = courseService.selectByOpenid(userInfo.getOpenid());
		model.addAttribute("course", courseInfo);
		model.addAttribute("buyer", buyerInfo);
		return "course/signDetail";
	}
	
	/**
	 * 组团报名页
	 * @auther 胡启萌
	 * @Date 2017年4月14日
	 * @param model
	 * @return
	 */
	@RequestMapping("sign/group")
	public String groupSign(Model model) {
		Integer courseId = Integer.valueOf(request.getParameter("pid"));
		Integer activityId = Integer.valueOf(request.getParameter("aid"));
		WechatUserInfo userInfo = getWechatUserInfo();
		model.addAttribute(Constants.WECHAT_USERINFO, userInfo);
		ActivityInfo activityInfo = activityService.selectById(activityId);
		CourseInfo courseInfo = courseService.selectByPrimaryKey(courseId);
		model.addAttribute("activityInfo", activityInfo);
		model.addAttribute("course", courseInfo);
		return "course/groupSign";
	}
	
	/**
	 * 组团报名订单创建
	 * @auther 胡启萌
	 * @Date 2017年4月14日
	 * @param vo
	 * @return
	 */
	@RequestMapping("group/order/create")
	@ResponseBody Object jsonGroupCreate(CourseSignVO vo){
		try {
			WechatUserInfo userInfo = getWechatUserInfo();
			vo.setOpenid(userInfo.getOpenid());
			vo.setHeadImg(userInfo.getHeadimgurl());
			vo.setCity(userInfo.getCountry() + " " + userInfo.getCity());
			vo.setNickName(userInfo.getNickname());
			ProductOrder view = courseService.addUPdateOrder(vo);
			WechatBridge bridge = PayUtils.wechatUnifiedOrder(view.getProductName(), String.valueOf(view.getProductId()), 
					view.getOrderNo(), AmountArithUtil.muiFloor(view.getTotalAmount(), 100), 
					view.getTradeType(), userInfo.getOpenid());
			bridge.setOrderNo(view.getOrderNo());
			String realPath = request.getRealPath("/");
			new Thread(new PosterImgExecutor(view.getOrderNo(), userInfo, realPath)).start();
			CourseGroupOrderView orderView = courseService.selectCourseGroupOrder(userInfo.getOpenid(), view.getOrderNo()).get(0);
			TemplateUtil.activitySignSucessMsg(view.getOrderNo(), orderView.getActivityAddr(), 
					userInfo.getOpenid(), orderView.getEmerName(), orderView.getActivityName(), 
					orderView.getStartDate());
			/*TemplateUtil.activitySignSucessMsgToCustomer(view.getOrderNo(), orderView.getEmerMobile(), 
					orderView.getEmerName(), orderView.getActivityName(), orderView.getActivityNum(), 
					orderView.getStartDate());*/
			return new RspResult(StatusEnum.SUCCESS, bridge);
		} catch (Exception e) {
			logger.error("--jsonGroupCreate, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	/**
	 * 单人报名订单创建
	 * @auther 胡启萌
	 * @Date 2017年4月14日
	 * @param vo
	 * @return
	 */
	@RequestMapping("sign/order/create")
	@ResponseBody Object signOrderCreate(CourseSignVO vo) {
		try {
			WechatUserInfo userInfo = getWechatUserInfo();
			vo.setOpenid(userInfo.getOpenid());
			vo.setHeadImg(userInfo.getHeadimgurl());
			vo.setCity(userInfo.getCountry() + " " + userInfo.getCity());
			vo.setNickName(userInfo.getNickname());
			ProductOrder view = courseService.addUPdateOrder(vo);
			Map<String, Object> map = new HashMap<>();
			map.put("orderNo", view.getOrderNo());
			map.put("productType", view.getProductType());
			return new RspResult(StatusEnum.SUCCESS, OrderEncryptUtils.getSignUrl(map));
		} catch (Exception e) {
			logger.error("--signOrderCreate, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	/**
	 * 组队创建页
	 * @auther 胡启萌
	 * @Date 2017年4月14日
	 * @param model
	 * @return
	 */
	@RequestMapping("group/create")
	public String groupCreate(Model model) {
		String orderNo = request.getParameter("orderNo");
		WechatUserInfo userInfo = getWechatUserInfo();
		ProductOrder productOrder = productOrderService.selectByOrderNo(orderNo);
		CourseInfo courseInfo = courseService.selectByPrimaryKey(Integer.valueOf(productOrder.getProductId()));
		int members = childSignInfoService.selectCountByOrderNo(orderNo);
		ChildSignInfo childInfo = new ChildSignInfo();
		if(productOrder.getOpenid().equals(userInfo.getOpenid())) {
			childInfo.setEmerMobile(productOrder.getSignPhone());
			childInfo.setEmerName(productOrder.getSignName());
		}
		
		model.addAttribute("course", courseInfo);
		model.addAttribute("orderNo", orderNo);
		model.addAttribute("members", members);
		model.addAttribute("childInfo", childInfo);
		model.addAttribute("isPay", 0);
		return "course/groupCreate";
	}
	
	/**
	 * 成员加入页
	 * @auther 胡启萌
	 * @Date 2017年4月14日
	 * @param model
	 * @return
	 */
	@RequestMapping("group/merber")
	public String groupMerber(Model model) {
		String orderNo = request.getParameter("oNo");
		String cid = request.getParameter("cId");
		Integer childId = StringUtils.isNotBlank(cid) ? Integer.valueOf(cid) : null;
		ChildSignInfo childInfo = new ChildSignInfo();
		if(childId != null) {
			childInfo = childSignInfoService.selectById(childId);
		}
		ProductOrder productOrder = productOrderService.selectByOrderNo(orderNo);
		CourseInfo courseInfo = courseService.selectByPrimaryKey(productOrder.getProductId());
		model.addAttribute("course", courseInfo);
		model.addAttribute("orderNo", orderNo);
		model.addAttribute("childInfo", childInfo);
		return "course/groupCreate";
	}
	
	/**
	 * 新增成员
	 * @auther 胡启萌
	 * @Date 2017年4月14日
	 * @param info
	 * @return
	 */
	@RequestMapping("json/group/addMember")
	@ResponseBody Object groupAddMember(ChildSignInfo info){
		WechatUserInfo userInfo = getWechatUserInfo();
		ProductOrder productOrder = productOrderService.selectByOrderNo(info.getOrderNo());
		if(!userInfo.getOpenid().equals(productOrder.getOpenid())) {
			int count = parentInfoService.selectCountByOpenidOrderNo(userInfo.getOpenid(), info.getOrderNo());
			if(count > 0) return new RspResult(StatusEnum.MERMBER_REPEAT_JOIN);
		}
		info.setActivityId(productOrder.getActivityId());
		info.setProductId(productOrder.getProductId());
		childSignInfoService.addAndUpdate(info, userInfo);
		if(info.getId() == null) {
			if(!productOrder.getOpenid().equals(userInfo.getOpenid())) {
				int members = childSignInfoService.selectCountByOrderNo(info.getOrderNo());
				TemplateUtil.memberAccessMsg(info.getOrderNo(), productOrder.getOrderStatus(),
						productOrder.getOpenid(), info.getEmerName(), members);
				TemplateUtil.accessSucessMsg(info.getId(), productOrder.getSignName(), 
						userInfo.getOpenid(), info.getEmerName(), info.getOrderNo());
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("orderNo", productOrder.getOrderNo());
		map.put("isSigner", productOrder.getOpenid().equals(userInfo.getOpenid()) ? 1 : 0);
		return new RspResult(StatusEnum.SUCCESS, map);
	}
	
	/**
	 * 新增成员页
	 * @auther 胡启萌
	 * @Date 2017年4月14日
	 * @param model
	 * @param orderNo
	 * @return
	 */
	@RequestMapping("group/add/{orderNo}")
	public String groupAdd(Model model, @PathVariable String orderNo) {
		ProductOrder productOrder = productOrderService.selectByOrderNo(orderNo);
		CourseInfo courseInfo = courseService.selectByPrimaryKey(Integer.valueOf(productOrder.getProductId()));
		List<ChildSignInfo> childList = childSignInfoService.selectListByOrderNo(orderNo);
		model.addAttribute("course", courseInfo);
		model.addAttribute("childList", childList);
		model.addAttribute("orderNo", orderNo);
		model.addAttribute("isPayed", productOrder.getOrderStatus().equals(OrderStatusEnum.SUCCESS.getCode()) ? 1 : 0);
		return "course/groupAdd";
	}
	
	@RequestMapping("group/poster/{orderNo}")
	public String groupPoster(Model model, @PathVariable String orderNo) {
		try {
			ProductOrder productOrder = productOrderService.selectByOrderNo(orderNo);
			ActivityInfo activityInfo = activityService.selectById(productOrder.getActivityId());
			model.addAttribute("productOrder", productOrder);
			model.addAttribute("activityInfo", activityInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "course/groupPoster";
	}
	
	@RequestMapping("group/poster/create")
	@ResponseBody Object groupPosterCreate(String orderNo, String extName, String imgdata, String mkdir) {
		RspResult rspResult = ImageUtils.saveImg(orderNo, extName, imgdata, mkdir);
		if(rspResult.getCode().equals(StatusEnum.SUCCESS.getCode())) {
			WechatUserInfo userInfo = getWechatUserInfo();
			ProductOrder productOrder = productOrderService.selectByParam(userInfo.getOpenid(), orderNo);
			productOrder.setPosterImg(String.valueOf(rspResult.getData()));
			productOrderService.updateById(productOrder);
			return new RspResult(StatusEnum.SUCCESS);
		}
		return rspResult;
	}
	
	@RequestMapping("group/orderList")
	public String groupOrderList(Model model) {
		WechatUserInfo userInfo = getWechatUserInfo();
		model.addAttribute("orderList", courseService.selectCourseGroupOrder(userInfo.getOpenid(), null));
		return "course/groupOrderList";
	}
	
	@RequestMapping("group/customerPay")
	public String groupCustomerPay(Model model) {
		String orderNo = request.getParameter("orderNo");
		ProductOrder productOrder = productOrderService.selectByOrderNo(orderNo);
		ActivityInfo activityInfo = activityService.selectById(productOrder.getActivityId());
		model.addAttribute("productOrder", productOrder);
		model.addAttribute("activityInfo", activityInfo);
		return "course/customerPay";
	}
}
