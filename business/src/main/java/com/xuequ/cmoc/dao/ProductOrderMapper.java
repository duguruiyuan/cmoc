package com.xuequ.cmoc.dao;

import com.xuequ.cmoc.model.ProductOrder;

public interface ProductOrderMapper {
    int insert(ProductOrder record);

    int insertSelective(ProductOrder record);
}