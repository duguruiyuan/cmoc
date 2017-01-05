package com.xuequ.cmoc.reqVo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class ActivityNamelistVO implements Serializable{

	private static final long serialVersionUID = -8038815443583061801L;

	private Integer activityId;
	/*
	 * 战队名称	
	 */
	private String marineName;
	/*
	 * 学员姓名	
	 */
	private String childName;
	/*
	 * 小孩证件号
	 */
	private String childIdcard;
	/*
	 * 小孩性别
	 */
	private String childSex;
	/*
	 * 小孩年龄
	 */
	private Integer childAge;
	/*
	 * 紧急联系人姓名
	 */
	private String emerName;
	/*
	 * 紧急联系号码
	 */
	private String emerMobile;
	/*
	 * 老师姓名	
	 */
	private String teatherName;
	/*
	 * 老师手机号码	
	 */
	private String teacherMobile;
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
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getChildIdcard() {
		return childIdcard;
	}
	public void setChildIdcard(String childIdcard) {
		this.childIdcard = childIdcard;
	}
	public String getChildSex() {
		return childSex;
	}
	public void setChildSex(String childSex) {
		if(StringUtils.isNotBlank(childSex)) {
			if("男".equals(childSex)) {
				this.childSex = "F";
			}else{
				this.childSex = "M";
			}
		}
	}
	public Integer getChildAge() {
		return childAge;
	}
	public void setChildAge(Integer childAge) {
		this.childAge = childAge;
	}
	public String getEmerName() {
		return emerName;
	}
	public void setEmerName(String emerName) {
		this.emerName = emerName;
	}
	public String getEmerMobile() {
		return emerMobile;
	}
	public void setEmerMobile(String emerMobile) {
		this.emerMobile = emerMobile;
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
	
}
