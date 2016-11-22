package com.xuequ.cmoc.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.xuequ.cmoc.model.GridBase;

public class ActivityQueryVO extends GridBase implements Serializable {

	private static final long serialVersionUID = -7450935457391927768L;

    private String activityName;

    private Date startDate;

    private Date endDate;

    private String lineName;

    private Integer lineId;

    private String city;

    private Integer cityId;

    private String activityType;

    private Integer year;

    private Integer month;

	public String getActivityName() {
		return StringUtils.isNotBlank(activityName) ? activityName : null;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
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

	public String getLineName() {
		return StringUtils.isNotBlank(lineName) ? lineName : null;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public String getCity() {
		return StringUtils.isNotBlank(city) ? city : null;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getActivityType() {
		return StringUtils.isNotBlank(activityType) ? activityType : null;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
    
    
    
}
