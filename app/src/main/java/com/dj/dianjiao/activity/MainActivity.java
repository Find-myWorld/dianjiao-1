package com.dj.dianjiao.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextClock;
import android.widget.TextView;

import com.dj.dianjiao.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private String LOG = "MainActivity";
    private TextView ieemcTV;
    private TextClock timeTC;
    private TextClock dateTC;
    private RadioButton bofangkongzhiRB;
    private RadioButton tuwenbobaoRB;
    private RadioButton meitiziyuanRB;
    private RadioButton jihuaguanliRB;
    private RadioButton xitongpeizhiRB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        ieemcTV = (TextView) findViewById(R.id.main_ieemp_c);
        timeTC = (TextClock) findViewById(R.id.main_clock_time);
        dateTC = (TextClock) findViewById(R.id.main_clock_date);
        bofangkongzhiRB = (RadioButton) findViewById(R.id.bofangkongzhi_rb);
        tuwenbobaoRB = (RadioButton) findViewById(R.id.tuwenbobao_rb);
        meitiziyuanRB = (RadioButton) findViewById(R.id.meitiziyuan_rb);
        jihuaguanliRB = (RadioButton) findViewById(R.id.jihuaguanli_rb);
        xitongpeizhiRB = (RadioButton) findViewById(R.id.xitongpeizhi_rb);

        AssetManager mgr=getAssets();//得到AssetManager
        Typeface tf=Typeface.createFromAsset(mgr, "fonts/MFYaYuan.otf");//根据路径得到Typeface
        ieemcTV.setTypeface(tf);//设置字体
        timeTC.setFormat12Hour("hh:mm");
        dateTC.setFormat24Hour("yyyy年MM月dd日 EEEE");

        bofangkongzhiRB.setOnClickListener(this);
        tuwenbobaoRB.setOnClickListener(this);
        meitiziyuanRB.setOnClickListener(this);
        jihuaguanliRB.setOnClickListener(this);
        xitongpeizhiRB.setOnClickListener(this);

    }

    private void goActivity(Class toClass) {
        Intent intent = new Intent(this, toClass);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bofangkongzhi_rb:
                goActivity(PlayControlActivity.class);
                break;
            case R.id.tuwenbobao_rb:
                goActivity(PicturePlayActivity.class);
                break;
            case R.id.meitiziyuan_rb:
                goActivity(MediaPlayActivity.class);
                break;
            case R.id.jihuaguanli_rb:
                goActivity(PlanMangerActivity.class);
                break;
            case R.id.xitongpeizhi_rb:
                goActivity(SystemSetActivity.class);
                break;
        }
    }
}
