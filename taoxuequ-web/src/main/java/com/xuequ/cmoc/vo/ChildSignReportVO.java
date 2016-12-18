package com.xuequ.cmoc.vo;

import org.apache.commons.lang.StringUtils;

import com.xuequ.cmoc.utils.DateUtil;
import com.xuequ.cmoc.view.ChildSignView;

public class ChildSignReportVO extends ChildSignView{

	private static final long serialVersionUID = -32347047380961837L;

	@Override
	public String getChildSex() {
		String childSex = super.getChildSex();
		if(StringUtils.isNotBlank(childSex)) {
			if("F".equals(childSex)) return "男";
			else return "女";
		}
		return "未知";
	}
	
	@Override
	public String getIsDisease() {
		String isDisease = super.getIsDisease();
		if("Y".equals(isDisease)) return "有";
		return "无";
	}
	
	public String getSignTime() {
		return DateUtil.getDate(super.getCreateTime());
	}
	
}
