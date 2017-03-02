package com.xuequ.cmoc.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.model.ParentInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.reqVo.ParentInfoVO;
import com.xuequ.cmoc.view.ChildActRecordView;
import com.xuequ.cmoc.view.ParentInfoView;

public interface IParentInfoService {
	
	int selectCountByOpenid(String openid);
	
	ParentInfo selectByOpenid(String openid);
	
	List<ChildActRecordView> selectChildActRecord(String openid);

	RspResult addParentBind(ParentInfoVO parentInfo);
	
	List<ParentInfoView> selectBuyTotalByPage(Page<ParentInfoView> page);
	
	List<ParentInfo> selectListByPage(Page<ParentInfo> page);
	
	ParentInfo selectById(Integer custId);
	
	ParentInfo selectByOrderNoOpenid(String openid, String orderNo);
	
	int selectCountByOpenidOrderNo(String openid, String orderNo);
}
