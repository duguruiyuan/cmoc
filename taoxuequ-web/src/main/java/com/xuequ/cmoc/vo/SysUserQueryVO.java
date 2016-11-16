package com.xuequ.cmoc.vo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.xuequ.cmoc.model.GridBase;

public class SysUserQueryVO extends GridBase implements Serializable {

	private static final long serialVersionUID = -4678372173867890877L;

	private String userName;
	
	private String userAccount;
	
	private Integer validFlag;

	public String getUserName() {
		return StringUtils.isNotBlank(userName) ? userName : null;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccount() {
		return StringUtils.isNotBlank(userAccount) ? userAccount : null;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public Integer getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}
	
	
}
