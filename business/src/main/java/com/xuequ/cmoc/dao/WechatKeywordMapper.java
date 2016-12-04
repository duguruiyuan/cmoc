package com.xuequ.cmoc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuequ.cmoc.model.WechatKeyword;

public interface WechatKeywordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WechatKeyword record);

    int insertSelective(WechatKeyword record);

    WechatKeyword selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WechatKeyword record);

    int updateByPrimaryKey(WechatKeyword record);
    
    int selectCountByKeyword(String keyword);
    
    WechatKeyword selectByKeyword(String keyword);
    
    List<WechatKeyword> selectListByParams(@Param("keyword")String keyword);
}