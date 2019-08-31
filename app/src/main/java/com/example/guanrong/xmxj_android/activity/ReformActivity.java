package com.example.guanrong.xmxj_android.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
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
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.guanrong.xmxj_android.R;
import com.example.guanrong.xmxj_android.bean.Danger;
import com.example.guanrong.xmxj_android.bean.Data;
import com.example.guanrong.xmxj_android.bean.Zhenggai;
import com.example.guanrong.xmxj_android.service.httpUtil;
import com.example.guanrong.xmxj_android.utils.DBUtils;
import com.example.guanrong.xmxj_android.utils.PhotoPath;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.guanrong.xmxj_android.utils.PhotoPath.Bitmap2String;
import static com.example.guanrong.xmxj_android.utils.PhotoPath.resizeBitmap;

public class ReformActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView bcak_img;
    private WebView webView;
    private Button save_btn;
    private Camera camera;
    private String sd_default;
    private String fileName;
    private String picPath;
    private Danger danger;
    private String imgdata;
    private ProgressDialog progressDialog ;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reform);
        initView();
        initCanmera();
    }


    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zgenggai_btn:
                webView.loadUrl("javascript:submit()");
                break;
            case R.id.back_img:
                isBack().show();
                break;
        }

    }
    public void dismissDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    @JavascriptInterface
    public void reform(String dangerStr){
        progressDialog = ProgressDialog.show(this, "提示",
                "提交中……", false, true);
        JSONObject jsonObject = JSON.parseObject(dangerStr);
        System.out.println("jsonObject = " + jsonObject);
        danger = jsonObject.toJavaObject(Danger.class);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int dangerId = DBUtils.reform(danger);
                Message msg =new Message();
                msg.what = 1004;
                Bundle data = new Bundle();
                data.putInt("result",dangerId);
                msg.setData(data);
                mHandler.sendMessage(msg);
            }
        });
        if (imgdata!=null && !imgdata.equals(""))
        {
            thread.start();
        }else {
            Toast.makeText(this, "请选择或拍摄照片", Toast.LENGTH_SHORT).show();
        }


    }

    //对webView控件进行设置
    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled", "AddJavascriptInterface"})
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void initView(){

        save_btn = findViewById(R.id.zgenggai_btn);
        webView = findViewById(R.id.webView);
        bcak_img = findViewById(R.id.back_img);
        bcak_img.setOnClickListener(this);
        save_btn.setOnClickListener(this);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许Javascript脚本
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient());//拦截alert（）函数
        webView.loadUrl("file:///android_asset/views/reform.html");//加载web页面
        webView.addJavascriptInterface(this, "demo");//html中JavaScript
        webView.requestFocusFromTouch();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                initData();
                queryDangers();


            }
        });
    }

    //对内存和相机权限的配置
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void initCanmera() {

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            Toast.makeText(getApplicationContext(), "没有检测到SD卡！", Toast.LENGTH_SHORT).show();//提示用户登陆成功
            return;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this,
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
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this,
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
            Toast.makeText(this, "请打开摄像头权限", Toast.LENGTH_SHORT).show();
        }
        if (camera != null)
            camera.release();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(new java.util.Date());
    }

    //初始化
    public void initData(){

        String dangerAreas = JSON.toJSONString(DBUtils.dangerArea);
        String dangerTypes = JSON.toJSONString(DBUtils.dangerTypes);
        String companys = JSON.toJSONString(DBUtils.companys);
        webView.loadUrl("javascript:initData('"
                + dangerAreas + "','" + dangerTypes + "','" + companys + "','"+
                DBUtils.api+"','"+DBUtils.imgUrl+"')");
        if (DBUtils.isReform) {
            webView.loadUrl("javascript:setDangerData('" + DBUtils.dangerJson + "')");
        }

    }

    public void queryDangers(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String dangersJson = DBUtils.queryDangers(
                        "/queryDanger?dangerState=1&zhenggaiPId="+
                                DBUtils.user.getId() );
                Message msg = new Message();
                msg.what = 1002;
                Bundle data = new Bundle();
                data.putString("result",dangersJson);
                msg.setData(data);
                mHandler.sendMessage(msg);
            }
        });
        thread.start();
    }

    public void uploadImg(final String imgdata, final int dangerId, final int flag){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String rs = DBUtils.uploadImg(imgdata,dangerId,flag);
                Message msg = new Message();
                msg.what = 1001;
                Bundle data = new Bundle();
                data.putString("result", rs);
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
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    //选择拍照或者上传
    @JavascriptInterface
    public void showChoseCam(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.apptubiao);
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
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //权限还没有授予，需要在这里写申请权限的代码
                    // 第二个参数是一个字符串数组，里面是你需要申请的权限 可以设置申请多个权限
                    // 最后一个参数是标志你这次申请的权限，该常量在onRequestPermissionsResult中使用到
                    ActivityCompat.requestPermissions(ReformActivity.this,
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                imgdata = Bitmap2String(take);
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
            String photoPath = PhotoPath.getRealPathFromUri(this,uri);
            ContentResolver cr = this.getContentResolver();
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap take = resizeBitmap(bitmap, 640, 860);

            setPicPath(photoPath);
            imgdata = Bitmap2String(take);

            webView.loadUrl("javascript:getPic('"+getPicPath()+"')");
        }
    }
    //通知图库
    public void updateB(File file){
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        this.sendBroadcast(intent);
    }

    //监听Back键按下事件,方法1
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            DBUtils.isReform = false;
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

                DBUtils.isReform = false;
                ReformActivity.this.onPause();
                ReformActivity.this.onStop();
                ReformActivity.this.finish();
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

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1001:
                    String result = msg.getData().getString("result");
                    JSONObject jsonObject = JSON.parseObject(result);
                    System.out.println("jsonObject = " + jsonObject);
                    if (jsonObject.getInteger("state") != 0){
                        Toast.makeText(getApplicationContext(), "图片保存成功",
                                Toast.LENGTH_SHORT).show();

                        dismissDialog();
                        onPause();
                        onStop();
                        finish();

                    }
                    else
                        Toast.makeText(getApplicationContext(), jsonObject.getString("msg"),
                                Toast.LENGTH_SHORT).show();
                    break;
                case 1002:
                    String dangersJson = msg.getData().getString("result");
                    System.out.println("dangersJson = " + dangersJson);
                    webView.loadUrl("javascript:intData('"+dangersJson+"')");
                    break;
                case 1004:
                    int re = msg.getData().getInt("result");
                    if (re != 0)
                    {
                        Toast.makeText(getApplicationContext(), "整改成功",
                                Toast.LENGTH_SHORT).show();

                        if (imgdata!=null && !imgdata.equals(""))
                        {
                            uploadImg(imgdata,danger.getDangerId(),2);
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "整改失败",
                                Toast.LENGTH_SHORT).show();

                    }
                    break;
            }
        }

    };
}
