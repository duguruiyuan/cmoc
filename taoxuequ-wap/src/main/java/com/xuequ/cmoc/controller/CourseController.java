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

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.core.wechat.utils.WechatUtils;
import com.xuequ.cmoc.model.CourseBuyerInfo;
import com.xuequ.cmoc.model.CourseInfo;
import com.xuequ.cmoc.model.ParentInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.ICourseService;
import com.xuequ.cmoc.utils.OrderEncryptUtils;
import com.xuequ.cmoc.view.CourseBuyerView;
import com.xuequ.cmoc.view.CourseListView;
import com.xuequ.cmoc.vo.CourseQueryVO;
import com.xuequ.cmoc.model.ProductOrder;
import com.xuequ.cmoc.model.WechatUserInfo;

@RequestMapping("course")
@Controller
public class CourseController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(CourseController.class);
	@Autowired
	private ICourseService courseService;
	
	@RequestMapping(value={"","/"})
	public String courseList(Model model) {
		List<CourseListView> list = courseService.selectShelvesList();
		model.addAttribute("list", list);
		return "course/list";
	}
	
	@RequestMapping("detail/{courseId}")
	public String detail(Model model, @PathVariable Integer courseId) throws UnsupportedEncodingException {
		CourseListView courseInfo = courseService.selectCourseDetail(courseId);
		model.addAttribute("course", courseInfo);
		model.addAttribute("desc", new String(courseInfo.getCourseDetails(),"UTF-8"));
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
//		String page = "course/sign";
//		String redir = wechatRedirect(model, page);
//		if(redir.equals(page)) {
//			WechatSnsToken token = (WechatSnsToken) model.asMap().get("snsToken");
//			String openid = request.getParameter("openid");
//			if(token != null || StringUtils.isNotBlank(openid)) {
//				if(token != null) openid = token.getOpenid();
//				CourseInfo courseInfo = courseService.selectByPrimaryKey(courseId);
//				CourseBuyerInfo buyerInfo = courseService.selectByOpenid(openid);
//				model.addAttribute("course", courseInfo);
//				model.addAttribute("buyer", buyerInfo);
//			}
//		}
//		return redir;
		CourseInfo courseInfo = courseService.selectByPrimaryKey(courseId);
		ParentInfo buyerInfo = courseService.selectByOpenid("oqyqUwq_YY84qjFWUtn6Ti4XIROE");
		model.addAttribute("course", courseInfo);
		model.addAttribute("buyer", buyerInfo);
		return "course/sign";
	}
	
	@RequestMapping("signorder/create")
	@ResponseBody Object signOrderCreate(CourseBuyerView vo) {
		try {
			CourseBuyerView view = courseService.addUPdateOrder(vo);
			Map<String, Object> map = new HashMap<>();
			map.put("orderNo", view.getOrderNo());
			map.put("productType", view.getProductType());
			return new RspResult(StatusEnum.SUCCESS, OrderEncryptUtils.getSignUrl(map));
		} catch (Exception e) {
			logger.error("--signOrderCreate, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
}
