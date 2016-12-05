package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.model.ActivityHmSign;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.ActivityHmSignView;

public interface IActivityHmService {

	List<ActivityHmSignView> selectListByPage(Page<ActivityHmSignView> page);
	
	/**
	 * 根据透明人openid获取接收微信消息信息
	 * @param openid
	 * @return
	 */
	ActivityHmSign selectForMessage(String openid);
}
