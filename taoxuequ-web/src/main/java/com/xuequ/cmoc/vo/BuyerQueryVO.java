package com.xuequ.cmoc.vo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.xuequ.cmoc.model.GridBase;

public class BuyerQueryVO extends GridBase implements Serializable{

	private static final long serialVersionUID = -1568878196792911479L;

	private String courseName;
	private Integer productId;
	private String childName;
	private String emerMobile;
	private String emerName;
	private String orderNo;
	private String startDate;
	private String endDate;
	private Integer isPhoneConfirm;
	private String status;
	private String orderStatus;
	private Integer parentId;
	
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
	public String getChildName() {
		return StringUtils.isNotBlank(childName) ? childName : null;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getEmerMobile() {
		return StringUtils.isNotBlank(emerMobile) ? emerMobile : null;
	}
	public void setEmerMobile(String emerMobile) {
		this.emerMobile = emerMobile;
	}
	public String getEmerName() {
		return StringUtils.isNotBlank(emerName) ? emerName : null;
	}
	public void setEmerName(String emerName) {
		this.emerName = emerName;
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
	public Integer getIsPhoneConfirm() {
		return isPhoneConfirm;
	}
	public void setIsPhoneConfirm(Integer isPhoneConfirm) {
		this.isPhoneConfirm = isPhoneConfirm;
	}
	public String getStatus() {
		return StringUtils.isNotBlank(status) ? status : null;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderStatus() {
		return StringUtils.isNotBlank(orderStatus) ? orderStatus : null;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
