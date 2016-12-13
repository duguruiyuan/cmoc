package com.xuequ.cmoc.vo;

import org.apache.commons.lang.StringUtils;

import com.xuequ.cmoc.model.GridBase;
import com.xuequ.cmoc.utils.StringUtil;

public class CourseQueryVO extends GridBase{

	private String courseName;
	
	private Integer shelves;
	
	private String courseType;
	
	private String city;
	
	public String getCourseName() {
		return StringUtils.isNotBlank(courseName) ? courseName : null;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getShelves() {
		return shelves;
	}

	public void setShelves(Integer shelves) {
		this.shelves = shelves;
	}

	public String getCourseType() {
		return StringUtils.isNotBlank(courseType) ? courseType : null;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getCity() {
		return StringUtils.isNotBlank(city) ? city : null;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
}
