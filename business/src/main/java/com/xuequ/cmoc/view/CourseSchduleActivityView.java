package com.xuequ.cmoc.view;

import java.io.Serializable;
import java.util.Date;

public class CourseSchduleActivityView implements Serializable {
	
	private static final long serialVersionUID = -6419659679293123062L;

	private Integer activityId;
  	
  	private String activityName;
  	
  	private String activityNum;
  	
  	private Date startDate;
  	
  	private Integer activityPeoples;
  	
  	private Integer buyCount;
  	
  	private String isFull;

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getActivityPeoples() {
		return activityPeoples;
	}

	public void setActivityPeoples(Integer activityPeoples) {
		this.activityPeoples = activityPeoples;
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public String getIsFull() {
		return isFull;
	}

	public void setIsFull(String isFull) {
		this.isFull = isFull;
	}

}
