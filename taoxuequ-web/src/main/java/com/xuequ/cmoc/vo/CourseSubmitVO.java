package com.xuequ.cmoc.vo;

import org.apache.commons.lang.StringUtils;

import com.xuequ.cmoc.model.CourseInfo;
import com.xuequ.cmoc.utils.DateUtil;

public class CourseSubmitVO extends CourseInfo {

	private static final long serialVersionUID = 1L;
	
	private String strStartDate;
	
	private String strEndDate;

	public String getStrStartDate() {
		return strStartDate;
	}

	public void setStrStartDate(String strStartDate) {
		if(StringUtils.isNotBlank(strStartDate)) {
			super.setStartDate(DateUtil.strToDate(strStartDate, 
					DateUtil.DEFAULT_DATE_FORMAT1));
		}
		this.strStartDate = strStartDate;
	}

	public String getStrEndDate() {
		return strEndDate;
	}

	public void setStrEndDate(String strEndDate) {
		if(StringUtils.isNotBlank(strEndDate)) {
			super.setEndDate(DateUtil.strToDate(strEndDate, 
					DateUtil.DEFAULT_DATE_FORMAT1));
		}
		this.strEndDate = strEndDate;
	}

}
