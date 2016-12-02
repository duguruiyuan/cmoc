package com.xuequ.cmoc.vo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.xuequ.cmoc.model.GridBase;

public class HollowManQueryVO extends GridBase implements Serializable{

	private static final long serialVersionUID = -1458546887210486420L;
	
	private String hmName;
	
	private String hmMobile;
	
	private String idCard;
	
	private String sex;
	
	private Integer isActive;

	public String getHmName() {
		return StringUtils.isNotBlank(hmName) ? hmName : null;
	}

	public void setHmName(String hmName) {
		this.hmName = hmName;
	}

	public String getHmMobile() {
		return StringUtils.isNotBlank(hmMobile) ? hmMobile : null;
	}

	public void setHmMobile(String hmMobile) {
		this.hmMobile = hmMobile;
	}

	public String getIdCard() {
		return StringUtils.isNotBlank(idCard) ? idCard : null;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getSex() {
		return StringUtils.isNotBlank(sex) ? sex : null;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	
	
	

}
