package com.example.guanrong.xmxj_android.fragement;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;

import com.alibaba.fastjson.JSONObject;
import com.example.guanrong.xmxj_android.activity.AddRecordActivity;
import com.example.guanrong.xmxj_android.activity.LoginActivity;
import com.example.guanrong.xmxj_android.activity.SelectActivity;
import com.example.guanrong.xmxj_android.bean.ResultInfo;
import com.example.guanrong.xmxj_android.utils.PhotoPath;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import com.alibaba.fastjson.JSON;
import com.example.guanrong.xmxj_android.R;
import com.example.guanrong.xmxj_android.bean.Item;
import com.example.guanrong.xmxj_android.utils.DBUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.guanrong.xmxj_android.utils.PhotoPath.Bitmap2String;
import static com.example.guanrong.xmxj_android.utils.PhotoPath.resizeBitmap;

/**
 * Created by GuanRong on 2019/3/26.
 */

public class My_fragment extends Fragment implements View.OnClickListener{

    private ProgressDialog progressDialog;
    private WebView webView;


    private Camera camera;
    private String sd_default;
    private String fileName;
    private String picPath;
    private String path;

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface", "SetJavaScriptEnabled"})
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        progressDialog = ProgressDialog.show(getActivity(), "提示",
                "加载中……", false, false);
        View view = inflater.inflate(R.layout.fragment_4,null);
        webView = view.findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许Javascript脚本
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient());//拦截alert（）函数
        webView.loadUrl("file:///android_asset/views/myData.html");//加载web页面
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
    //初始化界面后调用
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        path = DBUtils.imgUrl+DBUtils.user.getImg();
        initCanmera();
    }
    @JavascriptInterface
    public void dismissDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
        }
        webView.loadUrl("javascript:getPic('"+path+"')");
    }

    //对内存和相机权限的配置
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void initCanmera() {

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            Toast.makeText(getActivity(), "没有检测到SD卡！", Toast.LENGTH_SHORT).show();//提示用户登陆成功
            return;
        }

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);//1 can be another integer
        }

        sd_default = Environment.getExternalStorageDirectory().getAbsolutePath();
        if (sd_default.endsWith("/")) {
            sd_default = sd_default.substring(0, sd_default.length() - 1);
        }
        try {
            File file = new File(sd_default + "/DCIM/Camera/");
            if (!file.exists()) {
                file.mkdirs();
            }
            file.exists();
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA}, 1);//1 can be another integer
        }

        try {
            camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
            final Camera.Parameters cameraParams = camera.getParameters();
            List<Camera.Size> photoSizes = cameraParams.getSupportedPictureSizes();//获取系统可支持的图片尺寸
            for (Camera.Size size : photoSizes) {//因为我需要的是4：3的图片，所以设置为4：3图片尺寸。
                if (size.width / 4 == size.height / 3) {
                    cameraParams.setPictureSize(size.width, size.height);
                    break;
                }
            }
        } catch (RuntimeException e) {
            Toast.makeText(getActivity(), "请打开摄像头权限", Toast.LENGTH_SHORT).show();
        }
        if (camera != null)
            camera.release();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(new java.util.Date());
    }

    @JavascriptInterface
    public void initData(){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Item> items = DBUtils.queryItems(DBUtils.user.getId());
                Message msg = new Message();
                msg.what = 1001;
                Bundle data = new Bundle();
                data.putString("items",JSON.toJSONString(items));
                msg.setData(data);
                mHandler.sendMessage(msg);

            }
        });
        thread.start();

    }

    //拍照
    @JavascriptInterface
    public void takePhoto() {
        String str ;
        Date date ;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");//获取当前时间，进一步转化为字符串
        date = new Date();
        str = format.format(date);
        fileName = sd_default + "/DCIM/Camera/IMG_" +str+ ".jpg";
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(fileName)));
        startActivityForResult(intent, 1);//this.DEFAULT_KEYS_DIALER);
    }

    //从相册选择
    public void getImageFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    //选择拍照或者上传
    @JavascriptInterface
    public void showChoseCam(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setIcon(R.drawable.tubaio);
        builder.setTitle("选择拍照或者相册");
        /**左边按钮*/
        builder.setNegativeButton("相\t册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getImageFromAlbum();
            }
        });
        /**右边按钮*/
        builder.setPositiveButton("相\t机", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //权限还没有授予，需要在这里写申请权限的代码
                    // 第二个参数是一个字符串数组，里面是你需要申请的权限 可以设置申请多个权限
                    // 最后一个参数是标志你这次申请的权限，该常量在onRequestPermissionsResult中使用到
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                }else { //权限已经被授予，在这里直接写要执行的相应方法即可
                    takePhoto();
                }
            }
        });
        Dialog dialog=builder.create();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    //拍照的返回函数
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //拍照上传
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {

            try {

                Bitmap imageBitmap = BitmapFactory.decodeFile(fileName);
                Bitmap take = resizeBitmap(imageBitmap, 640, 860);
                File file = new File(fileName);

                updateB(file);

                setPicPath(fileName);
                BufferedOutputStream bos = new BufferedOutputStream(
                        new FileOutputStream(file));
                take.compress(Bitmap.CompressFormat.JPEG, 100, bos);// 将图片压缩到流中

                changeImg(Bitmap2String(take));

                bos.flush();// 输出
                bos.close();// 关闭

                webView.loadUrl("javascript:getPic('"+getPicPath()+"')");
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        //从相册上传
        else if(resultCode == Activity.RESULT_OK && requestCode == 2) {

            Uri uri = data.getData();
            String photoPath = PhotoPath.getRealPathFromUri(getActivity(),uri);
            ContentResolver cr = getActivity().getContentResolver();
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap take = resizeBitmap(bitmap, 640, 860);

            setPicPath(photoPath);
            changeImg(Bitmap2String(take));
            webView.loadUrl("javascript:getPic('"+getPicPath()+"')");
        }

    }
    //通知图库
    public void updateB(File file){
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        getActivity().sendBroadcast(intent);
    }


    public void changeImg(final String imgData){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String re = DBUtils.updateUserImg(DBUtils.user.getId(),imgData);
                Message msg = new Message();
                msg.what = 1002;
                Bundle data = new Bundle();
                data.putString("re",re);
                msg.setData(data);
                mHandler.sendMessage(msg);
            }
        });
        thread.start();
    }

    @Override
    public void onClick(View v) {

    }

    @JavascriptInterface
    public void logout(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();

    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1001:
                    String itemJson = msg.getData().getString("items");
                    String userJson = JSON.toJSONString(DBUtils.user);
                    String companyJson = JSON.toJSONString(DBUtils.companys1);

                    System.out.println("没有companyJson = " + companyJson);
                    webView.loadUrl("javascript:initData('"
                            +userJson+"', '"+companyJson+"', '"+itemJson +"','"
                            +DBUtils.api+"','"+DBUtils.imgUrl+"')");

                    //初始化头像
                    System.out.println("path = " + path);
                    webView.loadUrl("javascript:getPic('"+path+"')");
                    break;
                case 1002:
                    String rs = msg.getData().getString("re");
                    ResultInfo resultInfo= JSONObject.parseObject(rs,ResultInfo.class);
                    System.out.println("re = " + rs);
                    if (resultInfo.getState()==1){
                        DBUtils.user.setImg(resultInfo.getMsg());
                        Toast.makeText(getActivity(), "更换头像成功", Toast.LENGTH_SHORT).show();
                    }
                    break;

            }
        }

    };

}
