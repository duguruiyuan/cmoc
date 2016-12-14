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

    private String emerName;

    private String emerMobile;

    private String city;

    private String productId;

    private String parentId;

    private String familyNo;

    private Date createTime;

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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
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
}