package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.dao.ChildSignInfoMapper;
import com.xuequ.cmoc.dao.ParentInfoMapper;
import com.xuequ.cmoc.model.ParentInfo;
import com.xuequ.cmoc.reqVo.ParentInfoVO;
import com.xuequ.cmoc.service.IParentInfoService;
import com.xuequ.cmoc.view.ChildActRecordView;

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
		int count = childSignInfoMapper.selectHasChild(parentInfo.getChildName(), parentInfo.getSignMobile());
		if(count == 0) {
			return new RspResult(StatusEnum.PARENT_BIND_ERROR);
		}
		parentInfoMapper.insertSelective(parentInfo);
		return new RspResult(StatusEnum.SUCCESS);
	}

}
