package com.xuequ.cmoc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ActivityHmSign implements Serializable {
    private Integer id;

    private Integer activityId;

    private Integer lineId;

    private Integer marineId;

    private Integer hmId;

    private String signDate;

    private Integer isEffect;

    private String effectDate;

    private BigDecimal score;

    private String judge;

    private Integer createrUserId;

    private String creater;

    private Date createTime;

    private Integer updaterUserId;

    private String updater;

    private Date updateTime;

    private String isDelete;
    
    private String activityName;
    
    private String marineName;
    
    private String hmName;
    
    private String hmMobile;
    
    private String reason;
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getMarineId() {
        return marineId;
    }

    public void setMarineId(Integer marineId) {
        this.marineId = marineId;
    }

    public Integer getHmId() {
        return hmId;
    }

    public void setHmId(Integer hmId) {
        this.hmId = hmId;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate == null ? null : signDate.trim();
    }

    public Integer getIsEffect() {
        return isEffect;
    }

    public void setIsEffect(Integer isEffect) {
        this.isEffect = isEffect;
    }

    public String getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(String effectDate) {
        this.effectDate = effectDate == null ? null : effectDate.trim();
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge == null ? null : judge.trim();
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

	public String getHmName() {
		return hmName;
	}

	public void setHmName(String hmName) {
		this.hmName = hmName;
	}

	public String getHmMobile() {
		return hmMobile;
	}

	public void setHmMobile(String hmMobile) {
		this.hmMobile = hmMobile;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
    
}