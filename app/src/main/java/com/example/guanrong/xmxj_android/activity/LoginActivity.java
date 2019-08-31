package com.example.guanrong.xmxj_android.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.guanrong.xmxj_android.R;
import com.example.guanrong.xmxj_android.bean.User;
import com.example.guanrong.xmxj_android.utils.DBUtils;


/**
 * Created by GuanRong on 2019/3/6.
 */

public class LoginActivity extends AppCompatActivity{

    private EditText user_name,user_password;
    private Button login_btn;
    private TextView forget_pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    public void initView(){
        user_name = findViewById(R.id.user_name);
        user_password = findViewById(R.id.user_password);
        login_btn = findViewById(R.id.login_btn);
        forget_pw = findViewById(R.id.forget_pw);

        forget_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "请联系管理员重置密码", Toast.LENGTH_SHORT).show();
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                User list = DBUtils
                        .login( user_name.getText().toString(),
                                user_password.getText().toString());
                String json = JSONObject.toJSONString(list);
                Message msg = new Message();
                msg.what=1001;
                Bundle data = new Bundle();
                data.putString("list",json);
                msg.setData(data);
                mHandler.sendMessage(msg);
            }
        });

        thread.start();
    }


    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what)
            {
                case 1001:
                    String json = msg.getData().getString("list");
                    JSONObject jsonObject = JSON.parseObject(json);
                    System.out.println("list.get(0) = " + jsonObject);

                    switch (jsonObject.getInteger("identify")){
                        case -1:
                            Toast.makeText(getApplicationContext(), "密码错误",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case -2:
                            Toast.makeText(getApplicationContext(), "账号不存在",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case -3:
                            Toast.makeText(getApplicationContext(), "连接服务器失败",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case 0:
                        case 1:
//                            管理员和项目经理
                            Intent i1 = new Intent(getApplicationContext(),SelectActivity.class);
                            startActivity(i1);
                            finish();
                            Toast.makeText(getApplicationContext(), "登陆成功",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                        case 3:
//                            安全员和责任人
                            Intent i2 = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(i2);
                            finish();
                            Toast.makeText(getApplicationContext(), "登陆成功",
                                    Toast.LENGTH_SHORT).show();
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    };


}
