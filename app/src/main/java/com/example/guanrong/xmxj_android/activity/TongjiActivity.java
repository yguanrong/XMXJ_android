package com.example.guanrong.xmxj_android.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.guanrong.xmxj_android.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by GuanRong on 2019/3/19.
 */


@SuppressLint("Registered")
public class TongjiActivity extends AppCompatActivity implements View.OnClickListener{
    protected PieChart pieChart;
    protected LineChart lineChart;

    private TextView leiji_num,wxxyh_num;
    private View ysy,leiji;
    int b;
    int a;

    private Button qiri_btn,leiji_btn;
    private LinearLayout.LayoutParams ysy_lp;
    private LinearLayout.LayoutParams leiji_lp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongji);

        initView();
        initChar();


    }

    public void initView(){
        pieChart = findViewById(R.id.chart1);
        lineChart = findViewById(R.id.chart2);
        qiri_btn = findViewById(R.id.qiri_btn);
        leiji_btn = findViewById(R.id.leiji_btn);
        leiji_num = findViewById(R.id.leiji_num);
        wxxyh_num = findViewById(R.id.wxxyh_num);

        leiji = findViewById(R.id.leiji);
        ysy = findViewById(R.id.ysy);
        ysy_lp = (LinearLayout.LayoutParams) ysy.getLayoutParams();
        leiji_lp = (LinearLayout.LayoutParams) leiji.getLayoutParams();


        qiri_btn.setOnClickListener(this);
        leiji_btn.setOnClickListener(this);
    }

    public void initChar(){
        //1.设置x轴和y轴的点
        List<Entry> ens = new ArrayList<>();
        for (int i = 0; i < 12; i++)
            ens.add(new Entry(i, new Random().nextInt(300)));
        LineDataSet ds = new LineDataSet(ens, "Label"); // add entries to dataset

        //3.chart设置数据
        LineData lineData = new LineData(ds);
        lineChart.setData(lineData);
        lineChart.invalidate(); // refresh

        //showRingPieChart();

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(35.5f,""));//未用
        entries.add(new PieEntry(64.5f,""));

        PieDataSet dataSet = new PieDataSet(entries,"");

        //文字的大小
        dataSet.setValueTextSize(10);
        //文字的颜色
        dataSet.setValueTextColor(Color.GRAY);
        //文字样式
        dataSet.setValueTypeface(Typeface.DEFAULT_BOLD);

        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);


        PieData data = new PieData();
        data.setDataSet(dataSet);
        //格式化显示的数据为%百分比
        data.setValueFormatter(new PercentFormatter());
        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);
        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.setCenterTextColor(0xffffff);
        pieChart.setUsePercentValues(true);
        pieChart.setTouchEnabled(false);
        pieChart.setCenterText("项目使用率");
        pieChart.setCenterTextColor(R.color.white);
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.qiri_btn:
                a = Integer.parseInt(wxxyh_num.getText().toString());//100
                b = Integer.parseInt(leiji_num.getText().toString());//810
                a = a + 20;
                System.out.println("a = " + a);
                //b是总数，a是未消除隐患
                wxxyh_num.setText(a+"");
                ysy_lp.weight =(float) a/b;
                leiji_lp.weight =(float) (b-a)/b;
                ysy.setLayoutParams(ysy_lp);
                leiji.setLayoutParams(leiji_lp);
                System.out.println("b/a++b/(b-a) = " + (float)a/b+"--"+(float)(b-a)/b);

                break;

            case R.id.leiji_btn:
                int a = Integer.parseInt(leiji_num.getText().toString());
                a = a + 10;
                System.out.println("a = " + a);
                leiji_num.setText(a+"");
                break;

        }
    }
}
