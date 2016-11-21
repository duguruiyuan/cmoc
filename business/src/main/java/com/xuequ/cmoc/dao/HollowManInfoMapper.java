package com.xuequ.cmoc.dao;

import org.apache.ibatis.annotations.Param;

import com.xuequ.cmoc.model.HollowManInfo;

public interface HollowManInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HollowManInfo record);

    int insertSelective(HollowManInfo record);

    HollowManInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HollowManInfo record);

    int updateByPrimaryKey(HollowManInfo record);
    
    HollowManInfo selectByNameMobile(@Param("hmName")String hmName, 
    		@Param("hmMobile")String hmMobile);
}