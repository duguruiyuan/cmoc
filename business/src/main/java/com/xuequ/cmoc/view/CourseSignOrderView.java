package com.xuequ.cmoc.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CourseSignOrderView implements Serializable {

	private static final long serialVersionUID = -6468182930323278548L;

	private Integer orderId;
	private String orderNo; 
	private String orderStatus;
	private Date orderCreateTime;
	private Integer custId;
	private String signName;
	private String signPhone;
	private String openid;
	private Integer courseId;
	private String courseName;
	private Integer signWay;
	private Integer activityId;
	private String activityName;
	private String activityNum;
	private Date activityStartDate;
	private Date paySubmitTime;
	private BigDecimal totalPrice;
	private Integer members;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getOrderCreateTime() {
		return orderCreateTime;
	}
	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	
	public String getSignName() {
		return signName;
	}
	public void setSignName(String signName) {
		this.signName = signName;
	}
	public String getSignPhone() {
		return signPhone;
	}
	public void setSignPhone(String signPhone) {
		this.signPhone = signPhone;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getSignWay() {
		return signWay;
	}
	public void setSignWay(Integer signWay) {
		this.signWay = signWay;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityNum() {
		return activityNum;
	}
	public void setActivityNum(String activityNum) {
		this.activityNum = activityNum;
	}
	public Date getActivityStartDate() {
		return activityStartDate;
	}
	public void setActivityStartDate(Date activityStartDate) {
		this.activityStartDate = activityStartDate;
	}
	public Date getPaySubmitTime() {
		return paySubmitTime;
	}
	public void setPaySubmitTime(Date paySubmitTime) {
		this.paySubmitTime = paySubmitTime;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getMembers() {
		return members;
	}
	public void setMembers(Integer members) {
		this.members = members;
	}
	
	
}
