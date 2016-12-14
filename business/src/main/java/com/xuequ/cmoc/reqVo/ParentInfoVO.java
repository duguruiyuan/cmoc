package com.xuequ.cmoc.reqVo;

import com.xuequ.cmoc.model.ParentInfo;

public class ParentInfoVO extends ParentInfo{

	private static final long serialVersionUID = -166671551005671974L;

	private String childName;
	
	private String signMobile;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getSignMobile() {
		return signMobile;
	}

	public void setSignMobile(String signMobile) {
		this.signMobile = signMobile;
	}
}
