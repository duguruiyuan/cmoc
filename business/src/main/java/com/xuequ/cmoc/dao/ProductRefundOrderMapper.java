package com.xuequ.cmoc.dao;

import java.util.List;

import com.xuequ.cmoc.model.ProductRefundOrder;

public interface ProductRefundOrderMapper {

    int insertSelective(ProductRefundOrder record);

    ProductRefundOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductRefundOrder record);
    
    List<ProductRefundOrder> queryRefundPendingOrder();

}