package com.xuequ.cmoc.view;

import java.math.BigDecimal;

import com.xuequ.cmoc.model.ChildSignInfo;

public class ChildSignView extends ChildSignInfo {

	private static final long serialVersionUID = 696468525790695888L;
	
  	private String productType;
  	
  	private BigDecimal resAmount;
  	
  	private BigDecimal totalPrice;
  	
  	private Integer productId;
  	
  	private String courseName;
  	
  	private String courseType;

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public BigDecimal getResAmount() {
		return resAmount;
	}

	public void setResAmount(BigDecimal resAmount) {
		this.resAmount = resAmount;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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
  	
  	

}
