package com.xuequ.cmoc.view;

import com.xuequ.cmoc.model.ActivityTeacher;

public class ActivityTeacherView extends ActivityTeacher{

	private static final long serialVersionUID = -2300638815600220347L;

	private String activityName;
	
	private String marineName;
	
	private String activityNum;
	
	private String activityType;
	
	private Integer lineId;
	
	private String lineName;

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getMarineName() {
		return marineName;
	}

	public void setMarineName(String marineName) {
		this.marineName = marineName;
	}

	public String getActivityNum() {
		return activityNum;
	}

	public void setActivityNum(String activityNum) {
		this.activityNum = activityNum;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	
	
}
