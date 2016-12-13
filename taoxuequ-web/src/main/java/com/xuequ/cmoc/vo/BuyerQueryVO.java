package com.xuequ.cmoc.vo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.xuequ.cmoc.model.GridBase;

public class BuyerQueryVO extends GridBase implements Serializable{

	private static final long serialVersionUID = -1568878196792911479L;

	private String courseName;
	private Integer productId;
	private String mobile;
	private String name;
	private String orderNo;
	private String startDate;
	private String endDate;
	
	public String getCourseName() {
		return StringUtils.isNotBlank(courseName) ? courseName : null;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getMobile() {
		return StringUtils.isNotBlank(mobile) ? mobile : null;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return StringUtils.isNotBlank(name) ? name : null;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrderNo() {
		return StringUtils.isNotBlank(orderNo) ? orderNo : null;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getStartDate() {
		return StringUtils.isNotBlank(startDate) ? startDate : null;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return StringUtils.isNotBlank(endDate) ? endDate : null;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
