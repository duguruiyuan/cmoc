package com.xuequ.cmoc.dao;

import com.xuequ.cmoc.model.GoodsDetail;

public interface GoodsDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsDetail record);

    int insertSelective(GoodsDetail record);

    GoodsDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsDetail record);

    int updateByPrimaryKey(GoodsDetail record);
}