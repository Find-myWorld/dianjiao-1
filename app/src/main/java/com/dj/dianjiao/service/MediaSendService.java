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
        Serializable mediaPlay = intent.getSerializableExtra("jianqujianshi");
        Gson gson = new Gson();
        String jsonStr = gson.toJson(mediaPlay);
        Log.d("tag",jsonStr);
        try {
           // NettyClient.sendMsg2Server(jsonStr+"+mediaPlay");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("nettyClient 启动失败");
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d("tag","MediaSendService开启？");
    }
}
