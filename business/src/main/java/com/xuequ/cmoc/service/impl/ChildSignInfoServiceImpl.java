package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.common.enums.SignResource;
import com.xuequ.cmoc.dao.ChildSignInfoMapper;
import com.xuequ.cmoc.dao.ParentInfoMapper;
import com.xuequ.cmoc.dao.SysCommonMapper;
import com.xuequ.cmoc.model.ChildSignInfo;
import com.xuequ.cmoc.model.ParentInfo;
import com.xuequ.cmoc.model.ProductOrder;
import com.xuequ.cmoc.model.WechatUserInfo;
import com.xuequ.cmoc.service.IChildSignInfoService;
import com.xuequ.cmoc.service.IParentInfoService;
import com.xuequ.cmoc.service.IProductOrderService;
import com.xuequ.cmoc.utils.BeanUtils;

@Service("childSignInfoService")
public class ChildSignInfoServiceImpl implements IChildSignInfoService {
	
	@Autowired
	private ChildSignInfoMapper childSignInfoMapper;
	@Autowired
	private ParentInfoMapper parentInfoMapper;
	@Autowired
	private SysCommonMapper sysCommonMapper;
	@Autowired
	private IProductOrderService productOrderService;
	
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
			vo.setId(info.getId());
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
	@Override
	public int insertCourseSignNamelist(List<ChildSignInfo> childList, Integer orderId) {
		ProductOrder productOrder = productOrderService.selectById(orderId);
		ParentInfo parentInfo = parentInfoMapper.selectByOpenid(productOrder.getOpenid());
		for(ChildSignInfo signInfo : childList) {
			signInfo.setOrderNo(productOrder.getOrderNo());
			signInfo.setProductId(productOrder.getProductId());
			signInfo.setActivityId(productOrder.getActivityId());
			signInfo.setSignResource(SignResource.ONLINE.getCode());
			signInfo.setParentId(parentInfo.getId());
			signInfo.setFamilyNo(parentInfo.getFamilyNo());
			signInfo.setIsEffect(1);
			signInfo.setIsPhoneConfirm(1);
			childSignInfoMapper.insertSelective(signInfo);
		}
		return 1;
	}

}
