package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.model.ProductRefundOrder;

public interface IProductRefundOrderService {

	public int insertSelective(ProductRefundOrder record);
	
	public int updateByPrimaryKeySelective(ProductRefundOrder record);
	
	public List<ProductRefundOrder> queryRefundPendingOrder();
}
