package com.xuequ.cmoc.service;

import java.util.List;
import java.util.Map;

import com.xuequ.cmoc.model.SysDictData;
import com.xuequ.cmoc.model.SysDictType;

public interface ISysDictService {

	List<SysDictData> selectListByDictTypeId(Integer dictTypeId);
    
    List<SysDictData> selectActiveAll();
    
    List<SysDictType> selectDictTypeAll();
    
    int updateByPrimaryKeySelective(SysDictType dictType);
    
    int updateByPrimaryKeySelective(SysDictData dictData);
    
    int insertSelective(SysDictType dictType);
    
    int insertSelective(SysDictData dictData);
    
    SysDictData selectDictDataById(Integer id);
    
    SysDictType selectDictTypeById(Integer id);
    
    int selectCountByDictCode(String dictCode);
    
    List<SysDictData> selectListByDictCode(String dictCode);
    
    Map<String, Map<Integer, String>> selectResource();
}
