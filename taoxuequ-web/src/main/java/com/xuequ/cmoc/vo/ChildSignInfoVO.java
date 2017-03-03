package com.xuequ.cmoc.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;

import com.xuequ.cmoc.validate.Idcard;
import com.xuequ.cmoc.validate.Mobile;
import com.xuequ.cmoc.validate.Name;

public class ChildSignInfoVO implements Serializable {

	private static final long serialVersionUID = 2797312533271035538L;
	
	private Integer rows;
	
	@NotNull(message="小孩身份证不能为空")
	@Idcard
	private String childIdcard;

	@NotNull(message="小孩姓名不能为空")
	@Name
    private String childName;

	@NotNull(message="小孩姓名性别为空")
    private String childSex;

    private String isDisease;

    private String diseaseDesc;
    
    @NotNull(message="紧急联系人不能为空")
    @Name
    private String emerName;

    @NotNull(message="紧急联系电话不能为空")
    @Mobile
    private String emerMobile;

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getChildIdcard() {
		return childIdcard;
	}

	public void setChildIdcard(String childIdcard) {
		this.childIdcard = childIdcard;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
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

	public String getIsDisease() {
		return isDisease;
	}

	public void setIsDisease(String isDisease) {
		if(StringUtils.isNotBlank(isDisease)) {
			if("有".equals(isDisease)) {
				this.isDisease = "Y";
			}else{
				this.isDisease = "N";
			}
		}
	}

	public String getDiseaseDesc() {
		return diseaseDesc;
	}

	public void setDiseaseDesc(String diseaseDesc) {
		this.diseaseDesc = diseaseDesc;
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

}
