package com.xuequ.cmoc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.SysDictDataMapper;
import com.xuequ.cmoc.dao.SysDictTypeMapper;
import com.xuequ.cmoc.model.SysDictData;
import com.xuequ.cmoc.model.SysDictType;
import com.xuequ.cmoc.service.ISysDictService;

@Service("sysDictService")
public class SysDictServiceImpl implements ISysDictService {

	@Autowired
	private SysDictTypeMapper sysDictTypeMapper;
	@Autowired
	private SysDictDataMapper sysDictDataMapper;
	
	@Override
	public List<SysDictData> selectListByDictTypeId(Integer dictTypeId) {
		return sysDictDataMapper.selectListByDictTypeId(dictTypeId);
	}

	@Override
	public List<SysDictData> selectActiveAll() {
		return sysDictDataMapper.selectActiveAll();
	}

	@Override
	public List<SysDictType> selectDictTypeAll() {
		return sysDictTypeMapper.selectDictTypeAll();
	}

	@Override
	public int updateByPrimaryKeySelective(SysDictType dictType) {
		return sysDictTypeMapper.updateByPrimaryKeySelective(dictType);
	}

	@Override
	public int updateByPrimaryKeySelective(SysDictData dictData) {
		return sysDictDataMapper.updateByPrimaryKeySelective(dictData);
	}

	@Override
	public int insertSelective(SysDictType dictType) {
		return sysDictTypeMapper.insertSelective(dictType);
	}

	@Override
	public int insertSelective(SysDictData dictData) {
		int count = sysDictDataMapper.selectCountByDictTypeId(dictData.getDictTypeId());
		dictData.setDictDataKey(count + 1);
		return sysDictDataMapper.insertSelective(dictData);
	}

	@Override
	public SysDictData selectDictDataById(Integer id) {
		return sysDictDataMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysDictType selectDictTypeById(Integer id) {
		return sysDictTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int selectCountByDictCode(String dictCode) {
		return sysDictTypeMapper.selectCountByDictCode(dictCode);
	}

	@Override
	public List<SysDictData> selectListByDictCode(String dictCode) {
		return sysDictDataMapper.selectListByDictCode(dictCode);
	}

	@Override
	public Map<String, List<SysDictData>> selectResource() {
		List<SysDictType> dictTypeList = sysDictTypeMapper.selectActiveAll();
		List<SysDictData> dictDataList = sysDictDataMapper.selectActiveAll();
		Map<String, List<SysDictData>> map = new HashMap<>();
		for(SysDictType dictType : dictTypeList) {
			List<SysDictData> temp = new ArrayList<>();
			for(SysDictData dictData : dictDataList) {
				if(dictData.getDictTypeId() == dictType.getId()) {
					temp.add(dictData);
				}
			}
			map.put(dictType.getDictCode(), temp);
		}
		return map;
	}

}
