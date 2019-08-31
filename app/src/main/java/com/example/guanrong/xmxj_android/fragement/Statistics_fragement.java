package com.example.guanrong.xmxj_android.fragement;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
import com.alibaba.fastjson.JSON;
import com.example.guanrong.xmxj_android.activity.MainActivity;
import com.example.guanrong.xmxj_android.bean.CountForWeek;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import android.webkit.JavascriptInterface;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guanrong.xmxj_android.R;
import com.example.guanrong.xmxj_android.activity.DangerAreaActivity;
import com.example.guanrong.xmxj_android.activity.DangerCompanyActivity;
import com.example.guanrong.xmxj_android.activity.DangerTypeActivity;
import com.example.guanrong.xmxj_android.activity.DangerUserActivity;
import com.example.guanrong.xmxj_android.utils.DBUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by GuanRong on 2019/3/20.
 */

public class Statistics_fragement extends Fragment implements View.OnClickListener{

    private WebView webView;
    ProgressDialog progressDialog;
    private TextView xm_name;
    private Button companyPart_btn,dangetType_btn,user_btn,dangerArea_btn,workRepot_btn;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_2,null);
        xm_name = view.findViewById(R.id.xm_name);
        webView = view.findViewById(R.id.chart2);
        companyPart_btn = view.findViewById(R.id.companyPart_btn);
        dangetType_btn = view.findViewById(R.id.dangetType_btn);
        user_btn = view.findViewById(R.id.user_btn);
        dangerArea_btn = view.findViewById(R.id.dangerArea_btn);
        workRepot_btn = view.findViewById(R.id.workRepot_btn);

        return view;
    }

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled", "AddJavascriptInterface"})
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressDialog = ProgressDialog.show(getActivity(), "提示",
                "加载中……", false, true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许Javascript脚本
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient());//拦截alert（）函数
        webView.loadUrl("file:///android_asset/views/weekStatistics.html");//加载web页面
        webView.addJavascriptInterface(this, "demo");//html中JavaScript
        webView.requestFocusFromTouch();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                intData();
            }
        });


    }
    @JavascriptInterface
    public void dismissDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    public void intData(){

        companyPart_btn.setOnClickListener(this);
        dangetType_btn.setOnClickListener(this);
        user_btn.setOnClickListener(this);
        dangerArea_btn.setOnClickListener(this);
        workRepot_btn.setOnClickListener(this);

        if (DBUtils.user.getIdentify() <=1){
            switch (DBUtils.itemOrCompany){
                case 2:
                    xm_name.setText(DBUtils.company.getCompany_name()+"");
                    queryDangerByWeek(null,DBUtils.company.getId());

                    break;
                case 1:
                    xm_name.setText(DBUtils.item.getItemName()+"");
                    queryDangerByWeek(DBUtils.item.getId(),null);
                    break;
            }
        }
//        else if (DBUtils.user.getIdentify() == 2){
//            xm_name.setText("安全员：" + DBUtils.user.getName());
//        }
//        else if (DBUtils.user.getIdentify() == 3){
//            xm_name.setText("责任人：" + DBUtils.user.getName());
//        }
//        switch (DBUtils.user.getIdentify()){
//            case 0:
//            case 1:
//                xm_name.setText(DBUtils.company.getCompany_name()+"");
//                queryDangerByWeek(null,DBUtils.company.getId());
//                break;
//            case 2:
//            case 3:
//                xm_name.setText(DBUtils.item.getItemName()+"");
//                queryDangerByWeek(DBUtils.item.getId(),null);
//                break;
//        }
    }


    public void queryDangerByWeek(@Nullable final Integer itemId, @Nullable final Integer companyId){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String result = DBUtils.queryDangerByWeek(itemId, companyId);
                Message msg = new Message();
                msg.what = 1001;
                Bundle data = new Bundle();
                data.putString("result",result);
                msg.setData(data);
                mHandler.sendMessage(msg);
            }
        });
        thread.start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.companyPart_btn:

                if (DBUtils.user.getIdentify() < 1){
                    Intent intent1 = new Intent(getActivity(), DangerCompanyActivity.class);
                    startActivity(intent1);
                }
                else {
                    Toast.makeText(getActivity(), "你无权执行此操作", Toast.LENGTH_SHORT).show();
                }

//                if (DBUtils.user.getIdentify() >=2){
//
//                }
//                else {
//
//                }

                break;
            case R.id.dangetType_btn:

                Intent intent2 = new Intent(getActivity(), DangerTypeActivity.class);
                startActivity(intent2);
                break;
            case R.id.user_btn:
                if (DBUtils.user.getIdentify() < 1){
                    Intent intent3 = new Intent(getActivity(), DangerUserActivity.class);
                    startActivity(intent3);
                }else {
                    Toast.makeText(getActivity(), "你无权执行此操作", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.dangerArea_btn:
                if (DBUtils.user.getIdentify() > 0)
                {
                    Intent intent4 = new Intent(getActivity(), DangerAreaActivity.class);
                    startActivity(intent4);
                }else {
                    Toast.makeText(getActivity(), "请到电脑端查看", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.workRepot_btn:
                MainActivity.changeIndex(3);
                break;
        }
    }


    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1001:
                    String result = msg.getData().getString("result");
                    List<CountForWeek> list = JSON.parseArray(result,CountForWeek.class);
                    System.out.println("result = " + result);
                    CountForWeek countForWeek = list.get(0);
                    //setData(importantDanger,seriousDanger,commonDanger,dangerSum,datas)
                    System.out.println("countForWeek = " + countForWeek.getDates());
                    webView.loadUrl("javascript:seteData('"+
                            Arrays.toString(countForWeek.getImportantDanger()) +"','"+
                            Arrays.toString(countForWeek.getSeriousDanger()) +"','"+
                            Arrays.toString(countForWeek.getCommonDanger())+"','"+
                            Arrays.toString(countForWeek.getCount())+"','"+
                            countForWeek.getDates() +"')");
                    break;

            }
        }

    };
}
