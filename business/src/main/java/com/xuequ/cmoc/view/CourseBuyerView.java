package com.xuequ.cmoc.view;

import java.math.BigDecimal;

import com.xuequ.cmoc.model.CourseBuyerInfo;

public class CourseBuyerView extends CourseBuyerInfo {

	private static final long serialVersionUID = 6477502390468569364L;

	private BigDecimal resAmount;
	
	private Integer courseId;
	
	private String orderNo;
	
	private String productType;

	public BigDecimal getResAmount() {
		return resAmount;
	}

	public void setResAmount(BigDecimal resAmount) {
		this.resAmount = resAmount;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	
}
