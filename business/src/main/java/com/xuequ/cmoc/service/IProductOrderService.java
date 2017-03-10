package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.model.ProductOrder;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.CourseSignOrderView;

public interface IProductOrderService {
 
	ProductOrder selectByOrderNo(String orderNO);
	
	ProductOrder selectByParam(String openid, String orderNo);
	
	int updateById(ProductOrder vo);
	
	List<CourseSignOrderView> selectCourseSignOrderByPage(Page<CourseSignOrderView> page);
	
	CourseSignOrderView selectCourseSignOrderByOrderId(Integer orderId);
	
	ProductOrder selectById(Integer id);
	
	int updateOrderConfirmPay(ProductOrder vo);
}
