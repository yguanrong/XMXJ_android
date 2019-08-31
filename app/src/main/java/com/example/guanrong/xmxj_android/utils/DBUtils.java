package com.example.guanrong.xmxj_android.utils;

import android.support.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.guanrong.xmxj_android.activity.Report;
import com.example.guanrong.xmxj_android.bean.Company;
import com.example.guanrong.xmxj_android.bean.CountForCompany;
import com.example.guanrong.xmxj_android.bean.Danger;
import com.example.guanrong.xmxj_android.bean.Data;
import com.example.guanrong.xmxj_android.bean.Item;
import com.example.guanrong.xmxj_android.bean.ListForCompany;
import com.example.guanrong.xmxj_android.bean.ResultInfo;
import com.example.guanrong.xmxj_android.bean.User;
import com.example.guanrong.xmxj_android.bean.Zhenggai;
import com.example.guanrong.xmxj_android.service.httpUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GuanRong on 2019/3/19.
 */

public class  DBUtils {

    public static User user;
    public static Company company;
    public static Company companyPart;
    public static Item item;
    public static boolean isView = false; //默认不是
    public static boolean isEdit = false; //默认不是
    public static boolean isReview = false; //默认不是
    public static boolean isReform = false; //默认不是
    public static int itemOrCompany = 1;//1表示项目，2 表示公司
    public static String dangerJson;
    public static List<Data>  dangerArea;
    public static List<Data> dangerTypes;
    public static List<Data> companys;
    public static List<Data> companys1;


//    public static String api= "http://192.168.43.39:8089";
    public static String api= "http://119.23.73.235:8089";


    public static String imgUrl = "http://119.23.73.235:88/upload/phone_upload/";
//    public static String imgUrl = "http://192.168.43.39:88/upload/phone_upload/";


    public static User login(String userName, String password){
        user = new User();
        item = new Item();
        Map<String, String> map = new HashMap<>();
        map.put("userName", userName);
        map.put("password", password);

        String result = httpUtil.PostMethod(api+"/login",map,"UTF-8");
        if (result == null || result.equals("")){
            user.setIdentify(-3);
        }else {
            JSONObject jsonObject = JSON.parseObject(result);
            user = jsonObject.toJavaObject(User.class);

            dangerArea = queryDangerArea();
            dangerTypes = queryDangerType();
            companys = queryCompanyPart(2);
            companys1 = queryCompanyPart(null);
        }
        return user;
    }

    public static Company queryCompanyByUser(Integer userId){

        company = new Company();
        String result = httpUtil.GetMethod(api+"/queryCompanyByUser?userId="+userId);
        JSONObject jsonObject = JSON.parseObject(result);
        company = jsonObject.toJavaObject(Company.class);
        return company;
    }

    //查询分包公司
    public static List<Company> queryCompany(Integer companyType){
        companyPart = new Company();
        List<Company> list = new ArrayList<>();
        String result = httpUtil.GetMethod(api+"/queryCompany?companyType="+companyType);
        list = JSONArray.parseArray(result,Company.class);
        return list;
    }

    public static List<Item> queryItems(@Nullable Integer userId){

        List<Item> list = new ArrayList<>();
        String url = api+"/queryItems?userId="+userId;
        if (userId == null){
            url = api+"/queryItems";
        }
        String result = httpUtil.GetMethod(url);
        list = JSONArray.parseArray(result,Item.class);
        return list;
    }

    public static List<Data> queryItem(int userId) {
        List<Item> list = new ArrayList<>();
        List<Data> dataList = new ArrayList<>();
        String url = api+"/queryItems?userId="+userId;

        String result = httpUtil.GetMethod(url);
        list = JSONArray.parseArray(result,Item.class);
        for (Item item: list) {
            Data data = new Data();
            data.setId(item.getId());
            data.setName(item.getItemName());
            dataList.add(data);
        }
        return dataList;
    }

    public static int queryCountDanger(
            @Nullable Integer itemId,
            @Nullable Integer companyId,
            @Nullable Integer createPId,
            @Nullable Integer zhenggaiPId,
            @Nullable Integer dangerState,
            @Nullable Integer level){

        int count = 0;
        String url = "";
        if (itemId != null){
            url = api+"/queryCountDanger?itemId="+ itemId;
        }
        if (companyId != null){
            url = api+"/queryCountDanger?companyId="+ companyId;
        }
        if (createPId != null){
            url = api+"/queryCountDanger?createPId="+ createPId;
        }
        if (zhenggaiPId != null){
            url = api+"/queryCountDanger?zhenggaiPId="+ zhenggaiPId;
        }
        if (level != null){
            url = url+"&level="+level;
        }
        if (dangerState != null ){
            url = url+"&dangerState="+dangerState;
        }
        String result = httpUtil.GetMethod(url);
        if (result.equals(""))
        {
            count=0;
        }
        else {
            count = Integer.parseInt(result);
        }

        return count;
    }

    //风险类型
    public static List<Data> queryDangerType(){

        List<Data> list = new ArrayList<>();
        String result = httpUtil.GetMethod(
                api+"/queryDangerType");
        list = JSONArray.parseArray(result,Data.class);
        return list;
    }
    public static List<Data> queryDangerArea(){

        List<Data> list = new ArrayList<>();
        String result = httpUtil.GetMethod(
                api+"/queryDangerArea");
        list = JSONArray.parseArray(result,Data.class);
        return list;
    }
    public static List<Data> queryCompanyPart(@Nullable Integer type){

        List<Data> list = new ArrayList<>();
        String result = httpUtil.GetMethod(
                api+"/queryCompanyPart");
        list = JSONArray.parseArray(result,Data.class);
        if (type == null){
            Data data = new Data();
            data.setId(4);
            data.setName("总公司");
            list.add(data);
        }
        return list;
    }

    public static List<Data> queryUserByCompany(int companyId,int identify){

        List<Data> list = new ArrayList<>();
        String result = httpUtil.GetMethod(
                api+"/queryUser?identify="+identify+"&companyId="+companyId);
        list = JSONArray.parseArray(result,Data.class);
        return list;
    }

    public static Integer newDanger(Danger newDanger){

        int id = 0;
        Map<String, String> map = new HashMap<>();
        map.put("dangerName",newDanger.getDangerName());
        map.put("dangerAreaId",newDanger.getDangerAreaId().toString());
        map.put("companyId",newDanger.getCompanyId().toString());
        map.put("dangerType",newDanger.getDangerType().toString());
        map.put("dangerDec",newDanger.getDangerDec());
        map.put("zhenggaiDec",newDanger.getZhenggaiDec());
        map.put("dangerState",newDanger.getDangerState().toString());
        map.put("itemId",newDanger.getItemId().toString());
        map.put("dangerLevel",newDanger.getDangerLevel().toString());
        map.put("limitTime",newDanger.getLimitTime().toString());
        map.put("zhenggaiPId",newDanger.getZhenggaiPId().toString());
        map.put("createPId",newDanger.getCreatePId().toString());

        String result = httpUtil.PostMethod(api+"/newDanger",map,"UTF-8");
        System.out.println("newDangerId = " + result);
        id = Integer.parseInt(result);
        return id;
    }

    public static int reform(Danger danger) {

        int id = 0;
        Map<String, String> map = new HashMap<>();
        Date updateTime = new Date(System.currentTimeMillis());
        map.put("dangerId",danger.getDangerId().toString());
        map.put("zhenggaiLog",danger.getZhenggaiLog());
        map.put("dangerState",danger.getDangerState().toString());
        map.put("updateTime",updateTime.toString());
        String result = httpUtil.PostMethod(api+"/reform",map,"UTF-8");
        id = Integer.parseInt(result);
        return id;
    }

    public static int review(Danger danger) {
        int id = 0;
        /**
         * dangerId: '',
         reviewTime: '',
         reviewLog:'',
         reviewPId:''
         dangerState:3,
         * */
        Map<String, String> map = new HashMap<>();
        Date reviewTime = new Date(System.currentTimeMillis());

        map.put("dangerId",danger.getDangerId().toString());
        map.put("reviewLog",danger.getReviewLog());
        map.put("dangerState",danger.getDangerState().toString());
        map.put("reviewPId",danger.getReviewPId().toString());
        map.put("reviewTime",reviewTime.toString());
        String result = httpUtil.PostMethod(api+"/review",map,"UTF-8");
        id = Integer.parseInt(result);
        return id;
    }

    public static String queryDangers(String url){
        return httpUtil.GetMethod(api + url);
    }

    public static int updateDanger(Danger danger) {
        int id = 0;
        Map<String, String> map = new HashMap<>();
        map.put("dangerId",danger.getDangerId().toString());
        map.put("dangerName",danger.getDangerName());
        map.put("dangerAreaId",danger.getDangerAreaId().toString());
        map.put("companyId",danger.getCompanyId().toString());
        map.put("dangerType",danger.getDangerType().toString());
        map.put("dangerDec",danger.getDangerDec());
        map.put("zhenggaiDec",danger.getZhenggaiDec());
        map.put("dangerState",danger.getDangerState().toString());
        map.put("itemId",danger.getItemId().toString());
        map.put("dangerLevel",danger.getDangerLevel().toString());
        map.put("limitTime",danger.getLimitTime().toString());
        map.put("zhenggaiPId",danger.getCreatePId().toString());
        map.put("createPId",danger.getCreatePId().toString());

        String result = httpUtil.PostMethod(api+"/updateDanger",map,"UTF-8");
        id = Integer.parseInt(result);
        return id;
    }

    public static String uploadImg(String base64, Integer dangerId, Integer flag){

        Map<String,String> map = new HashMap<>();
        map.put("base64",base64);
        map.put("dangerId",dangerId+"");
        map.put("flag",flag+"");

        return  httpUtil.PostMethod(api+"/uploadImg",map,"UTF-8");
    }

    public static String queryDangerByWeek(@Nullable Integer itemId, @Nullable Integer companyId){

        String url = api + "/queryDangerByWeek?itemId="+itemId;
        if (itemId == null){
            url = api + "/queryDangerByWeek?companyId="+companyId;
        }
        return httpUtil.GetMethod(url);
    }

    public static CountForCompany queryCountForCompany(@Nullable Integer itemId){
        String url = api + "/queryCountForCompany?itemId="+itemId;
        if (itemId == null)
        {
            url = api + "/queryCountForCompany";
        }
        String result =  httpUtil.GetMethod(url);
        List<ListForCompany>  listForCompanies = JSONArray.parseArray(result,ListForCompany.class);
        List<String> companyName = new ArrayList<>();

        for (Data data:
             companys) {
            companyName.add(data.getName());
        }
        int[] count = new int[companyName.size()];
        int[] sum = new int[companyName.size()];
        for ( ListForCompany listForCompany:
                listForCompanies) {
            for (int i = 0; i< companyName.size(); i++) {

                if ( listForCompany.getCompanyName().equals(companyName.get(i)) ){
                    sum[i]=sum[i]+listForCompany.getCount();
                    if (listForCompany.getDangerState()==0)
                    {
                        count[i] = count[i]+listForCompany.getCount();
                    }

                }

            }
        }
        CountForCompany countForCompany = new CountForCompany();
        countForCompany.setCompany(companyName);
        countForCompany.setCount(count);
        countForCompany.setSum(sum);
        return countForCompany;
    }


    public static String queryDangerForType(@Nullable Integer itemId, @Nullable Integer companyId){

        String url = api + "/queryDangerForType?itemId="+itemId;
        if (itemId == null){
            url = api + "/queryDangerForType?companyId="+companyId;
        }
        return httpUtil.GetMethod(url);
    }

    public static String updateUserImg(Integer userId, String imgData){

        Map<String,String> map = new HashMap<>();
        map.put("userId",userId+"");
        map.put("imgData",imgData);
        String url = api + "/updateUserImg";
        return httpUtil.PostMethod(url,map,"UTF-8");

    }

    public static int submitReport(Report report){

        Map<String,String> map = new HashMap<>();
        map.put("userId",report.getUserId()+"");
        map.put("report",report.getReport());
        map.put("state",report.getState()+"");
        map.put("createDate",report.getCreateDate()+"");

        String url = api + "/updateReport";

        String re = httpUtil.PostMethod(url,map,"UTF-8");
        return Integer.parseInt(re);
    }


}
