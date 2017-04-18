package com.xuequ.cmoc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thoughtworks.xstream.XStream;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.OrderStatusEnum;
import com.xuequ.cmoc.common.enums.PayType;
import com.xuequ.cmoc.common.enums.ResultCode;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.common.enums.TradeState;
import com.xuequ.cmoc.common.enums.TradeType;
import com.xuequ.cmoc.core.wechat.utils.SerializeXmlUtil;
import com.xuequ.cmoc.core.wechat.utils.TemplateUtil;
import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.model.CourseInfo;
import com.xuequ.cmoc.model.ProductOrder;
import com.xuequ.cmoc.model.WechatUserInfo;
import com.xuequ.cmoc.pay.wepay.common.WechatBridge;
import com.xuequ.cmoc.pay.wepay.protocol.payQueryProtocol.OrderQueryResData;
import com.xuequ.cmoc.reqVo.CourseSignVO;
import com.xuequ.cmoc.service.IActivityService;
import com.xuequ.cmoc.service.ICourseService;
import com.xuequ.cmoc.service.IProductOrderService;
import com.xuequ.cmoc.utils.AmountArithUtil;
import com.xuequ.cmoc.utils.DateUtil;
import com.xuequ.cmoc.utils.OrderEncryptUtils;
import com.xuequ.cmoc.utils.PayUtils;
import com.xuequ.cmoc.view.CourseSignOrderView;

@Controller
public class PayController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(PayController.class);
	
	@Autowired
	private IProductOrderService productOrderService;
	@Autowired
	private IActivityService activityService;
	@Autowired
	private ICourseService courseService;

	@RequestMapping("pay/detail")
	public String wechatPay(Model model) {
		WechatUserInfo userInfo = getWechatUserInfo();
		String orderNo = request.getParameter("ono");
		ProductOrder order = productOrderService.selectByOrderNo(orderNo);
		if(!(order != null && StringUtils.isNotBlank(orderNo) 
				&& order.getOpenid().equals(userInfo.getOpenid()))) {
			model.addAttribute("error", StatusEnum.PARAM_FAIL.getMsg());
		}else {
			model.addAttribute("order", order);
		}
		return "pay/detail";
	}
	
	@RequestMapping("pay/result/{orderNo}")
	public String payResult(Model model, @PathVariable String orderNo) {
		ProductOrder productOrder = productOrderService.selectByOrderNo(orderNo);
		WechatUserInfo userInfo = getWechatUserInfo();
		if(productOrder != null && !productOrder.getOpenid().equals(userInfo.getOpenid())) {
			return null;
		}
		CourseInfo courseInfo = courseService.selectByPrimaryKey(productOrder.getProductId());
		if(courseInfo.getSignWay() == 1) {
			ActivityInfo activityInfo = activityService.selectById(productOrder.getActivityId());
			model.addAttribute("activityInfo", activityInfo);
		}
		model.addAttribute("productOrder", productOrder);
		model.addAttribute("courseInfo", courseInfo);
		return "pay/payResult";
	}
	
	@RequestMapping("pay/sign")
	@ResponseBody Object paySign(ProductOrder vo) {
		try {
			WechatUserInfo userInfo = getWechatUserInfo();
			ProductOrder view = productOrderService.selectByOrderNo(vo.getOrderNo());
			CourseInfo courseInfo = courseService.selectByPrimaryKey(view.getProductId());
			WechatBridge bridge = PayUtils.wechatUnifiedOrder(courseInfo.getCourseName(), String.valueOf(view.getProductId()), 
					view.getOrderNo(), AmountArithUtil.muiFloor(view.getResAmount(), 100), 
					TradeType.JSAPI.getCode(), userInfo.getOpenid());
			bridge.setOrderNo(view.getOrderNo());
			return new RspResult(StatusEnum.SUCCESS, bridge);
		} catch (Exception e) {
			logger.error("--paySign, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	@RequestMapping("wechatpay/callback")
	@ResponseBody void wechatCallback(HttpServletResponse response) {
		try {
            ServletInputStream in = request.getInputStream();  
            StringBuilder xmlMsg = new StringBuilder();  
            byte[] b = new byte[4096];  
            for (int n; (n = in.read(b)) != -1;) {  
                xmlMsg.append(new String(b, 0, n, "UTF-8"));  
            } 
            String rsqMsg = xmlMsg.toString();
            logger.info("---wechatCallback repMsg={}", rsqMsg);
	        XStream xs = SerializeXmlUtil.createXstream();  
	        xs.processAnnotations(OrderQueryResData.class);  
	        xs.alias("xml", OrderQueryResData.class);  
	        OrderQueryResData resData = (OrderQueryResData) xs.fromXML(rsqMsg);  
	        ProductOrder order = productOrderService.selectByOrderNo(resData.getOut_trade_no());
	        if(resData.getReturn_code().equals(ResultCode.SUCCESS.getCode())) {
	        	if(resData.getResult_code().equals(ResultCode.SUCCESS.getCode())) {
	        		order.setTransNo(resData.getTransaction_id());
	        		order.setBankCode(resData.getBank_type());
	        		order.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
	        		order.setPayCallbackTime(DateUtil.strToDate(resData.getTime_end(), DateUtil.DEFAULT_DATE_FORMAT3));
	        		order.setTradeType(resData.getTrade_type());
	        		order.setChannel(PayType.WEIXIN.getCode());
	        		order.setTradeState(TradeState.SUCCESS.getCode());
	        	}else {
					order.setOrderStatus(OrderStatusEnum.FAIL.getCode());
					order.setErrorCode(resData.getErr_code());
					order.setErrorReason(resData.getErr_code_des());
				}
	        }else {
	        	order.setOrderStatus(OrderStatusEnum.FAIL.getCode());
	        	order.setErrorCode(resData.getReturn_code());
	        	order.setErrorReason(resData.getReturn_msg());
	        }
	        productOrderService.updateById(order);
	        CourseSignOrderView view = productOrderService.selectCourseSignOrderByOrderId(order.getId());
			if(view != null) {
				if(view.getSignWay() == 1) {
					TemplateUtil.courseOrderPaySucessMsg(view.getPaySubmitTime(), view.getOrderNo(), 
							view.getOpenid(), view.getSignName(), view.getActivityName(), 
							view.getActivityNum(), view.getActivityStartDate(), view.getTotalPrice());
					TemplateUtil.activitySignSucessMsgToCustomer(view.getOrderNo(), view.getSignPhone(), 
							view.getSignName(), view.getActivityName(), view.getActivityNum(), 
							view.getActivityStartDate());
				}else {
					TemplateUtil.courseOrderPaySucessMsg(view.getPaySubmitTime(), view.getOrderNo(), 
							view.getOpenid(), view.getSignName(), view.getCourseName(), 
							null, null, view.getResAmount());
					TemplateUtil.activitySignSucessMsgToCustomer(view.getOrderNo(), view.getSignPhone(), 
							view.getSignName(), view.getCourseName(), null, null);
				}
			}
	        response.getWriter().write("SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--wechatCallback error={}", e);
		}
	}
}
