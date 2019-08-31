package com.example.guanrong.xmxj_android.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.guanrong.xmxj_android.R;
import com.example.guanrong.xmxj_android.bean.CountForCompany;
import com.example.guanrong.xmxj_android.utils.DBUtils;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.Arrays;

public class VideoPlayActivity extends AppCompatActivity {

    public WebView webView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        initView();
    }


    //对webView控件进行设置
    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled", "AddJavascriptInterface"})
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void initView(){

        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许Javascript脚本
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient());//拦截alert（）函数
        webView.loadUrl("file:///android_asset/views/videoPlay.html");//加载web页面
        webView.addJavascriptInterface(this, "demo");//html中JavaScript
        webView.requestFocusFromTouch();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                inietData();
            }
        });
    }

    public void inietData(){


    }

    //监听Back键按下事件,方法1
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            super.onPause();
            super.onStop();
            super.finish();
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }


    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1001:


                    break;

            }
        }

    };
}
