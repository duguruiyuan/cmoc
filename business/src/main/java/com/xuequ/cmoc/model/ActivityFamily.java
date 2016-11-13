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

    private String otherName;

    private String otherMobile;

    private Integer otherAge;

    private String childName;

    private String childMobile;

    private Integer childAge;

    private String childImg;

    private Long activityId;

    private String activityName;

    private Integer marineId;

    private String marineName;

    private String hmName;

    private String hmMobile;

    private String teacherName;

    private String teacherMobile;

    private String create;

    private Date createTime;

    private String updater;

    private Date updateTime;

    private String isDelete;

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

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName == null ? null : otherName.trim();
    }

    public String getOtherMobile() {
        return otherMobile;
    }

    public void setOtherMobile(String otherMobile) {
        this.otherMobile = otherMobile == null ? null : otherMobile.trim();
    }

    public Integer getOtherAge() {
        return otherAge;
    }

    public void setOtherAge(Integer otherAge) {
        this.otherAge = otherAge;
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

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public Integer getMarineId() {
        return marineId;
    }

    public void setMarineId(Integer marineId) {
        this.marineId = marineId;
    }

    public String getMarineName() {
        return marineName;
    }

    public void setMarineName(String marineName) {
        this.marineName = marineName == null ? null : marineName.trim();
    }

    public String getHmName() {
        return hmName;
    }

    public void setHmName(String hmName) {
        this.hmName = hmName == null ? null : hmName.trim();
    }

    public String getHmMobile() {
        return hmMobile;
    }

    public void setHmMobile(String hmMobile) {
        this.hmMobile = hmMobile == null ? null : hmMobile.trim();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getTeacherMobile() {
        return teacherMobile;
    }

    public void setTeacherMobile(String teacherMobile) {
        this.teacherMobile = teacherMobile == null ? null : teacherMobile.trim();
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create == null ? null : create.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}