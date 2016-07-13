package com.dj.dianjiao.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.dj.dianjiao.activity.LoginActivity;
import com.dj.dianjiao.domain.LoginResultEvent;
import com.dj.dianjiao.netty.NettyClient;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/7/8.
 */
public class LoginService extends IntentService{
    private static final String TAG="tag";
    private String jsonStrm;
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public LoginService(){
        super("LoginService");
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final String username = intent.getStringExtra("username");
        final String password = intent.getStringExtra("password");
        Log.d(TAG,username+ "+++++++++++++" +password);
        /*生成json字符串*/
        final JSONObject jsonObject = new JSONObject();
        //发送json信息
        new Thread() {
            @Override
            public void run() {
                Socket client = null;
                PrintWriter pw = null;
                try {
                    jsonObject.put("username",username);
                    jsonObject.put("password",password);
                    jsonStrm =  jsonObject.toString();
                    Log.d("tag",jsonStrm);
                    //向服务端发送信息
                    NettyClient.sendMsg2Server(jsonStrm);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("error","与该IP地址无法建立通信");
                }
            }
        }.start();
    }

    @Override
    public void onCreate() {
        Log.d(TAG,"服务创建成功");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d(TAG,"服务开启");
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"服务销毁");
        super.onDestroy();
    }
}
