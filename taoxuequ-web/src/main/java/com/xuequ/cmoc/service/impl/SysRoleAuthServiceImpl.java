package com.xuequ.cmoc.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.common.Constants;
import com.xuequ.cmoc.dao.SysResourceMapper;
import com.xuequ.cmoc.model.SysMenu;
import com.xuequ.cmoc.model.SysResource;
import com.xuequ.cmoc.service.ISysRoleAuthService;
import com.xuequ.cmoc.utils.BeanUtils;
import com.xuequ.cmoc.utils.StringUtil;

@Service("sysRoleAuthService")
public class SysRoleAuthServiceImpl implements ISysRoleAuthService {

	@Autowired
	private SysResourceMapper sysResourceMapper;
	
	public List<SysMenu> fillMenuInfo(String userId, Integer userType) {
		List<SysResource> list = null;
		if(1 == userType) {
			list = sysResourceMapper.selectAll();
		}else {
			list = sysResourceMapper.selectByUserId(userId);
		}
		List<SysMenu> sysMenuList = BeanUtils.copyAs(list, SysMenu.class);
		Map<String, List<SysMenu>> menuMap = new HashMap<String, List<SysMenu>>();
		if(list != null) {
			for(SysMenu menu : sysMenuList) {
				String key = null;
				Integer parentReasourceId = menu.getParentResourceId();
				if(!(parentReasourceId != null && parentReasourceId != 0)) {
					key = Constants.ROOT_MENU_KEY;
				}else {
					key = String.valueOf(parentReasourceId);
				}
				List<SysMenu> childMenuList = menuMap.get(key);
				if(childMenuList == null) {
					childMenuList = new ArrayList<SysMenu>();
				}
				childMenuList.add(menu);
				menuMap.put(key, childMenuList);
			}
			
			for(SysMenu menu : sysMenuList) {
				menu.setSubMenuList(menuMap.get(String.valueOf(menu.getIdResource())));
				if(!StringUtil.isNullOrEmpty(menu.getSubMenuList())) {
					menu.setHasChild(true);
				}
			}
			sysMenuList = menuMap.get(Constants.ROOT_MENU_KEY);
		}
		return sysMenuList;
	}

	public Map<String, Collection<ConfigAttribute>> loadAllResourceMenu() {
		List<SysResource> list = sysResourceMapper.selectAll();
		Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        for(SysResource resource : list) {
        	 Collection<ConfigAttribute> atts = resourceMap.get(resource.getResourceUrl());
        	 if(atts != null) {
        		 atts.add(new SecurityConfig(String.valueOf(resource.getIdResource())));
        	 }else {
        		 Collection<ConfigAttribute> newAtts = new ArrayList<ConfigAttribute>();
        		 ConfigAttribute ca = new SecurityConfig(String.valueOf(resource.getIdResource()));
        		 newAtts.add(ca);
        		 resourceMap.put(resource.getResourceUrl(), newAtts);
        	 }
        }
        return resourceMap;
	}

}
