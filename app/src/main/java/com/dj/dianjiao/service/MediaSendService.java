package com.dj.dianjiao.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.dj.dianjiao.netty.NettyClient;
import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/18.
 */
public class MediaSendService extends IntentService {

    public MediaSendService() {
        super("MediaSendService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final Serializable jianqujianshi = intent.getSerializableExtra("jianqujianshi");
        new Thread(){
            @Override
            public void run() {
                try {
                    Gson gson = new Gson();
                    String jsonStr = gson.toJson(jianqujianshi);
                    Log.d("tag",jsonStr);
                    NettyClient.sendMsg2Server(jsonStr+"+mediaPlay");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("nettyClient 启动失败");
                }
            }
        }.start();

    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d("tag","MediaSendService开启");
    }
}
