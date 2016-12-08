package com.xuequ.cmoc.view;

import java.io.Serializable;
import java.util.Date;

import com.xuequ.cmoc.utils.DateUtil;

public class ChildActRecordView implements Serializable {

	private static final long serialVersionUID = 2854370660584906202L;
	
	private String activityName;
	
	private String activityNum;
	
	private Date startDate;
	
	private Date endDate;
	
	private Integer activityId;
	
	private String marineName;
	
	private Integer marineId;
	
	private String childName;

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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getMarineName() {
		return marineName;
	}

	public void setMarineName(String marineName) {
		this.marineName = marineName;
	}

	public Integer getMarineId() {
		return marineId;
	}

	public void setMarineId(Integer marineId) {
		this.marineId = marineId;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	public int getStatus() {
		Date currDate = new Date();
		if(DateUtil.compare(currDate, endDate) == 1) {
			return 2;
		}else {
			if(DateUtil.compare(currDate, startDate) == 1) {
				return 1;
			}
		}
		return 0;
	}

	
}
