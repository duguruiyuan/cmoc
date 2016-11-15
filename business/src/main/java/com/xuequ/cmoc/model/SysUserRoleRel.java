package com.xuequ.cmoc.model;

import java.io.Serializable;
import java.util.Date;

public class SysUserRoleRel implements Serializable {
    private Integer idUserRoleRel;

    private Integer idUser;

    private Integer idRole;

    private String creator;

    private String lastUpdator;

    private Date createTime;

    private Date lastUpdateTime;

    private String isDelete;

    private static final long serialVersionUID = 1L;

    public Integer getIdUserRoleRel() {
        return idUserRoleRel;
    }

    public void setIdUserRoleRel(Integer idUserRoleRel) {
        this.idUserRoleRel = idUserRoleRel;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
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
}