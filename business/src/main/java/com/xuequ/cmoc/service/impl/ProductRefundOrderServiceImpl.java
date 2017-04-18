package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.ProductRefundOrderMapper;
import com.xuequ.cmoc.model.ProductRefundOrder;
import com.xuequ.cmoc.service.IProductRefundOrderService;

@Service("productRefundOrderService")
public class ProductRefundOrderServiceImpl implements IProductRefundOrderService {

	@Autowired
	private ProductRefundOrderMapper productRefundOrderMapper;
	
	@Override
	public int insertSelective(ProductRefundOrder record) {
		return productRefundOrderMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ProductRefundOrder record) {
		return productRefundOrderMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<ProductRefundOrder> queryRefundPendingOrder() {
		return productRefundOrderMapper.queryRefundPendingOrder();
	}

}
