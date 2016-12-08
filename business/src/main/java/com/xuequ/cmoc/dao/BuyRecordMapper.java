package com.xuequ.cmoc.dao;

import com.xuequ.cmoc.model.BuyRecord;

public interface BuyRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BuyRecord record);

    int insertSelective(BuyRecord record);

    BuyRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BuyRecord record);

    int updateByPrimaryKey(BuyRecord record);
}