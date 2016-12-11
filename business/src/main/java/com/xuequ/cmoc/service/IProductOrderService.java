package com.xuequ.cmoc.service;

import com.xuequ.cmoc.model.ProductOrder;

public interface IProductOrderService {
 
	ProductOrder selectByOrderNo(String orderNO);
}
