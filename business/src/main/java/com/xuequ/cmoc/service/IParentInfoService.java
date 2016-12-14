package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.model.ParentInfo;
import com.xuequ.cmoc.reqVo.ParentInfoVO;
import com.xuequ.cmoc.view.ChildActRecordView;

public interface IParentInfoService {
	
	int selectCountByOpenid(String openid);
	
	ParentInfo selectByOpenid(String openid);
	
	List<ChildActRecordView> selectChildActRecord(String openid);

	RspResult addParentBind(ParentInfoVO parentInfo);
}
