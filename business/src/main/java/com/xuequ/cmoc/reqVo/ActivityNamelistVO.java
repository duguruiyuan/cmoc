package com.xuequ.cmoc.reqVo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import com.xuequ.cmoc.validate.Idcard;
import com.xuequ.cmoc.validate.Mobile;
import com.xuequ.cmoc.validate.Name;

public class ActivityNamelistVO implements Serializable{

	private static final long serialVersionUID = -8038815443583061801L;
	
	private Integer rows;

	private Integer activityId;
	/*
	 * 战队名称	
	 */
	@NotNull(message="战队名称不能为空")
	@NotEmpty(message="战队名称不能为空")
	private String marineName;
	/*
	 * 学员姓名	
	 */
	@NotNull(message="学员姓名不能为空")
	@NotEmpty(message="学员姓名不能为空")
	@Name
	private String childName;
	/*
	 * 小孩证件号
	 */
	@NotNull(message="小孩证件号不能为空")
	@NotEmpty(message="小孩证件号不能为空")
	@Idcard
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
	@NotNull(message="紧急联系号码不能为空")
	@NotEmpty(message="紧急联系号码不能为空")
	@Mobile
	private String emerMobile;
	/*
	 * 老师姓名	
	 */
	private String teatherName;
	/*
	 * 老师手机号码	
	 */
	private String teacherMobile;
	
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getMarineName() {
		return StringUtils.isNotBlank(marineName) ? marineName : null;
	}
	public void setMarineName(String marineName) {
		this.marineName = marineName;
	}
	public String getChildName() {
		return StringUtils.isNotBlank(childName) ? childName : null;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getChildIdcard() {
		return StringUtils.isNotBlank(childIdcard) ? childIdcard : null;
	}
	public void setChildIdcard(String childIdcard) {
		this.childIdcard = childIdcard;
	}
	public String getChildSex() {
		return StringUtils.isNotBlank(childSex) ? childSex : null;
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
		return StringUtils.isNotBlank(emerName) ? emerName : null;
	}
	public void setEmerName(String emerName) {
		this.emerName = emerName;
	}
	public String getEmerMobile() {
		return StringUtils.isNotBlank(emerMobile) ? emerMobile : null;
	}
	public void setEmerMobile(String emerMobile) {
		this.emerMobile = emerMobile;
	}
	public String getTeatherName() {
		return StringUtils.isNotBlank(teatherName) ? teatherName : null;
	}
	public void setTeatherName(String teatherName) {
		this.teatherName = teatherName;
	}
	public String getTeacherMobile() {
		return StringUtils.isNotBlank(teacherMobile) ? teacherMobile : null;
	}
	public void setTeacherMobile(String teacherMobile) {
		this.teacherMobile = teacherMobile;
	}
	
}
