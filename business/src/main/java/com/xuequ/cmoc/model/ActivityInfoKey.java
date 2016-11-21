package com.xuequ.cmoc.model;

import java.io.Serializable;

public class ActivityInfoKey implements Serializable {
    private Integer id;

    private String activityType;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType == null ? null : activityType.trim();
    }
}