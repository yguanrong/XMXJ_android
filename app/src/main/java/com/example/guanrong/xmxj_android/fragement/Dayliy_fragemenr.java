package com.example.guanrong.xmxj_android.fragement;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guanrong.xmxj_android.R;
import com.example.guanrong.xmxj_android.activity.AddRecordActivity;
import com.example.guanrong.xmxj_android.activity.MainActivity;
import com.example.guanrong.xmxj_android.activity.RecordActivity;
import com.example.guanrong.xmxj_android.activity.ReformActivity;
import com.example.guanrong.xmxj_android.activity.ReviewActivity;
import com.example.guanrong.xmxj_android.activity.TongjiActivity;
import com.example.guanrong.xmxj_android.activity.VideoPlayActivity;
import com.example.guanrong.xmxj_android.utils.DBUtils;
import com.example.guanrong.xmxj_android.utils.PhotoPath;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static com.example.guanrong.xmxj_android.utils.PhotoPath.Bitmap2String;
import static com.example.guanrong.xmxj_android.utils.PhotoPath.resizeBitmap;


/**
 * Created by GuanRong on 2019/3/6.
 */

public class Dayliy_fragemenr extends Fragment implements View.OnClickListener{

    private TextView xm_name , jcjl_sum, dqcz_num, yzjy_num ,cqwzg_num ;
    private Button sp_btn,tjfx,addRecord_btn,sao_btn,album_btn;
    private LinearLayout aqxg_lay,zgjc_lay,jcjl_lay,pfjc_lay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.fragment_1,null);

        xm_name = view.findViewById(R.id.xm_name);
        jcjl_sum = view.findViewById(R.id.jcjl_sum);
        dqcz_num = view.findViewById(R.id.dqcz_num);
        cqwzg_num = view.findViewById(R.id.cqwzg_num);
        yzjy_num = view.findViewById(R.id.yzjy_num);
        tjfx = view.findViewById(R.id.tjfx);
        sp_btn = view.findViewById(R.id.sp_btn);
        addRecord_btn = view.findViewById(R.id.addRecord_btn);
        sao_btn = view.findViewById(R.id.sao_btn);
        album_btn = view.findViewById(R.id.album_btn);

        aqxg_lay = view.findViewById(R.id.aqxg_lay);
        zgjc_lay = view.findViewById(R.id.zgjc_lay);
        jcjl_lay = view.findViewById(R.id.jcjl_lay);
        pfjc_lay = view.findViewById(R.id.pfjc_lay);

        tjfx.setOnClickListener(this);
        sp_btn.setOnClickListener(this);
        aqxg_lay.setOnClickListener(this);
        zgjc_lay.setOnClickListener(this);
        jcjl_lay.setOnClickListener(this);
        pfjc_lay.setOnClickListener(this);
        addRecord_btn.setOnClickListener(this);
        sao_btn.setOnClickListener(this);
        album_btn.setOnClickListener(this);
        return view;
    }

    //初始化界面后调用
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @SuppressLint("SetTextI18n")
    public void initData(){
        if (DBUtils.user.getIdentify() <=1){
            switch (DBUtils.itemOrCompany){
                case 2:
                    xm_name.setText(DBUtils.company.getCompany_name()+"");
                    //System.out.println("DBUtils.item.getItemName() = " + DBUtils.item.getItemName());
                    //查询分包公司旗下的所有记录
                    queryCount(null,DBUtils.companyPart.getId(),
                            null,null,null, null,1001);
                    //查询分包公司旗下的所有未整改记录 dangerSatet = 1
                    queryCount(null,DBUtils.companyPart.getId(),
                            null,null,1, null,1002);
                    //查询分包公司旗下的所有严重紧要项目 level = 2
                    queryCount(null,DBUtils.companyPart.getId(),
                            null,null,null,2,1003);
                    //查询分包公司旗下的超期未整改 level = 2
                    queryCount(null,DBUtils.companyPart.getId(),
                            null,null,0,null,1004);
                    break;
                case 1:
                    xm_name.setText(DBUtils.item.getItemName()+"");
                    //System.out.println("DBUtils.item.getItemName() = " + DBUtils.item.getItemName());
                    //查询项目的所有记录
                    queryCount(DBUtils.item.getId(),null,
                            null,null,null, null,1001);
                    //查询项目的所有未整改记录 dangerSatet = 1
                    queryCount(DBUtils.item.getId(),null,
                            null,null,1, null,1002);
                    //查询项目的所有严重紧要项目 level = 2
                    queryCount(DBUtils.item.getId(),null,
                            null,null,null,2,1003);
                    //查询项目的超期未整改 level = 2
                    queryCount(DBUtils.item.getId(),null,
                            null,null,0,null,1004);
                    break;
            }
        }
        else if (DBUtils.user.getIdentify() == 2){
            xm_name.setText("安全员：" + DBUtils.user.getName());
            //System.out.println("DBUtils.item.getItemName() = " + DBUtils.item.getItemName());
            //查询项目的所有记录
            queryCount(null,null,
                    DBUtils.user.getId(),null,null, null,1001);
            //查询项目的所有未整改记录 dangerSatet = 1
            queryCount(null,null,
                    DBUtils.user.getId(),null,1, null,1002);
            //查询项目的所有严重紧要项目 level = 2
            queryCount(null,null,
                    DBUtils.user.getId(),null,null,2,1003);
            //查询项目的超期未整改 level = 2
            queryCount(null,null,
                    DBUtils.user.getId(),null,0,null,1004);

        }
        else if (DBUtils.user.getIdentify() == 3){
            xm_name.setText("责任人：" + DBUtils.user.getName());
            //System.out.println("DBUtils.item.getItemName() = " + DBUtils.item.getItemName());
            //查询项目的所有记录
            queryCount(null,null,
                    null,DBUtils.user.getId(),null, null,1001);
            //查询项目的所有未整改记录 dangerSatet = 1
            queryCount(null,null,
                    null,DBUtils.user.getId(),1, null,1002);
            //查询项目的所有严重紧要项目 level = 2
            queryCount(null,null,
                    null,DBUtils.user.getId(),null,2,1003);
            //查询项目的超期未整改 level = 2
            queryCount(null,null,
                    null,DBUtils.user.getId(),0,null,1004);
        }
    }

    public void queryCount(
            @Nullable final Integer itemId,
            @Nullable final Integer companyId,
            @Nullable final Integer createPId,
            @Nullable final Integer zhenggaiPId,
            @Nullable final Integer dangerState,
            @Nullable final Integer level,
            final Integer what){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = DBUtils.queryCountDanger(itemId, companyId ,
                        createPId,zhenggaiPId,dangerState,level);
                Message msg = new Message();
                msg.what=what;
                Bundle data = new Bundle();
                data.putInt("count",count);
                msg.setData(data);
                mHandler.sendMessage(msg);
            }
        });
        thread.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sp_btn:
                System.out.println("点击了视频按钮");
                Intent intent6 = new Intent(getActivity(), VideoPlayActivity.class);
                startActivity(intent6);

                break;
            case R.id.tjfx:
                System.out.println("点击了统计");
//                Intent intent = new Intent(getActivity(), TongjiActivity.class);
//                startActivity(intent);
                MainActivity.changeIndex(2);
                break;
            case R.id.aqxg_lay:
                if (DBUtils.user.getIdentify() <= 2)
                {
                    Toast.makeText(getActivity(), "你无权进行隐患整改", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent3 = new Intent(getActivity(), ReformActivity.class);
                    startActivity(intent3);

                }
                System.out.println("点击了安全修改");
                break;
            case R.id.zgjc_lay:

                if (DBUtils.user.getIdentify() == 0
                        || DBUtils.user.getIdentify() == 1
                        || DBUtils.user.getIdentify() == 3)
                {
                    Toast.makeText(getActivity(), "你无权进行整改复查", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent5 = new Intent(getActivity(), ReviewActivity.class);
                    startActivity(intent5);

                }
                System.out.println("点击了整改复查");

                break;
            case R.id.jcjl_lay:
                System.out.println("点击了检查记录");
                Intent intent2 = new Intent(getActivity(), RecordActivity.class);
                startActivity(intent2);
                break;
            case R.id.pfjc_lay:
                System.out.println("点击了评分检测");
                Toast.makeText(getActivity(), "暂未开启", Toast.LENGTH_SHORT).show();
                break;
            case R.id.addRecord_btn:
                System.out.println("点击了检测按钮");
                if (DBUtils.user.getIdentify() == 0
                        || DBUtils.user.getIdentify() == 1
                        || DBUtils.user.getIdentify() == 3)
                {
                    Toast.makeText(getActivity(), "你无权进行隐患整改", Toast.LENGTH_SHORT).show();
                }
                else{
                    DBUtils.isEdit = false;
                    Intent intent1 = new Intent(getActivity(), AddRecordActivity.class);
                    startActivity(intent1);
                }

                break;
            case R.id.sao_btn:
                System.out.println("点击了扫一扫");
                toWeChatScan();
                //Toast.makeText(getActivity(), "暂未开启", Toast.LENGTH_SHORT).show();
                break;
            case R.id.album_btn:
                System.out.println("点击了相册");
                getImageFromAlbum();
                break;
        }
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void toWeChatScan() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.tencent.mm",
                    "com.tencent.mm.ui.LauncherUI"));
            intent.putExtra("LauncherUI.From.Scaner.Shortcut", true);
            intent.setFlags(335544320);
            intent.setAction("android.intent.action.VIEW");
            getContext().startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "还没安装微信，无法使用功能", Toast.LENGTH_SHORT).show();
        }

    }


    //从相册选择
    public void getImageFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }


    //拍照的返回函数
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //从相册上传
        if(resultCode == Activity.RESULT_OK && requestCode == 2) {
            Toast.makeText(getActivity(), "选择了照片", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler(){
        @SuppressLint("SetTextI18n")
        public void handleMessage(android.os.Message msg) {
            switch (msg.what)
            {
                case 1001:
                    int count1 = msg.getData().getInt("count");
                    jcjl_sum.setText(count1+"");
                    break;
                case 1002:
                    int count2 = msg.getData().getInt("count");
                    dqcz_num.setText(count2+"");
                    break;
                case 1003:
                    int count3 = msg.getData().getInt("count");
                    yzjy_num.setText(count3+"");
                    break;
                case 1004:
                    int count4 = msg.getData().getInt("count");
                    cqwzg_num.setText(count4+"");
                    break;

                default:
                    break;
            }
        }
    };

}
