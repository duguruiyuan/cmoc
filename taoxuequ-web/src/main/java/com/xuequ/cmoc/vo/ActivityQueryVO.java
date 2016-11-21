package com.xuequ.cmoc.vo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.xuequ.cmoc.model.GridBase;

public class ActivityQueryVO extends GridBase implements Serializable{

	private static final long serialVersionUID = -8903342077438253502L;
	
	private Integer activityId;
	
	private String childName;
	
	private String marineName;

	private String activityType;
	
	private String activityName;
	
	private String startDate;
	
	private String endDate;

	
	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getChildName() {
		return StringUtils.isNotBlank(childName) ? childName : null;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getMarineName() {
		return StringUtils.isNotBlank(marineName) ? marineName : null;
	}

	public void setMarineName(String marineName) {
		this.marineName = marineName;
	}

	public String getActivityType() {
		return StringUtils.isNotBlank(activityType) ? activityType : null;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getActivityName() {
		return StringUtils.isNotBlank(activityName) ? activityName : null;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
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
