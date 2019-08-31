package com.example.guanrong.xmxj_android.bean;

/**
 * Created by GuanRong on 2019/4/4.
 */

public class Data {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Data() {
    }

    public Data(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
