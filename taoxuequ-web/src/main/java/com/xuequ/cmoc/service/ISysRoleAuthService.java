package com.xuequ.cmoc.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;

import com.xuequ.cmoc.model.SysMenu;

public interface ISysRoleAuthService {

	List<SysMenu> fillMenuInfo(Integer userId, Integer userType);
	
	/**
	 * 加载URL与操作的映射关系
	 * @author 胡启萌
	 * @date 2015年9月7日
	 */
	Map<String, Collection<ConfigAttribute>> loadAllResourceMenu();
}
