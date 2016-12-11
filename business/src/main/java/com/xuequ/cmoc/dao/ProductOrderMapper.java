package com.xuequ.cmoc.dao;

import com.xuequ.cmoc.model.ProductOrder;

public interface ProductOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductOrder record);

    int insertSelective(ProductOrder record);

    ProductOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductOrder record);

    int updateByPrimaryKey(ProductOrder record);
    
    ProductOrder selectByOrderNo(String orderNo);
}