package com.xuequ.cmoc.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.StringUtils;

import com.xuequ.cmoc.model.SysMenu;
import com.xuequ.cmoc.model.SysResource;
import com.xuequ.cmoc.utils.StringUtil;

public class DefaultSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	private final AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();
	
	@SuppressWarnings("unchecked")
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = ((FilterInvocation)object).getHttpRequest().getServletPath();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && !authenticationTrustResolver.isAnonymous(authentication)) {
        	AppUser appUser = (AppUser)authentication.getPrincipal();
        	Map<String, Collection<ConfigAttribute>> resourceMap = appUser.getResourceMap();
        	for(Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()){ 
        		if(url.equals(entry.getKey())) {
        			SecurityConfig config = (SecurityConfig) entry.getValue().toArray()[0];
        			((FilterInvocation)object).getHttpRequest().setAttribute("menu", getCurrMenu(appUser.getSysMenuList(), config.getAttribute()));
        			return entry.getValue();
        		}
        	}
        	if(!StringUtils.isEmpty(url)) {
        		if(url.contains("/json/")) {
        			Collection<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
        			attributes.add(new SecurityConfig(AuthConstant.ROOT_AUTHORITY));
        			return attributes;
        		}
        	}
        }
        return (Collection<ConfigAttribute>)Collections.EMPTY_LIST;
	}
	
	private static SysResource getCurrMenu(List<SysMenu> menuList, String resourceId) {
		if(!StringUtil.isNullOrEmpty(menuList) && !StringUtils.isEmpty(resourceId)) {
			for(SysMenu menu : menuList) {
				if(String.valueOf(menu.getIdResource()).equals(resourceId)) {
					return menu;
				}
				if(menu.isHasChild()) {
					return getCurrMenu(menu.getSubMenuList(), resourceId);
				}
			}
		}
		return new SysResource();
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return new ArrayList<ConfigAttribute>();
	}

}
