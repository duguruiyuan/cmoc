package com.xuequ.cmoc.model;

import java.io.Serializable;
import java.util.Date;

public class ActivityResource implements Serializable {
    private Integer id;

    private String url;

    private Integer marineId;

    private String marineName;

    private String hmMobile;

    private String hmName;

    private String resourceType;

    private String isDelete;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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

    public String getHmMobile() {
        return hmMobile;
    }

    public void setHmMobile(String hmMobile) {
        this.hmMobile = hmMobile == null ? null : hmMobile.trim();
    }

    public String getHmName() {
        return hmName;
    }

    public void setHmName(String hmName) {
        this.hmName = hmName == null ? null : hmName.trim();
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}