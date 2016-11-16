package com.xuequ.cmoc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.SysUserMapper;
import com.xuequ.cmoc.dao.SysUserRoleRelMapper;
import com.xuequ.cmoc.model.SysRole;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.model.SysUserRoleRel;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.reqVo.AddAndUpdateUserVO;
import com.xuequ.cmoc.service.ISysUserService;
import com.xuequ.cmoc.utils.BeanUtils;
import com.xuequ.cmoc.utils.MD5Util;
import com.xuequ.cmoc.view.SysUserInfo;

@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleRelMapper sysUserRoleRelMapper;

	@Override
	public List<SysUser> selectListByPage(Page<SysUser> page) {
		return sysUserMapper.selectListByPage(page);
	}

	@Override
	public int addAndUpdateUser(AddAndUpdateUserVO vo, SysUser sysUser) {
		SysUser user = BeanUtils.copyAs(vo, SysUser.class);
		if(vo.getIdUser() == null) {
			user.setPassword(MD5Util.md5(vo.getPassword()));
			user.setCreator(sysUser.getUserAccount());
			user.setCreateTime(new Date());
			sysUserMapper.insertSelective(user);
		}else {
			user.setLastUpdator(sysUser.getUserAccount());
			user.setLastUpdateTime(new Date());
			sysUserMapper.updateByPrimaryKeySelective(user);
			sysUserRoleRelMapper.deleteByIdUser(vo.getIdUser());
		}
		vo.setIdUser(user.getIdUser());
		return sysUserRoleRelMapper.insertBatch(vo);
	}

	@Override
	public SysUserInfo selectUserInfo(Integer idUser) {
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(idUser);
		SysUserInfo info = BeanUtils.copyAs(sysUser, SysUserInfo.class);
		List<SysUserRoleRel> relList = sysUserRoleRelMapper.selectListByIdUser(idUser);
		info.setRelList(relList);
		return info;
	}

}
