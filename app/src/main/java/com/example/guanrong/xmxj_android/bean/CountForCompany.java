package com.example.guanrong.xmxj_android.bean;

import java.util.List;

/**
 * Created by GuanRong on 2019/4/12.
 */

public class CountForCompany {

    private List<String> company;
    private int[] count;
    private int[] sum;

    public List<String> getCompany() {
        return company;
    }

    public void setCompany(List<String> company) {
        this.company = company;
    }

    public int[] getCount() {
        return count;
    }

    public void setCount(int[] count) {
        this.count = count;
    }

    public int[] getSum() {
        return sum;
    }

    public void setSum(int[] sum) {
        this.sum = sum;
    }
}
