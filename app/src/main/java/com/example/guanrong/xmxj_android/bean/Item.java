package com.example.guanrong.xmxj_android.bean;

/**
 * Created by GuanRong on 2019/3/25.
 */

public class Item {

    private Integer id;
    private String itemName;
    private Integer companyId;
    private Integer manageId;
    private Integer saferOfficeId;
    private Integer responserId;
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getManageId() {
        return manageId;
    }

    public void setManageId(Integer manageId) {
        this.manageId = manageId;
    }

    public Integer getSaferOfficeId() {
        return saferOfficeId;
    }

    public void setSaferOfficeId(Integer saferOfficeId) {
        this.saferOfficeId = saferOfficeId;
    }

    public Integer getResponserId() {
        return responserId;
    }

    public void setResponserId(Integer responserId) {
        this.responserId = responserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
