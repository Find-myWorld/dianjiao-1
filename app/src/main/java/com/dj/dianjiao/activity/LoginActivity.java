package com.dj.dianjiao.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.dj.dianjiao.R;
import com.dj.dianjiao.domain.LoginResultEvent;
import com.dj.dianjiao.service.LoginService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private String LOG = "LoginActivity";
    private TextView ieemcTV;
    private TextClock timeTC;
    private TextClock dateTC;
    private Button loginBtn;
    private EditText usernameET;
    private EditText passwordET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        EventBus.getDefault().register(this);

    }

    private void initViews() {
        ieemcTV = (TextView) findViewById(R.id.login_ieemp_c);
        timeTC = (TextClock) findViewById(R.id.clock_time);
        dateTC = (TextClock) findViewById(R.id.clock_date);
        loginBtn = (Button) findViewById(R.id.login_btn);
        usernameET = (EditText) findViewById(R.id.et_username);
        passwordET = (EditText) findViewById(R.id.et_password);

        AssetManager mgr=getAssets();//得到AssetManager
        Typeface tf=Typeface.createFromAsset(mgr, "fonts/MFYaYuan.otf");//根据路径得到Typeface
        ieemcTV.setTypeface(tf);//设置字体

        timeTC.setFormat12Hour("hh:mm");
        dateTC.setFormat24Hour("yyyy年MM月dd日 EEEE");

        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                /*向板子发送用户信息*/
                Intent serviceIntent = new Intent(getApplicationContext(),LoginService.class);

                serviceIntent.putExtra("username",usernameET.getText().toString().trim());
                serviceIntent.putExtra("password",passwordET.getText().toString().trim());
                startService(serviceIntent);
                //sendSocket();
                break;
        }
    }

    @Subscribe
    public void onEventMainThread(LoginResultEvent event) {
        if(event.getLoginResult()==1) {
            Looper.prepare();
            Toast.makeText(getApplicationContext(),"登陆成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            finish();
            Looper.loop();

        }else {
            Looper.prepare();
            Toast.makeText(getApplicationContext(),"用户名与密码不匹配", Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
