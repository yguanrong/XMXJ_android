package com.example.guanrong.xmxj_android.bean;

import java.sql.Timestamp;

/**
 * Created by GuanRong on 2019/4/7.
 */

public class Zhenggai {

    private Integer dangerId;
    private String zhenggaiLog;
    private String zhenggaiDec;
    private Timestamp zhenggaiTime;
    private Integer dangerState;

    public Integer getDangerId() {
        return dangerId;
    }

    public void setDangerId(Integer dangerId) {
        this.dangerId = dangerId;
    }

    public String getZhenggaiLog() {
        return zhenggaiLog;
    }

    public void setZhenggaiLog(String zhenggaiLog) {
        this.zhenggaiLog = zhenggaiLog;
    }

    public String getZhenggaiDec() {
        return zhenggaiDec;
    }

    public void setZhenggaiDec(String zhenggaiDec) {
        this.zhenggaiDec = zhenggaiDec;
    }

    public Timestamp getZhenggaiTime() {
        return zhenggaiTime;
    }

    public void setZhenggaiTime(Timestamp zhenggaiTime) {
        this.zhenggaiTime = zhenggaiTime;
    }

    public Integer getDangerState() {
        return dangerState;
    }

    public void setDangerState(Integer dangerState) {
        this.dangerState = dangerState;
    }
}
