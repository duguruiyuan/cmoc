package com.xuequ.cmoc.reqVo;

import com.xuequ.cmoc.model.ChildSignInfo;

public class CourseSignVO extends ChildSignInfo {

	private static final long serialVersionUID = 5743949453539622570L;

	private String openid;
	
	private String productType;
	
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

}
