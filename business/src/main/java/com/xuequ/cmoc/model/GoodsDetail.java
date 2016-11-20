package com.xuequ.cmoc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GoodsDetail implements Serializable {
    private Integer id;

    private String goodsName;

    private Integer stock;

    private String goodsNo;

    private String color;

    private String mainImg;

    private BigDecimal price;

    private BigDecimal discountPrice;

    private Integer points;

    private String productDescUrl;

    private Integer createrUserId;

    private String creater;

    private Date createTime;

    private Integer updaterUserId;

    private String updater;

    private Date updaterTime;

    private String isDelete;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo == null ? null : goodsNo.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg == null ? null : mainImg.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getProductDescUrl() {
        return productDescUrl;
    }

    public void setProductDescUrl(String productDescUrl) {
        this.productDescUrl = productDescUrl == null ? null : productDescUrl.trim();
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

    public Date getUpdaterTime() {
        return updaterTime;
    }

    public void setUpdaterTime(Date updaterTime) {
        this.updaterTime = updaterTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }
}