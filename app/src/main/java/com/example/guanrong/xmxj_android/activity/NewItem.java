package com.example.guanrong.xmxj_android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.guanrong.xmxj_android.R;
import com.example.guanrong.xmxj_android.bean.Item;
import com.example.guanrong.xmxj_android.utils.DBUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by GuanRong on 2019/3/28.
 */

public class NewItem extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

    }

    public void login(){

        final String name = "aaa";
        final String password = "123456";

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


            }
        });
        thread.start();
    }



}
