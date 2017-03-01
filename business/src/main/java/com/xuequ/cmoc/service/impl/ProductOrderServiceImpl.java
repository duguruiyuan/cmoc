package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.ProductOrderMapper;
import com.xuequ.cmoc.model.ProductOrder;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.IProductOrderService;
import com.xuequ.cmoc.view.CourseSignOrderView;

@Service("productOrderService")
public class ProductOrderServiceImpl implements IProductOrderService {

	@Autowired
	private ProductOrderMapper productOrderMapper;
	
	@Override
	public ProductOrder selectByOrderNo(String orderNO) {
		return productOrderMapper.selectByOrderNo(orderNO);
	}

	@Override
	public ProductOrder selectByParam(String openid, String orderNo) {
		return productOrderMapper.selectByParam(openid, orderNo);
	}

	@Override
	public int updateById(ProductOrder vo) {
		return productOrderMapper.updateByPrimaryKeySelective(vo);
	}

	@Override
	public List<CourseSignOrderView> selectCourseSignOrderByPage(Page<CourseSignOrderView> page) {
		return productOrderMapper.selectCourseSignOrderByPage(page);
	}

	@Override
	public CourseSignOrderView selectCourseSignOrderByOrderId(Integer orderId) {
		return productOrderMapper.selectCourseSignOrderByOrderId(orderId);
	}

	@Override
	public ProductOrder selectById(Integer id) {
		return productOrderMapper.selectByPrimaryKey(id);
	}

}
