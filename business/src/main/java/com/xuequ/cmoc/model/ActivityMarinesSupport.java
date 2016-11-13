package com.xuequ.cmoc.model;

import java.io.Serializable;
import java.util.Date;

public class ActivityMarinesSupport implements Serializable {
    private Integer id;

    private String openid;

    private String nickname;

    private String imgUrl;

    private Integer marginId;

    private String marginName;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Integer getMarginId() {
        return marginId;
    }

    public void setMarginId(Integer marginId) {
        this.marginId = marginId;
    }

    public String getMarginName() {
        return marginName;
    }

    public void setMarginName(String marginName) {
        this.marginName = marginName == null ? null : marginName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}