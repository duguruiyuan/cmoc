package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.ChildSignInfoMapper;
import com.xuequ.cmoc.dao.ParentInfoMapper;
import com.xuequ.cmoc.dao.SysCommonMapper;
import com.xuequ.cmoc.model.ChildSignInfo;
import com.xuequ.cmoc.model.ParentInfo;
import com.xuequ.cmoc.model.WechatUserInfo;
import com.xuequ.cmoc.service.IChildSignInfoService;
import com.xuequ.cmoc.utils.BeanUtils;

@Service("childSignInfoService")
public class ChildSignInfoServiceImpl implements IChildSignInfoService {
	
	@Autowired
	private ChildSignInfoMapper childSignInfoMapper;
	@Autowired
	private ParentInfoMapper parentInfoMapper;
	@Autowired
	private SysCommonMapper sysCommonMapper;
	
	@Override
	public int selectCountByOrderNo(String orderNo) {
		return childSignInfoMapper.selectCountByOrderNo(orderNo);
	}
	@Override
	public void addAndUpdate(ChildSignInfo vo, WechatUserInfo userInfo) {
		ChildSignInfo info = BeanUtils.copyAs(vo, ChildSignInfo.class);
		String familyNo = childSignInfoMapper.selectFamilyNoByOrderNo(info.getOrderNo());
		if(StringUtils.isBlank(familyNo)) {
			familyNo = sysCommonMapper.selectFamilyNoSeq();
		}
		ParentInfo parentInfo = parentInfoMapper.selectByOpenid(userInfo.getOpenid());
		if(parentInfo == null) {
			parentInfo = new ParentInfo();
			parentInfo.setOpenid(userInfo.getOpenid());
			parentInfo.setParentMobile(info.getEmerMobile());
			parentInfo.setParentName(info.getEmerName());
			parentInfo.setRelation(info.getSignRelation());
			parentInfo.setFamilyNo(familyNo);
			parentInfo.setCity(userInfo.getProvince() + " " + userInfo.getCity());
			parentInfo.setHeadImg(userInfo.getHeadimgurl());
			parentInfoMapper.insertSelective(parentInfo);
		}else {
			parentInfo.setParentName(info.getEmerName());
			parentInfo.setParentMobile(info.getEmerMobile());
			parentInfo.setRelation(info.getSignRelation());
			parentInfo.setCity(userInfo.getProvince() + " " + userInfo.getCity());
			parentInfo.setHeadImg(userInfo.getHeadimgurl());
			parentInfoMapper.updateByPrimaryKeySelective(parentInfo);
		}
		info.setFamilyNo(familyNo);
		if(info.getId() != null) {
			childSignInfoMapper.updateByPrimaryKey(info);
		}else {
			childSignInfoMapper.insertSelective(info);
		}
	}
	@Override
	public List<ChildSignInfo> selectListByOrderNo(String orderNo) {
		return childSignInfoMapper.selectListByOrderNo(orderNo);
	}
	@Override
	public ChildSignInfo selectById(Integer id) {
		return childSignInfoMapper.selectByPrimaryKey(id);
	}
	@Override
	public ChildSignInfo selectByParam(String orderNo, String openid) {
		return childSignInfoMapper.selectByParam(orderNo, openid);
	}

}
