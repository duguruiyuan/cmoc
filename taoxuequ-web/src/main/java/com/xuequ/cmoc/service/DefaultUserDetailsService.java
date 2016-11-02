package com.xuequ.cmoc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.xuequ.cmoc.auth.AppUser;
import com.xuequ.cmoc.auth.AuthConstant;
import com.xuequ.cmoc.auth.AuthException;
import com.xuequ.cmoc.dao.SysUserMapper;
import com.xuequ.cmoc.model.SysMenu;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.utils.BeanUtils;

public class DefaultUserDetailsService {
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private ISysRoleAuthService sysRoleAuthService;
	
	/**
     * 组装用户详情对象：UserDetails
     * @param userName
     * @param userPassword
     * @return
     */
	public AppUser setUserDetails(String userName, String userPassword) throws AuthException,Exception{
		SysUser sysUser = sysUserMapper.selectUser(userName, userPassword);
		if(sysUser == null) {
			throw new com.xuequ.cmoc.auth.AuthException(AuthConstant.USER_PASSWORD_NOT_MATCH_MESSAGE);
		}
		AppUser user = loadUser(sysUser.getIdUser(), sysUser.getUserType());
		BeanUtils.copy(sysUser, user);
		return user;
	}

	private AppUser loadUser(String userId, Integer userType)
			throws Exception {
		AppUser appUser = this.getAppUser(userId, userType);
		//角色与资源的映射关系
		appUser.setResourceMap(sysRoleAuthService.loadAllResourceMenu());
		return appUser;
	}
	
	/**
     * 设置用户权限
     *
     * @param userName
     * @return
     */
    private AppUser getAppUser(String userId, Integer userType) throws Exception{
    	AppUser appUser = new AppUser();
    	List<SysMenu> list = sysRoleAuthService.fillMenuInfo(userId, userType);
    	appUser.setSysMenuList(list);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		setResource(authorities, list);
		appUser.setAuthorities(authorities);
        return appUser;
    }
    
    private static void setResource(List<GrantedAuthority> authorities, List<SysMenu> list) {
    	for(SysMenu resource : list) {
			 GrantedAuthority auth = new SimpleGrantedAuthority(String.valueOf(resource.getIdResource()));
			 authorities.add(auth);
			 if(resource.isHasChild()) {
				 setResource(authorities, resource.getSubMenuList());
			 }
		 }
    }
}
