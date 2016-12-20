package com.xuequ.cmoc.dao;

import org.apache.ibatis.annotations.Param;

import com.xuequ.cmoc.model.MarineSupport;

public interface MarineSupportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MarineSupport record);

    int insertSelective(MarineSupport record);

    MarineSupport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MarineSupport record);

    int updateByPrimaryKey(MarineSupport record);
    
    int selectCountForSupport(@Param("openid")String openid, @Param("marineId")Integer marineId);
}