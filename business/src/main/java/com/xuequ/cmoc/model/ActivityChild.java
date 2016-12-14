package com.xuequ.cmoc.model;

import java.io.Serializable;
import java.util.Date;

public class ActivityChild implements Serializable {
    private Integer id;

    private Integer childId;

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
    
    private String marineName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
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

	public String getMarineName() {
		return marineName;
	}

	public void setMarineName(String marineName) {
		this.marineName = marineName;
	}
    
}