package com.xuequ.cmoc.model;

import java.io.Serializable;
import java.util.Date;

public class SysResource implements Serializable {
    private Integer idResource;

    private String resourceName;

    private String resourceUrl;

    private String resourceType;

    private String resourceCode;

    private Integer parentResourceId;

    private Integer weight;

    private String creator;

    private String lastUpdator;

    private Date createTime;

    private Date lastUpdateTime;

    private String isDelete;

    private String isQuickMenu;

    private String isSystemConfMenu;

    private static final long serialVersionUID = 1L;

    public Integer getIdResource() {
        return idResource;
    }

    public void setIdResource(Integer idResource) {
        this.idResource = idResource;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode == null ? null : resourceCode.trim();
    }

    public Integer getParentResourceId() {
        return parentResourceId;
    }

    public void setParentResourceId(Integer parentResourceId) {
        this.parentResourceId = parentResourceId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getLastUpdator() {
        return lastUpdator;
    }

    public void setLastUpdator(String lastUpdator) {
        this.lastUpdator = lastUpdator == null ? null : lastUpdator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public String getIsQuickMenu() {
        return isQuickMenu;
    }

    public void setIsQuickMenu(String isQuickMenu) {
        this.isQuickMenu = isQuickMenu == null ? null : isQuickMenu.trim();
    }

    public String getIsSystemConfMenu() {
        return isSystemConfMenu;
    }

    public void setIsSystemConfMenu(String isSystemConfMenu) {
        this.isSystemConfMenu = isSystemConfMenu == null ? null : isSystemConfMenu.trim();
    }
}