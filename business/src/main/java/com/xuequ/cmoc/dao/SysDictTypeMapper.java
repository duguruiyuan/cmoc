package com.xuequ.cmoc.dao;

import java.util.List;

import com.xuequ.cmoc.model.SysDictType;

public interface SysDictTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDictType record);

    int insertSelective(SysDictType record);

    SysDictType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDictType record);

    int updateByPrimaryKey(SysDictType record);
    
    List<SysDictType> selectDictTypeAll();
    
    List<SysDictType> selectActiveAll();
    
    int selectCountByDictCode(String dictCode);
}