package com.xuequ.cmoc.model;

import java.io.Serializable;
import java.util.Date;

public class ChildSignInfo implements Serializable {
    private Integer id;

    private String childIdcard;

    private String childName;

    private String childMobile;

    private Integer childAge;

    private String childSex;

    private String isDisease;

    private String diseaseDesc;
    
    private Integer childHeight;
    
    private Integer childWeight;

    private String emerName;

    private String emerMobile;

    private String city;

    private Integer productId;
    
    private Integer activityId;

    private String signResource;

    private String signRelation;

    private Integer parentId;

    private String familyNo;

    private Date createTime;

    private Integer isEffect;

    private String status;
    
    private String orderNo;
    
    private Integer isPhoneConfirm;
    
    private Integer updateUserId; 
    
    private String updater;
    
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChildIdcard() {
        return childIdcard;
    }

    public void setChildIdcard(String childIdcard) {
        this.childIdcard = childIdcard == null ? null : childIdcard.trim();
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName == null ? null : childName.trim();
    }

    public String getChildMobile() {
        return childMobile;
    }

    public void setChildMobile(String childMobile) {
        this.childMobile = childMobile == null ? null : childMobile.trim();
    }

    public Integer getChildAge() {
        return childAge;
    }

    public void setChildAge(Integer childAge) {
        this.childAge = childAge;
    }

    public String getChildSex() {
        return childSex;
    }

    public void setChildSex(String childSex) {
        this.childSex = childSex == null ? null : childSex.trim();
    }

    public String getIsDisease() {
        return isDisease;
    }

    public void setIsDisease(String isDisease) {
        this.isDisease = isDisease == null ? null : isDisease.trim();
    }

    public String getDiseaseDesc() {
        return diseaseDesc;
    }

    public void setDiseaseDesc(String diseaseDesc) {
        this.diseaseDesc = diseaseDesc == null ? null : diseaseDesc.trim();
    }

    public Integer getChildHeight() {
		return childHeight;
	}

	public void setChildHeight(Integer childHeight) {
		this.childHeight = childHeight;
	}

	public Integer getChildWeight() {
		return childWeight;
	}

	public void setChildWeight(Integer childWeight) {
		this.childWeight = childWeight;
	}

	public String getEmerName() {
        return emerName;
    }

    public void setEmerName(String emerName) {
        this.emerName = emerName == null ? null : emerName.trim();
    }

    public String getEmerMobile() {
        return emerMobile;
    }

    public void setEmerMobile(String emerMobile) {
        this.emerMobile = emerMobile == null ? null : emerMobile.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getSignResource() {
        return signResource;
    }

    public void setSignResource(String signResource) {
        this.signResource = signResource == null ? null : signResource.trim();
    }

    public String getSignRelation() {
        return signRelation;
    }

    public void setSignRelation(String signRelation) {
        this.signRelation = signRelation == null ? null : signRelation.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getFamilyNo() {
        return familyNo;
    }

    public void setFamilyNo(String familyNo) {
        this.familyNo = familyNo == null ? null : familyNo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsEffect() {
        return isEffect;
    }

    public void setIsEffect(Integer isEffect) {
        this.isEffect = isEffect;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getIsPhoneConfirm() {
		return isPhoneConfirm;
	}

	public void setIsPhoneConfirm(Integer isPhoneConfirm) {
		this.isPhoneConfirm = isPhoneConfirm;
	}

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
}