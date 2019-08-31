package com.example.guanrong.xmxj_android.fragement;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.guanrong.xmxj_android.R;
import com.example.guanrong.xmxj_android.activity.Report;
import com.example.guanrong.xmxj_android.utils.DBUtils;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by GuanRong on 2019/3/26.
 */

public class Report_fragment extends Fragment {

    private ProgressDialog progressDialog;
    private WebView webView;

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface", "SetJavaScriptEnabled"})
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        progressDialog = ProgressDialog.show(getActivity(), "提示",
                "加载中……", false, false);
        View view = inflater.inflate(R.layout.fragment_report,null);
        webView = view.findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许Javascript脚本
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient());//拦截alert（）函数
        webView.loadUrl("file:///android_asset/views/report.html");//加载web页面
        webView.addJavascriptInterface(this, "demo");//html中JavaScript
        webView.requestFocusFromTouch();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);


            }
        });
        return view;
    }
    @JavascriptInterface
    public void dismissDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    @JavascriptInterface
    public void initData1(){
        webView.loadUrl("javascript:getName('"+
                JSON.toJSONString(DBUtils.user) +"','"+DBUtils.api+"')");
    }

    @JavascriptInterface
    public void submitReport(String jsonParam ){
        JSONObject jsonObject = JSON.parseObject(jsonParam);
        final Report report = jsonObject.toJavaObject(Report.class);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int re = DBUtils.submitReport(report);
                Message msg = new Message();
                msg.what = 1001;
                Bundle data = new Bundle();
                data.putInt("re",re);
                msg.setData(data);
                mHandler.sendMessage(msg);
            }
        });
        thread.start();
    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1001:
                    int re = msg.getData().getInt("re");

                    if (re == 1){
                        Toast.makeText(getActivity(), "填写成功", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getActivity(), "填写失败", Toast.LENGTH_SHORT).show();
                    }
                break;

            }
        }

    };

}
