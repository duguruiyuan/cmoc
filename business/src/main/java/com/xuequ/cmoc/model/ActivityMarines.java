package com.xuequ.cmoc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ActivityMarines implements Serializable {
    private Integer id;

    private String marineName;

    private String marineImg;

    private String lines;

    private Integer activityId;

    private String activityName;

    private Integer votes;

    private Integer reads;

    private BigDecimal score;

    private Date createTime;

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

    public String getLines() {
        return lines;
    }

    public void setLines(String lines) {
        this.lines = lines == null ? null : lines.trim();
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Integer getReads() {
        return reads;
    }

    public void setReads(Integer reads) {
        this.reads = reads;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}