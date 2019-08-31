package com.example.guanrong.xmxj_android.bean;

/**
 * Created by GuanRong on 2019/3/19.
 */

public class Company {

    private Integer id;
    private String company_name;
    private Integer faren;
    private Integer type;

    public Integer getFaren() {
        return faren;
    }

    public void setFaren(Integer faren) {
        this.faren = faren;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Company() {
    }

    public Company(Integer id, String company_name) {
        this.id = id;
        this.company_name = company_name;
    }
}
