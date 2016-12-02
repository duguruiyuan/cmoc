package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.HollowManInfoMapper;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.IHollowManService;
import com.xuequ.cmoc.view.HollowManInfoView;

@Service("hollowManService")
public class HollowManServiceImpl implements IHollowManService {

	@Autowired
	private HollowManInfoMapper hollowManInfoMapper;
	
	@Override
	public List<HollowManInfoView> selectByPage(Page<HollowManInfoView> page) {
		return hollowManInfoMapper.selectByPage(page);
	}

}
