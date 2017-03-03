package com.xuequ.cmoc.reqVo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.xuequ.cmoc.model.GridBase;

public class CourseSignOrderVO extends GridBase implements Serializable {

	private static final long serialVersionUID = 6653278962546881946L;
	
	private Integer orderId;
	
	private String orderStatus;
	
	private Integer courseId;
	
	private Integer activityId;
	
	private String signName;
	
	private String signPhone;
	
	private String activityStartDate;
	
	private String activityEndDate;
	
	private String orderStartTime;
	
	private String orderEndTime;
	
	private String openid;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return StringUtils.isNotBlank(orderStatus) ? orderStatus : null;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getSignName() {
		return StringUtils.isNotBlank(signName) ? signName : null;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getSignPhone() {
		return StringUtils.isNotBlank(signPhone) ? signPhone : null;
	}

	public void setSignPhone(String signPhone) {
		this.signPhone = signPhone;
	}

	public String getActivityStartDate() {
		return activityStartDate;
	}

	public void setActivityStartDate(String activityStartDate) {
		this.activityStartDate = activityStartDate;
	}

	public String getActivityEndDate() {
		return StringUtils.isNotBlank(activityEndDate) ? activityEndDate : null;
	}

	public void setActivityEndDate(String activityEndDate) {
		this.activityEndDate = activityEndDate;
	}

	public String getOrderStartTime() {
		return StringUtils.isNotBlank(orderStartTime) ? orderStartTime : null;
	}

	public void setOrderStartTime(String orderStartTime) {
		this.orderStartTime = orderStartTime;
	}

	public String getOrderEndTime() {
		return StringUtils.isNotBlank(orderEndTime) ? orderEndTime : null;
	}

	public void setOrderEndTime(String orderEndTime) {
		this.orderEndTime = orderEndTime;
	}

	public String getOpenid() {
		return StringUtils.isNotBlank(openid) ? openid : null;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}
