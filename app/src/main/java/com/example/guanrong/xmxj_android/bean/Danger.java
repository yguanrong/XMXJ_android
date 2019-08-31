package com.example.guanrong.xmxj_android.bean;

import java.sql.Date;

public class Danger {

    private Integer dangerId;
    private String dangerName;
    private Integer dangerAreaId;
    private Integer companyId;
    private Integer dangerType;
    private String dangerDec;
    private String zhenggaiDec;
    private Integer dangerState;
    private Integer itemId;
    private Integer dangerLevel;
    private Date createTime;
    private Date limitTime;
    private Date updateTime;
    private String zhenggaiLog;
    private String createImg;
    private String updateImg;
    private Integer zhenggaiPId;
    private Integer createPId;
    private Integer reviewPId;
    private String reviewLog;
    private Date reviewTime;

    public Danger() {
    }

    public Danger(String dangerName, Integer dangerAreaId, Integer companyId,
                  Integer dangerType, String dangerDec, String zhenggaiDec,
                  Integer dangerState, Integer itemId, Integer dangerLevel,
                  Date limitTime, Integer zhenggaiPId, Integer createPId) {
        this.dangerName = dangerName;
        this.dangerAreaId = dangerAreaId;
        this.companyId = companyId;
        this.dangerType = dangerType;
        this.dangerDec = dangerDec;
        this.zhenggaiDec = zhenggaiDec;
        this.dangerState = dangerState;
        this.itemId = itemId;
        this.dangerLevel = dangerLevel;
        this.limitTime = limitTime;
        this.zhenggaiPId = zhenggaiPId;
        this.createPId = createPId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getZhenggaiLog() {
        return zhenggaiLog;
    }

    public void setZhenggaiLog(String zhenggaiLog) {
        this.zhenggaiLog = zhenggaiLog;
    }

    public Integer getReviewPId() {
        return reviewPId;
    }

    public void setReviewPId(Integer reviewPId) {
        this.reviewPId = reviewPId;
    }

    public String getReviewLog() {
        return reviewLog;
    }

    public void setReviewLog(String reviewLog) {
        this.reviewLog = reviewLog;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Integer getDangerId() {
        return dangerId;
    }

    public void setDangerId(Integer dnagerId) {
        this.dangerId = dnagerId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateImg() {
        return createImg;
    }

    public void setCreateImg(String createImg) {
        this.createImg = createImg;
    }

    public String getUpdateImg() {
        return updateImg;
    }

    public void setUpdateImg(String updateImg) {
        this.updateImg = updateImg;
    }

    public String getDangerName() {
        return dangerName;
    }

    public void setDangerName(String dangerName) {
        this.dangerName = dangerName;
    }

    public Integer getDangerAreaId() {
        return dangerAreaId;
    }

    public void setDangerAreaId(Integer dangerAreaId) {
        this.dangerAreaId = dangerAreaId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getDangerType() {
        return dangerType;
    }

    public void setDangerType(Integer dangerType) {
        this.dangerType = dangerType;
    }

    public String getDangerDec() {
        return dangerDec;
    }

    public void setDangerDec(String dangerDec) {
        this.dangerDec = dangerDec;
    }

    public String getZhenggaiDec() {
        return zhenggaiDec;
    }

    public void setZhenggaiDec(String zhenggaiDec) {
        this.zhenggaiDec = zhenggaiDec;
    }

    public Integer getDangerState() {
        return dangerState;
    }

    public void setDangerState(Integer dangerState) {
        this.dangerState = dangerState;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(Integer dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public Date getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Date limitTime) {
        this.limitTime = limitTime;
    }

    public Integer getZhenggaiPId() {
        return zhenggaiPId;
    }

    public void setZhenggaiPId(Integer zhenggaiPId) {
        this.zhenggaiPId = zhenggaiPId;
    }

    public Integer getCreatePId() {
        return createPId;
    }

    public void setCreatePId(Integer createPId) {
        this.createPId = createPId;
    }
}
