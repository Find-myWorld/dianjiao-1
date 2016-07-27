package com.dj.dianjiao.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2016/7/26.
 */
public class DefiniteReceive extends BroadcastReceiver {

    private String action;

    private DataBean data;

    @Override
    public void onReceive(Context context, Intent intent) {
        System.err.println("收到定时命令");
    }


    public static class DataBean {
        private String serverIp;
        private String port;
        private String channel;
        private String userName;
        private String passWord;

    }
}
