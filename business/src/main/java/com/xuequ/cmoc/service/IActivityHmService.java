package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.ActivityHmSignView;

public interface IActivityHmService {

	List<ActivityHmSignView> selectListByPage(Page<ActivityHmSignView> page);
}
