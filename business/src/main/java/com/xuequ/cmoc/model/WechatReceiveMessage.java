package com.xuequ.cmoc.model;

import java.io.Serializable;
import java.util.Date;

public class WechatReceiveMessage implements Serializable {
    private String msgid;

    private Integer hmsignid;

    private String tousername;

    private String fromusername;

    private String msgtype;

    private String mediaid;

    private String content;

    private String title;

    private String description;

    private String format;

    private String recognition;

    private String picurl;

    private String url;

    private String musicurl;

    private String hqmusicurl;

    private String thumbmediaid;

    private Float locationx;

    private Float locationy;

    private Integer scale;

    private String label;

    private String isdelete;

    private Integer createtime;

    private Date syscreatetime;

    private String updater;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid == null ? null : msgid.trim();
    }

    public Integer getHmsignid() {
        return hmsignid;
    }

    public void setHmsignid(Integer hmsignid) {
        this.hmsignid = hmsignid;
    }

    public String getTousername() {
        return tousername;
    }

    public void setTousername(String tousername) {
        this.tousername = tousername == null ? null : tousername.trim();
    }

    public String getFromusername() {
        return fromusername;
    }

    public void setFromusername(String fromusername) {
        this.fromusername = fromusername == null ? null : fromusername.trim();
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype == null ? null : msgtype.trim();
    }

    public String getMediaid() {
        return mediaid;
    }

    public void setMediaid(String mediaid) {
        this.mediaid = mediaid == null ? null : mediaid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format == null ? null : format.trim();
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition == null ? null : recognition.trim();
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getMusicurl() {
        return musicurl;
    }

    public void setMusicurl(String musicurl) {
        this.musicurl = musicurl == null ? null : musicurl.trim();
    }

    public String getHqmusicurl() {
        return hqmusicurl;
    }

    public void setHqmusicurl(String hqmusicurl) {
        this.hqmusicurl = hqmusicurl == null ? null : hqmusicurl.trim();
    }

    public String getThumbmediaid() {
        return thumbmediaid;
    }

    public void setThumbmediaid(String thumbmediaid) {
        this.thumbmediaid = thumbmediaid == null ? null : thumbmediaid.trim();
    }

    public Float getLocationx() {
        return locationx;
    }

    public void setLocationx(Float locationx) {
        this.locationx = locationx;
    }

    public Float getLocationy() {
        return locationy;
    }

    public void setLocationy(Float locationy) {
        this.locationy = locationy;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete == null ? null : isdelete.trim();
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }

    public Date getSyscreatetime() {
        return syscreatetime;
    }

    public void setSyscreatetime(Date syscreatetime) {
        this.syscreatetime = syscreatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}