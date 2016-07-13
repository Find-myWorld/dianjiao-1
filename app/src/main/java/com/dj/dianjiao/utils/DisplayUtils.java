package com.dj.dianjiao.utils;

import android.app.Activity;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/7/13.
 */
public class DisplayUtils {
    public static void getWindowSize(Activity a) {
        WindowManager manager = a.getWindowManager();
        Display display = manager.getDefaultDisplay();
        int displayWidth = display.getWidth();
        int displayHeight = display.getHeight();
        System.out.println(displayWidth+"-------------"+ displayHeight);
    }

}
