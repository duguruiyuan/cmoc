package com.xuequ.cmoc.vo;

import org.apache.commons.lang.StringUtils;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.xuequ.cmoc.model.GridBase;

public class HmResourceQueryVO extends GridBase {

	private String hmName;
	
	private String hmMobile;
	
	private Integer activityId;
	
	private String activityName;
	
	private Integer activityType;
	
	private String startDate;
	
	private String endDate;

	public String getHmName() {
		return StringUtils.isNotBlank(hmName) ? hmName : null;
	}

	public void setHmName(String hmName) {
		this.hmName = hmName;
	}

	public String getHmMobile() {
		return StringUtils.isNotBlank(hmMobile) ? hmMobile : null;
	}

	public void setHmMobile(String hmMobile) {
		this.hmMobile = hmMobile;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return StringUtils.isNotBlank(activityName) ? activityName : null;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
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
