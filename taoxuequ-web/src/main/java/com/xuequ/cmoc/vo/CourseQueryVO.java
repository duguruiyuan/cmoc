package com.xuequ.cmoc.vo;

import org.apache.commons.lang.StringUtils;

import com.xuequ.cmoc.model.GridBase;
import com.xuequ.cmoc.utils.StringUtil;

public class CourseQueryVO extends GridBase{

	private String name;
	
	private Integer shelves;
	
	private String courseType;
	
	private String city;
	
	public String getName() {
		return StringUtils.isNotBlank(name) ? name : null;
	}

	public void setName(String name) {
		this.name = name;
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
