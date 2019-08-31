package com.example.guanrong.xmxj_android.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
//import android.webkit.JavascriptInterface;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
import com.alibaba.fastjson.JSON;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import android.webkit.JavascriptInterface;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.guanrong.xmxj_android.R;
import com.example.guanrong.xmxj_android.utils.DBUtils;



public class RecordActivity extends AppCompatActivity implements View.OnClickListener{

    private WebView webView;
    private ImageView bcak_img;
    private Button newRecord_btn;

    private ProgressDialog progressDialog;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        initView();
    }

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled", "AddJavascriptInterface"})
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void initView(){

        progressDialog = ProgressDialog.show(this, "提示",
                "加载中……", false, true);
        bcak_img = findViewById(R.id.back_img);
        newRecord_btn = findViewById(R.id.newRecord_btn);
        bcak_img.setOnClickListener(this);
        newRecord_btn.setOnClickListener(this);
        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许Javascript脚本
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient());//拦截alert（）函数
        webView.loadUrl("file:///android_asset/views/record.html");//加载web页面
        webView.addJavascriptInterface(this, "demo");//html中JavaScript
        webView.requestFocusFromTouch();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                initData();

            }
        });
    }


    @JavascriptInterface
    public void dismissDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    public void initData(){

        String urlParam = "";
        switch (DBUtils.user.getIdentify()){
            case 0://管理员
                if (DBUtils.itemOrCompany == 1)//项目
                {
                    urlParam = "?itemId="+DBUtils.item.getId();
                }else {
                    urlParam = "?companyId="+DBUtils.companyPart.getId();
                }
                break;
            case 1:
                //项目经理
                urlParam = "?itemId="+DBUtils.item.getId();
                break;
            case 2://安全员
                urlParam = "?createPId="+DBUtils.user.getId();
                break;
            case 3://责任人
                urlParam = "?zhenggaiPId="+DBUtils.user.getId();
                break;
        }

        webView.loadUrl("javascript:setUser('"+ JSON.toJSONString(DBUtils.user)+"')");
        webView.loadUrl("javascript:setQueryUrlParam('"+urlParam+"','"+DBUtils.api+"')");

    }

    @JavascriptInterface
    public void editRecord(String rocordString){
        DBUtils.isReform = true;
        DBUtils.dangerJson = rocordString;
        Intent intent = new Intent(getApplicationContext(),ReformActivity.class);
        startActivity(intent);
    }

    @JavascriptInterface
    public void tip(){
        Toast.makeText(this, "无权执行此操作", Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void reviewRecord(String rocordString){
        DBUtils.isReview = true;
        DBUtils.dangerJson = rocordString;
        Intent intent = new Intent(getApplicationContext(),ReviewActivity.class);
        startActivity(intent);
    }

    @JavascriptInterface
    public void viewRecord(String rocordString){
        DBUtils.isView = true;
        DBUtils.dangerJson = rocordString;
        Intent intent = new Intent(getApplicationContext(),ReviewActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newRecord_btn:
                if (DBUtils.user.getIdentify() == 2){
                    DBUtils.isEdit = false;
                    Intent intent = new Intent(getApplicationContext(),AddRecordActivity.class);
                    startActivity(intent);
                }
                else {
                    tip();
                }
                break;

            case R.id.back_img:
                isBack().show();
                break;
        }
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
    //是否返回
    public Dialog isBack(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.apptubiao);
        builder.setTitle("项目巡检系统");
        builder.setMessage("确定要退出？\n");
        /**左边按钮*/
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            onPause();
            onStop();
            finish();
            }
        });
        /**右边按钮*/
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }


}
