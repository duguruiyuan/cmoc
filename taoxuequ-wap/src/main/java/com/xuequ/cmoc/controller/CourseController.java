package com.xuequ.cmoc.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.xuequ.cmoc.reqVo.CourseSignVO;
import com.xuequ.cmoc.service.IActivityService;
import com.xuequ.cmoc.service.IChildSignInfoService;
import com.xuequ.cmoc.service.ICourseService;
import com.xuequ.cmoc.service.IParentInfoService;
import com.xuequ.cmoc.service.IProductOrderService;
import com.xuequ.cmoc.utils.ImageUtils;
import com.xuequ.cmoc.utils.OrderEncryptUtils;
import com.xuequ.cmoc.utils.QRCoderUtils;
import com.xuequ.cmoc.view.CourseBuyerView;
import com.xuequ.cmoc.view.CourseGroupOrderView;
import com.xuequ.cmoc.view.CourseListView;
import com.xuequ.cmoc.vo.CourseQueryVO;
import com.xuequ.cmoc.vo.ImageSynthesisVo;

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
		CourseGroupOrderView orderView = courseService.selectCourseGroupOrder(userInfo.getOpenid(), view.getOrderNo()).get(0);
		TemplateUtil.activitySignSucessMsg(view.getOrderNo(), orderView.getActivityAddr(), 
				userInfo.getOpenid(), orderView.getEmerName(), orderView.getActivityName(), 
				orderView.getStartDate());
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
	public String groupMerber(Model model) {
		String orderNo = request.getParameter("oNo");
		String cid = request.getParameter("cId");
		return toGroupMember(model, orderNo, cid);
	}
	
	private String toGroupMember(Model model, String orderNo, String cid) {
		Integer childId = StringUtils.isNotBlank(cid) ? Integer.valueOf(cid) : null;
		ChildSignInfo childInfo = new ChildSignInfo();
		if(childId != null) {
			childInfo = childSignInfoService.selectById(childId);
		}
		ProductOrder productOrder = productOrderService.selectByOrderNo(orderNo);
		CourseInfo courseInfo = courseService.selectByPrimaryKey(Integer.valueOf(productOrder.getProductId()));
		model.addAttribute("course", courseInfo);
		model.addAttribute("orderNo", orderNo);
		model.addAttribute("childInfo", childInfo);
		return "course/groupCreate";
	}
	
	@RequestMapping("json/group/addMember")
	@ResponseBody Object groupAddMember(ChildSignInfo info){
		WechatUserInfo userInfo = getWechatUserInfo();
		ProductOrder productOrder = productOrderService.selectByOrderNo(info.getOrderNo());
		info.setActivityId(productOrder.getActivityId());
		info.setProductId(productOrder.getProductId());
		childSignInfoService.addAndUpdate(info, userInfo);
		ParentInfo parentInfo = parentInfoService.selectById(productOrder.getCustId());
		if(info.getId() == null) {
			if(!parentInfo.getOpenid().equals(userInfo.getOpenid())) {
				int members = childSignInfoService.selectCountByOrderNo(info.getOrderNo());
				TemplateUtil.memberAccessMsg(info.getOrderNo(), parentInfo.getOpenid(), 
						info.getEmerName(), members);
				TemplateUtil.accessSucessMsg(info.getId(), parentInfo.getParentName(), 
						userInfo.getOpenid(), info.getEmerName(), info.getOrderNo());
			}
		}
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
		model.addAttribute("orderNo", orderNo);
		model.addAttribute("isPayed", productOrder.getOrderStatus().equals("000") ? 1 : 0);
		return "course/groupAdd";
	}
	
	@RequestMapping("group/poster")
	public String groupPoster(Model model) {
		try {
			String orderNo = request.getParameter("oNo");
			WechatUserInfo userInfo = getWechatUserInfo();
			ProductOrder productOrder = productOrderService.selectByParam(userInfo.getOpenid(), orderNo);
			if(StringUtils.isBlank(productOrder.getPosterImg())) {
				List<ImageSynthesisVo> list = new ArrayList<>();
				ImageSynthesisVo vo1 = new ImageSynthesisVo(userInfo.getHeadimgurl(), 86, 134, 100, 100);
				String url = Constants.BASEPATH + "/course/group/merber?oNo=" + productOrder.getOrderNo();
				url = QRCoderUtils.createEWM(url, 100, 100, productOrder.getOrderNo());
				ImageSynthesisVo vo2 = new ImageSynthesisVo(url, 189, 305, 130, 130);
				list.add(vo1);
				list.add(vo2);
				String fileSrc = request.getRealPath("/images") + "/poster-share.png";
				String outSrcName = orderNo + ".jpg";
				String outSrc = QRCoderUtils.getRealImgUrl() + File.separator + outSrcName;
				ImageUtils.composePic(fileSrc, outSrc, list, 642, 900);
				productOrder.setPosterImg(QRCoderUtils.getRspImgUrl(outSrcName));
				productOrderService.updateById(productOrder);
			}
			model.addAttribute("productOrder", productOrder);
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
	
}
