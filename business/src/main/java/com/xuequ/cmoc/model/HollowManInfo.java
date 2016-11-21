package com.xuequ.cmoc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HollowManInfo implements Serializable {
    private Integer id;

    private String hmName;

    private String hmMobile;

    private String hmIdType;

    private String hmIdCard;

    private String hmAddr;

    private String hmSchoole;

    private String hmKimName;

    private String hmKimMobile;

    private Integer isActive;

    private String active;

    private String openid;

    private String level;

    private BigDecimal score;

    private String creater;

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

    public String getHmIdType() {
        return hmIdType;
    }

    public void setHmIdType(String hmIdType) {
        this.hmIdType = hmIdType == null ? null : hmIdType.trim();
    }

    public String getHmIdCard() {
        return hmIdCard;
    }

    public void setHmIdCard(String hmIdCard) {
        this.hmIdCard = hmIdCard == null ? null : hmIdCard.trim();
    }

    public String getHmAddr() {
        return hmAddr;
    }

    public void setHmAddr(String hmAddr) {
        this.hmAddr = hmAddr == null ? null : hmAddr.trim();
    }

    public String getHmSchoole() {
        return hmSchoole;
    }

    public void setHmSchoole(String hmSchoole) {
        this.hmSchoole = hmSchoole == null ? null : hmSchoole.trim();
    }

    public String getHmKimName() {
        return hmKimName;
    }

    public void setHmKimName(String hmKimName) {
        this.hmKimName = hmKimName == null ? null : hmKimName.trim();
    }

    public String getHmKimMobile() {
        return hmKimMobile;
    }

    public void setHmKimMobile(String hmKimMobile) {
        this.hmKimMobile = hmKimMobile == null ? null : hmKimMobile.trim();
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active == null ? null : active.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
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