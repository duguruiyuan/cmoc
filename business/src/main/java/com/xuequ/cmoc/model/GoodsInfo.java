package com.xuequ.cmoc.model;

import java.io.Serializable;
import java.util.Date;

public class GoodsInfo implements Serializable {
    private Integer id;

    private String goodsNo;

    private String creater;

    private Date createTime;

    private String updater;

    private Date updaterTime;

    private byte[] goodsDesc;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo == null ? null : goodsNo.trim();
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

    public byte[] getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(byte[] goodsDesc) {
        this.goodsDesc = goodsDesc;
    }
}