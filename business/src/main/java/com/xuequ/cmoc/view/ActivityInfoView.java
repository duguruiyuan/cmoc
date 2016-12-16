package com.xuequ.cmoc.view;

import com.xuequ.cmoc.model.ActivityInfo;

public class ActivityInfoView extends ActivityInfo{

	private static final long serialVersionUID = -2836732846020880503L;
	
	/*
	 * 参加活动人数
	 */
	private int enterPeoples;
	
	private String courseName;
	
	private String courseType;

	public int getEnterPeoples() {
		return enterPeoples;
	}

	public void setEnterPeoples(int enterPeoples) {
		this.enterPeoples = enterPeoples;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	
	
}
