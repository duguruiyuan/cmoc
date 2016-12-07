package com.xuequ.cmoc.model;

import java.io.Serializable;
import java.util.Date;

public class ActivityFamily implements Serializable {
    private Integer id;

    private String fatherName;

    private String fatherMobile;

    private Integer fatherAge;

    private String motherName;

    private String motherMobile;

    private Integer motherAge;

    private String childIdcard;

    private String parentMobile;

    private String childName;

    private String childMobile;

    private Integer childAge;

    private String childImg;

    private String childTitle;

    private String childComment;

    private Integer activityId;

    private Integer marineId;

    private Integer createrUserId;

    private String creater;

    private Date createTime;

    private Integer updaterUserId;

    private String updater;

    private Date updateTime;

    private String isDelete;
    
    private String activityName;
    
    private String marineName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName == null ? null : fatherName.trim();
    }

    public String getFatherMobile() {
        return fatherMobile;
    }

    public void setFatherMobile(String fatherMobile) {
        this.fatherMobile = fatherMobile == null ? null : fatherMobile.trim();
    }

    public Integer getFatherAge() {
        return fatherAge;
    }

    public void setFatherAge(Integer fatherAge) {
        this.fatherAge = fatherAge;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName == null ? null : motherName.trim();
    }

    public String getMotherMobile() {
        return motherMobile;
    }

    public void setMotherMobile(String motherMobile) {
        this.motherMobile = motherMobile == null ? null : motherMobile.trim();
    }

    public Integer getMotherAge() {
        return motherAge;
    }

    public void setMotherAge(Integer motherAge) {
        this.motherAge = motherAge;
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

    public String getChildImg() {
        return childImg;
    }

    public void setChildImg(String childImg) {
        this.childImg = childImg == null ? null : childImg.trim();
    }

    public String getChildTitle() {
        return childTitle;
    }

    public void setChildTitle(String childTitle) {
        this.childTitle = childTitle == null ? null : childTitle.trim();
    }

    public String getChildComment() {
        return childComment;
    }

    public void setChildComment(String childComment) {
        this.childComment = childComment == null ? null : childComment.trim();
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getMarineId() {
        return marineId;
    }

    public void setMarineId(Integer marineId) {
        this.marineId = marineId;
    }

    public Integer getCreaterUserId() {
        return createrUserId;
    }

    public void setCreaterUserId(Integer createrUserId) {
        this.createrUserId = createrUserId;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdaterUserId() {
        return updaterUserId;
    }

    public void setUpdaterUserId(Integer updaterUserId) {
        this.updaterUserId = updaterUserId;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getMarineName() {
		return marineName;
	}

	public void setMarineName(String marineName) {
		this.marineName = marineName;
	}
    
}