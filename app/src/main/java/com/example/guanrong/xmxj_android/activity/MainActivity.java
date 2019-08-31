package com.example.guanrong.xmxj_android.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.guanrong.xmxj_android.R;
import com.example.guanrong.xmxj_android.fragement.Dayliy_fragemenr;
import com.example.guanrong.xmxj_android.fragement.My_fragment;
import com.example.guanrong.xmxj_android.fragement.Report_fragment;
import com.example.guanrong.xmxj_android.fragement.Statistics_fragement;
import com.example.guanrong.xmxj_android.utils.DBUtils;


public class MainActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;
    private static TextView day_text,zl_text,fx_text,my_text;
    private Long backTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        backTime = 165L;
    }

    public void initView(){
        day_text = findViewById(R.id.text_home);
        zl_text = findViewById(R.id.text_cp);
        fx_text = findViewById(R.id.text_tz);
        my_text = findViewById(R.id.text_my);

        fragmentManager=getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content,new Dayliy_fragemenr());
        day_text.setTextColor(Color.BLUE);
        zl_text.setTextColor(Color.BLACK);
        fx_text.setTextColor(Color.BLACK);
        my_text.setTextColor(Color.BLACK);
        fragmentTransaction.commit();

    }

    public void dayli_click(View view) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content,new Dayliy_fragemenr());
        day_text.setTextColor(Color.BLUE);
        zl_text.setTextColor(Color.BLACK);
        fx_text.setTextColor(Color.BLACK);
        my_text.setTextColor(Color.BLACK);
        fragmentTransaction.commit();

    }

    public void ziliao_click(View view) {
        if (DBUtils.user.getIdentify() > 1){
            Toast.makeText(this, "你无权人员/项目统计分析", Toast.LENGTH_SHORT).show();
        }
        else {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_content,new Statistics_fragement());
            day_text.setTextColor(Color.BLACK);
            zl_text.setTextColor(Color.BLUE);
            fx_text.setTextColor(Color.BLACK);
            my_text.setTextColor(Color.BLACK);
            fragmentTransaction.commit();
        }
    }

    public void fenxiangquan_click(View view) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content,new Report_fragment());
        day_text.setTextColor(Color.BLACK);
        zl_text.setTextColor(Color.BLACK);
        fx_text.setTextColor(Color.BLUE);
        my_text.setTextColor(Color.BLACK);
        fragmentTransaction.commit();

    }

    public void my_click(View view) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_content,new My_fragment());
        day_text.setTextColor(Color.BLACK);
        zl_text.setTextColor(Color.BLACK);
        fx_text.setTextColor(Color.BLACK);
        my_text.setTextColor(Color.BLUE);
        fragmentTransaction.commit();
    }


    public static void changeIndex(int index){
        switch (index){
            case 1:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_content,new Dayliy_fragemenr());
                day_text.setTextColor(Color.BLUE);
                zl_text.setTextColor(Color.BLACK);
                fx_text.setTextColor(Color.BLACK);
                my_text.setTextColor(Color.BLACK);
                fragmentTransaction.commit();
                break;
            case 2:
                if (DBUtils.user.getIdentify() > 1){

                }
                else {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_content,new Statistics_fragement());
                    day_text.setTextColor(Color.BLACK);
                    zl_text.setTextColor(Color.BLUE);
                    fx_text.setTextColor(Color.BLACK);
                    my_text.setTextColor(Color.BLACK);
                    fragmentTransaction.commit();
                }
                break;
            case 3:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_content,new Report_fragment());
                day_text.setTextColor(Color.BLACK);
                zl_text.setTextColor(Color.BLACK);
                fx_text.setTextColor(Color.BLUE);
                my_text.setTextColor(Color.BLACK);
                fragmentTransaction.commit();
                break;
            case 4:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_content,new My_fragment());
                day_text.setTextColor(Color.BLACK);
                zl_text.setTextColor(Color.BLACK);
                fx_text.setTextColor(Color.BLACK);
                my_text.setTextColor(Color.BLUE);
                fragmentTransaction.commit();
                break;
        }
    }

    //监听Back键按下事件,方法1
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            if (DBUtils.user.getIdentify() >= 2)
            {
                if (System.currentTimeMillis() - backTime>1000)
                {
                    Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                    backTime = System.currentTimeMillis();
                }else {
                    super.onPause();
                    super.onStop();
                    super.finish();
                }
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

}
