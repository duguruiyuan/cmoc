package com.xuequ.cmoc.reqVo;

import java.io.Serializable;

public class ActivityNamelistVO implements Serializable{

	private static final long serialVersionUID = -8038815443583061801L;

	private Integer activityId;
	/*
	 * 战队名称	
	 */
	private String marinesName;
	/*
	 * 学员姓名	
	 */
	private String childName;
	/*
	 * 爸爸姓名	
	 */
	private String fatherName;
	/*
	 * 爸爸手机号码	
	 */
	private String fatherMobile;
	/*
	 * 妈妈姓名	
	 */
	private String motherName;
	/*
	 * 妈妈手机号码	
	 */
	private String motherMobile;
	/*
	 * 小孩头衔	
	 */
	private String childTitle;
	/*
	 * 老师姓名	
	 */
	private String teatherName;
	/*
	 * 老师手机号码	
	 */
	private String teacherMobile;
	/*
	 * 透明人姓名	
	 */
	private String hmName;
	/*
	 * 透明人手机号码
	 */
	private String hmMobile;
	
	
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getMarinesName() {
		return marinesName;
	}
	public void setMarinesName(String marinesName) {
		this.marinesName = marinesName;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getFatherMobile() {
		return fatherMobile;
	}
	public void setFatherMobile(String fatherMobile) {
		this.fatherMobile = fatherMobile;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getMotherMobile() {
		return motherMobile;
	}
	public void setMotherMobile(String motherMobile) {
		this.motherMobile = motherMobile;
	}
	public String getChildTitle() {
		return childTitle;
	}
	public void setChildTitle(String childTitle) {
		this.childTitle = childTitle;
	}
	public String getTeatherName() {
		return teatherName;
	}
	public void setTeatherName(String teatherName) {
		this.teatherName = teatherName;
	}
	public String getTeacherMobile() {
		return teacherMobile;
	}
	public void setTeacherMobile(String teacherMobile) {
		this.teacherMobile = teacherMobile;
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
	
	

}
