package com.xuequ.cmoc.view;

import com.xuequ.cmoc.model.ActivityMarines;

public class ActivityMarinesView extends ActivityMarines {

	private static final long serialVersionUID = 83327574988055913L;

	private String activityName;
	
	private String marineName;
	
	private String activityNum;
	
	private String activityType;
	
	private String marineTeam;

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

	public String getMarineTeam() {
		return marineTeam;
	}

	public void setMarineTeam(String marineTeam) {
		this.marineTeam = marineTeam;
	}
	
}
