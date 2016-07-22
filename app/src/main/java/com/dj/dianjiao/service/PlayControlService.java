package com.dj.dianjiao.service;

import android.app.IntentService;
import android.content.Intent;

import com.dj.dianjiao.netty.NettyClient;

/**
 * Created by Administrator on 2016/7/20.
 */
public class PlayControlService extends IntentService{
    public PlayControlService() {
        super("PlayControlService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final String control = intent.getStringExtra("playControl");
        new Thread(){
            @Override
            public void run() {
                try {
                    NettyClient.sendMsg2Server(control+"+playControl");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
