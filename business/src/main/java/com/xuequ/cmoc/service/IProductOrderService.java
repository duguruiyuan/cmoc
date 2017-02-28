package com.xuequ.cmoc.service;

import com.xuequ.cmoc.model.ProductOrder;

public interface IProductOrderService {
 
	ProductOrder selectByOrderNo(String orderNO);
	
	ProductOrder selectByParam(String openid, String orderNo);
	
	int updateById(ProductOrder vo);
}
