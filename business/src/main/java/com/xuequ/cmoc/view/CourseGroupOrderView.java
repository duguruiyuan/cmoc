package com.xuequ.cmoc.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CourseGroupOrderView implements Serializable {

	private static final long serialVersionUID = 902783469743867813L;

	private BigDecimal totalPrice; 
  	private Integer member; 
  	private String orderStatus;
  	private String activityName; 
  	private Date startDate;
  	private String orderNo;
  	private String activityAddr;
  	private String activityNum;
  	private String emerName;
  	private String emerMobile;
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getMember() {
		return member;
	}
	public void setMember(Integer member) {
		this.member = member;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getActivityAddr() {
		return activityAddr;
	}
	public void setActivityAddr(String activityAddr) {
		this.activityAddr = activityAddr;
	}
	public String getActivityNum() {
		return activityNum;
	}
	public void setActivityNum(String activityNum) {
		this.activityNum = activityNum;
	}
	public String getEmerName() {
		return emerName;
	}
	public void setEmerName(String emerName) {
		this.emerName = emerName;
	}
	public String getEmerMobile() {
		return emerMobile;
	}
	public void setEmerMobile(String emerMobile) {
		this.emerMobile = emerMobile;
	}
  	
  	
}
