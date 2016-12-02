package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.HollowManInfoView;

public interface IHollowManService {
	
	/**
	 * 分页查询透明人信息
	 * @param page
	 * @return
	 */
	List<HollowManInfoView> selectByPage(Page<HollowManInfoView> page);

}
