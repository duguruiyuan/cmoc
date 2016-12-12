package com.xuequ.cmoc.service.impl;

import java.util.List;

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

}
