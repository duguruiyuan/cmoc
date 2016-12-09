package com.xuequ.cmoc.view;

import java.io.Serializable;
import java.util.Date;

public class HollowManTakeView implements Serializable {

	private static final long serialVersionUID = 5610007574974196425L;
	
	private Integer hmId;
	
	private String hmName;
	
	private String hmMobile;
	
	private String activityName;
	
	private String activityNum;
	
	private Date startDate;
	
	private String activityType;
	
	private Integer imageNum;
	
	private Integer videoNum;

	public Integer getHmId() {
		return hmId;
	}

	public void setHmId(Integer hmId) {
		this.hmId = hmId;
	}

	public String getHmName() {
		return hmName;
	}

	public void setHmName(String hmName) {
		this.hmName = hmName;
	}

	public String getHmMobile() {
		return hmMobile;
	}

	public void setHmMobile(String hmMobile) {
		this.hmMobile = hmMobile;
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

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public Integer getImageNum() {
		return imageNum;
	}

	public void setImageNum(Integer imageNum) {
		this.imageNum = imageNum;
	}

	public Integer getVideoNum() {
		return videoNum;
	}

	public void setVideoNum(Integer videoNum) {
		this.videoNum = videoNum;
	}

    
    
}
