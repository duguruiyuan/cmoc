package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.dao.ChildSignInfoMapper;
import com.xuequ.cmoc.dao.ParentInfoMapper;
import com.xuequ.cmoc.model.ChildSignInfo;
import com.xuequ.cmoc.model.ParentInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.reqVo.ParentInfoVO;
import com.xuequ.cmoc.service.IParentInfoService;
import com.xuequ.cmoc.view.ChildActRecordView;
import com.xuequ.cmoc.view.ParentInfoView;

@Service("parentInfoService")
public class ParentInfoServiceImpl implements IParentInfoService {
	
	@Autowired
	private ParentInfoMapper parentInfoMapper;
	@Autowired
	private ChildSignInfoMapper childSignInfoMapper;
	
	@Override
	public ParentInfo selectByOpenid(String openid) {
		return parentInfoMapper.selectByOpenid(openid);
	}

	@Override
	public List<ChildActRecordView> selectChildActRecord(String openid) {
		return parentInfoMapper.selectChildActRecord(openid);
	}

	@Override
	public int selectCountByOpenid(String openid) {
		return parentInfoMapper.selectCountByOpenid(openid);
	}

	@Override
	public RspResult addParentBind(ParentInfoVO parentInfo) {
		ChildSignInfo signInfo = childSignInfoMapper.selectByChildNameMobile(
				parentInfo.getChildName(), parentInfo.getSignMobile());
		if(signInfo == null) {
			return new RspResult(StatusEnum.PARENT_BIND_ERROR);
		}
		parentInfo.setFamilyNo(signInfo.getFamilyNo());
		parentInfo.setParentMobile(parentInfo.getSignMobile());
		parentInfoMapper.insertSelective(parentInfo);
		return new RspResult(StatusEnum.SUCCESS);
	}

	@Override
	public List<ParentInfoView> selectBuyTotalByPage(Page<ParentInfoView> page) {
		return parentInfoMapper.selectBuyTotalByPage(page);
	}

	@Override
	public List<ParentInfo> selectListByPage(Page<ParentInfo> page) {
		return parentInfoMapper.selectListByPage(page);
	}

}
