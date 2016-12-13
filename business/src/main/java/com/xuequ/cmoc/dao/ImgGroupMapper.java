package com.xuequ.cmoc.dao;

import java.util.List;

import com.xuequ.cmoc.model.ImgGroup;

public interface ImgGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImgGroup record);

    int insertSelective(ImgGroup record);

    ImgGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImgGroup record);

    int updateByPrimaryKey(ImgGroup record);
    
    List<ImgGroup> selectListByParam(ImgGroup record);
}