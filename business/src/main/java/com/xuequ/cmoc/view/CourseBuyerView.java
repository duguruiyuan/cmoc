package com.xuequ.cmoc.view;

import java.math.BigDecimal;

import com.xuequ.cmoc.model.CourseBuyerInfo;
import com.xuequ.cmoc.model.ParentInfo;

public class CourseBuyerView extends ParentInfo {

	private static final long serialVersionUID = 6477502390468569364L;

	private BigDecimal resAmount;
	
	private Integer productId;
	
	private String orderNo;
	
	private String productType;
	
	private String courseName;
	
	private String courseType;
	
	private BigDecimal totalAmount;
	
	public BigDecimal getResAmount() {
		return resAmount;
	}

	public void setResAmount(BigDecimal resAmount) {
		this.resAmount = resAmount;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
}
