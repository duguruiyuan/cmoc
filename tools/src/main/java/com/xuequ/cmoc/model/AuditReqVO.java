package com.xuequ.cmoc.model;

import java.io.Serializable;

public class AuditReqVO implements Serializable {

	private static final long serialVersionUID = -1714548021815844557L;

	private String ids;
	
	/*
	 *状态 0不通过 1通过 
	 */
	private Integer status;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
