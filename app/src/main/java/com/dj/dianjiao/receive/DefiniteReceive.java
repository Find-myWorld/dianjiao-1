package com.dj.dianjiao.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2016/7/26.
 */
public class DefiniteReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.err.println("收到定时命令");
    }
}
