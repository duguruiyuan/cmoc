package com.xuequ.cmoc.dao;

import java.util.List;

import com.xuequ.cmoc.model.SysDictData;
import com.xuequ.cmoc.model.SysDictType;

public interface SysDictDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDictData record);

    int insertSelective(SysDictData record);

    SysDictData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDictData record);

    int updateByPrimaryKey(SysDictData record);
    
    List<SysDictData> selectListByDictTypeId(Integer dictTypeId);
    
    List<SysDictData> selectActiveAll();
    
    int selectCountByDictTypeId(Integer dictTypeId);
    
    List<SysDictData> selectListByDictCode(String dictCode);
}