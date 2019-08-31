package com.example.guanrong.xmxj_android.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.guanrong.xmxj_android.R;
import com.example.guanrong.xmxj_android.bean.Company;
import com.example.guanrong.xmxj_android.bean.Item;
import com.example.guanrong.xmxj_android.utils.DBUtils;

import java.util.List;

/**
 * Created by GuanRong on 2019/3/24.
 */


public class SelectActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout companyPartLaout,itemLayout;
    private Spinner companyPartSpinner,itemSpinner;
    private Button isCompanyBtn,isItemBtn,submitBtn;

    private TextView userName,companyName;

    private  String[] companyPartSpinnerData;
    private  String[] itemSpinnerData;
    private Long backTime;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        backTime = 165L;
        initView();
        initData(DBUtils.user.getIdentify());

    }
    public void initView(){
        companyPartLaout = findViewById(R.id.company_part_layout);
        itemLayout = findViewById(R.id.item_layout);
        userName = findViewById(R.id.userName);
        companyName = findViewById(R.id.companyName);

        companyPartSpinner = findViewById(R.id.spinner_company_part);
        itemSpinner = findViewById(R.id.spinner_item);

        isCompanyBtn = findViewById(R.id.isCompany_btn);
        isItemBtn = findViewById(R.id.isItem_btn);
        submitBtn = findViewById(R.id.submit_btn);

        isItemBtn.setOnClickListener(this);
        isCompanyBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
    }

    public void initData(int identify){

        userName.setText(DBUtils.user.getName());


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Company company =  DBUtils.queryCompanyByUser(DBUtils.user.getId());
                String json = JSONObject.toJSONString(company);
                Message msg = new Message();
                msg.what=1001;
                Bundle data = new Bundle();
                data.putString("list",json);
                msg.setData(data);
                mHandler.sendMessage(msg);
            }
        });
        thread.start();

        switch (identify){

            case 0:
                itemLayout.setVisibility(View.INVISIBLE);
                queryCompany();
                queryItemByUser(null);
                DBUtils.itemOrCompany = 2;
                break;
            case 1:

                companyPartLaout.setVisibility(View.INVISIBLE);
                isItemBtn.setVisibility(View.INVISIBLE);
                isCompanyBtn.setVisibility(View.INVISIBLE);
                queryItemByUser(DBUtils.user.getId());
                DBUtils.itemOrCompany = 1;
                break;
        }

    }

    //初始化公司
    public void queryCompany(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Company> companyList =  DBUtils.queryCompany(2);
                String json = JSONObject.toJSONString(companyList);
                Message msg = new Message();
                msg.what=1002;
                Bundle data = new Bundle();
                data.putString("list",json);
                msg.setData(data);
                mHandler.sendMessage(msg);
            }
        });
        thread1.start();
    }

    //初始化项目
    public void queryItemByUser(@Nullable final Integer userId){

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Item> items =  DBUtils.queryItems(userId);
                String json = JSONObject.toJSONString(items);
                Message msg = new Message();
                msg.what=1003;
                Bundle data = new Bundle();
                data.putString("list",json);
                msg.setData(data);
                mHandler.sendMessage(msg);
            }
        });
        thread2.start();
    }



    public void initCompanyPartSpinner(final List<Company> list){

        // 建立数据源
        companyPartSpinnerData = new String[list.size()];
        for (int i = 0; i< list.size(); i++) {

            System.out.println(list.get(i).getId()+"--"+list.get(i).getCompany_name());
            companyPartSpinnerData[i]= list.get(i).getCompany_name();
        }

        // 建立Adapter并且绑定数据源
        ArrayAdapter<String > adapter=new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,companyPartSpinnerData);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //绑定 Adapter到控件
        companyPartSpinner.setAdapter(adapter);
        companyPartSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //选择列表项的操作
                System.out.println("mItems = " + list.get(position).getCompany_name());
                DBUtils.companyPart = list.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //未选中时候的操作
            }
        });
    }




    public void initItemSpinner(final List<Item> list){

        // 建立数据源
        itemSpinnerData = new String[list.size()];
        for (int i = 0 ; i< list.size(); i++ ){
            System.out.println(list.get(i).getId()+"--"+list.get(i).getItemName());
            itemSpinnerData[i]= list.get(i).getItemName();
        }

        // 建立Adapter并且绑定数据源
        ArrayAdapter<String > adapter=new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,itemSpinnerData);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //绑定 Adapter到控件
        itemSpinner.setAdapter(adapter);
        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //选择列表项的操作
                System.out.println("mItems = " + list.get(position).getItemName());
                DBUtils.item = list.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //未选中时候的操作
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.isCompany_btn:
                itemLayout.setVisibility(View.INVISIBLE);
                companyPartLaout.setVisibility(View.VISIBLE);
                DBUtils.itemOrCompany = 2;
                break;
            case R.id.isItem_btn:
                companyPartLaout.setVisibility(View.INVISIBLE);
                itemLayout.setVisibility(View.VISIBLE);
                DBUtils.itemOrCompany = 1;
                break;
            case R.id.submit_btn:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            if (System.currentTimeMillis() - backTime>1000)
            {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                backTime = System.currentTimeMillis();
            }else {
                super.onPause();
                super.onStop();
                super.finish();
            }
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler(){
        @SuppressLint("SetTextI18n")
        public void handleMessage(android.os.Message msg) {
            switch (msg.what)
            {
                case 1001:
                    String json = msg.getData().getString("list");
                    Company company = JSON.parseObject(json, Company.class);
                    System.out.println("json = " + json);
                    assert company != null;
                    companyName.setText(company.getCompany_name()+"");
                    break;

                case 1002:
                    String json2 = msg.getData().getString("list");
                    List<Company> list2 = JSONObject.parseArray(json2,Company.class);
                    initCompanyPartSpinner(list2);
                    break;
                case 1003:
                    String json3 = msg.getData().getString("list");
                    List<Item> list3 = JSONObject.parseArray(json3,Item.class);
                    System.out.println("json = " + json3);

                    initItemSpinner(list3);
                    break;

                default:
                    break;
            }
        }
    };
}
