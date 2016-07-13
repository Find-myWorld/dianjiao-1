package com.dj.dianjiao.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.dj.dianjiao.R;
import com.dj.dianjiao.activity.MainActivity;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    private String LOG = "BaseActivity";
    private TextView homePageTV;
    private TextClock timeTC;
    private TextClock dateTC;
    private ImageView homePageIV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    protected void initBaseViews() {
        homePageTV = (TextView) findViewById(R.id.home_page_tv);
        homePageIV = (ImageView) findViewById(R.id.home_page_iv);
        timeTC = (TextClock) findViewById(R.id.base_clock_time);
        dateTC = (TextClock) findViewById(R.id.base_clock_date);

        timeTC.setFormat12Hour("hh:mm:ss");
        dateTC.setFormat24Hour("yyyy年MM月dd日 EEEE");

        homePageTV.setOnClickListener(this);
        homePageIV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_page_tv:
                goActivity(MainActivity.class);
                break;
            case R.id.home_page_iv:
                goActivity(MainActivity.class);
                break;
        }
    }

    protected void goActivity(Class toClass) {
        Intent intent = new Intent(this, toClass);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
