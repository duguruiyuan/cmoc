package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.SysResourceMapper;
import com.xuequ.cmoc.model.SysResource;
import com.xuequ.cmoc.service.ISysMenuService;

@Service
public class SysMenuServiceImpl implements ISysMenuService {

	private SysResourceMapper sysResourceMapper;
	
	@Override
	public List<SysResource> queryAllMenu() {
		return sysResourceMapper.selectAll();
	}

}
