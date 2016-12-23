package com.xuequ.cmoc.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.service.IProductOrderService;
import com.xuequ.cmoc.utils.OrderEncryptUtils;

@RequestMapping("pay")
@Controller
public class PayController extends BaseController{
	
	@Autowired
	private IProductOrderService productOrderService;

	@RequestMapping("wechatpay")
	public String wechatPay(Model model) {
		String orderNo = request.getParameter("orderNo");
		String productType = request.getParameter("productType");
		String key = request.getParameter("key");
		if(StringUtils.isBlank(orderNo) || StringUtils.isBlank(productType) || 
				StringUtils.isBlank(key)) {
			model.addAttribute("error", StatusEnum.PARAM_FAIL.getMsg());
		}else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderNo", orderNo);
			map.put("productType", productType);
			if(!OrderEncryptUtils.isCompare(map, key)) {
				model.addAttribute("error", StatusEnum.ORDER_WARN.getMsg());
			}else {
				model.addAttribute("order", productOrderService.selectByOrderNo(orderNo));
			}
		}
		return "pay/wechatpay";
	}
}
