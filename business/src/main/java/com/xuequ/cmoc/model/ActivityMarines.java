package com.xuequ.cmoc.model;

import java.io.Serializable;
import java.util.Date;

public class ActivityMarines implements Serializable {
    private Integer id;

    private String marineName;

    private String marineImg;

    private String marineSlogan;

    private String marinePrize;

    private String lineName;

    private Integer lineId;

    private Integer activityId;
    
    private Integer status;
    
    private String comment;

    private Integer votes;

    private Integer readnum;

    private Integer score;
    
    private String qrcodeUrl;

    private Integer createrUserId;

    private String creater;

    private Date createTime;

    private Integer updaterUserId;

    private String updater;

    private Date updateTime;

    private String isDelete;
    
    private String activityName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarineName() {
        return marineName;
    }

    public void setMarineName(String marineName) {
        this.marineName = marineName == null ? null : marineName.trim();
    }

    public String getMarineImg() {
        return marineImg;
    }

    public void setMarineImg(String marineImg) {
        this.marineImg = marineImg == null ? null : marineImg.trim();
    }

    public String getMarineSlogan() {
        return marineSlogan;
    }

    public void setMarineSlogan(String marineSlogan) {
        this.marineSlogan = marineSlogan == null ? null : marineSlogan.trim();
    }

    public String getMarinePrize() {
        return marinePrize;
    }

    public void setMarinePrize(String marinePrize) {
        this.marinePrize = marinePrize == null ? null : marinePrize.trim();
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName == null ? null : lineName.trim();
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
    
    public Integer getReadnum() {
		return readnum;
	}

	public void setReadnum(Integer readnum) {
		this.readnum = readnum;
	}

	public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getQrcodeUrl() {
		return qrcodeUrl;
	}

	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
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
    
    
}