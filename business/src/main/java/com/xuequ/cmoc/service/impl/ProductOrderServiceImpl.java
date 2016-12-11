package com.xuequ.cmoc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.ProductOrderMapper;
import com.xuequ.cmoc.model.ProductOrder;
import com.xuequ.cmoc.service.IProductOrderService;

@Service("productOrderService")
public class ProductOrderServiceImpl implements IProductOrderService {

	@Autowired
	private ProductOrderMapper productOrderMapper;
	
	@Override
	public ProductOrder selectByOrderNo(String orderNO) {
		return productOrderMapper.selectByOrderNo(orderNO);
	}

}
