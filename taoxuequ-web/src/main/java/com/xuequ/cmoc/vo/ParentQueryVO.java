package com.xuequ.cmoc.vo;

import org.apache.commons.lang.StringUtils;

import com.xuequ.cmoc.model.GridBase;

public class ParentQueryVO extends GridBase {

	private String familyNo;
	
	private String startDate;
	
	private String endDate;
	
	private Integer parentId;
	
	private String parentName;
	
	private String parentMobile;

	public String getFamilyNo() {
		return StringUtils.isNotBlank(familyNo) ? familyNo : null;
	}

	public void setFamilyNo(String familyNo) {
		this.familyNo = familyNo;
	}

	public String getStartDate() {
		return StringUtils.isNotBlank(startDate) ? startDate : null;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return StringUtils.isNotBlank(endDate) ? endDate : null;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return StringUtils.isNotBlank(parentName) ? parentName : null;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentMobile() {
		return StringUtils.isNotBlank(parentMobile) ? parentMobile : null;
	}

	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}
	
	
}
