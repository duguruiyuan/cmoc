package com.xuequ.cmoc.model;

import java.io.Serializable;
import java.util.Date;

public class ActivityInfo implements Serializable {
    private Integer id;

    private String activityName;

    private String startDate;

    private String endDate;

    private String activityAddr;

    private String activityNum;

    private String activityDesc;

    private String activityType;

    private String creater;

    private Date createTime;

    private String updater;

    private Date updaterTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public String getActivityAddr() {
        return activityAddr;
    }

    public void setActivityAddr(String activityAddr) {
        this.activityAddr = activityAddr == null ? null : activityAddr.trim();
    }

    public String getActivityNum() {
        return activityNum;
    }

    public void setActivityNum(String activityNum) {
        this.activityNum = activityNum == null ? null : activityNum.trim();
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc == null ? null : activityDesc.trim();
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType == null ? null : activityType.trim();
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

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Date getUpdaterTime() {
        return updaterTime;
    }

    public void setUpdaterTime(Date updaterTime) {
        this.updaterTime = updaterTime;
    }
}