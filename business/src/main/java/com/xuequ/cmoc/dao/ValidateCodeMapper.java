package com.xuequ.cmoc.dao;

import com.xuequ.cmoc.model.ValidateCode;

public interface ValidateCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ValidateCode record);

    int insertSelective(ValidateCode record);

    ValidateCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ValidateCode record);

    int updateByPrimaryKey(ValidateCode record);
}