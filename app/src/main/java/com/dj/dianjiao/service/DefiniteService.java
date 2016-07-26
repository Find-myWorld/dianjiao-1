package com.dj.dianjiao.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;

import com.dj.dianjiao.domain.DefiniteTimeEvent;
import com.dj.dianjiao.domain.Plan;
import com.dj.dianjiao.receive.DefiniteReceive;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/26.
 */
public class DefiniteService extends IntentService {
    private List<Plan> timeList;

    public DefiniteService() {
        super("DefiniteService");
    }

    @Subscribe
    public void onEventMainThread(DefiniteTimeEvent event){
        System.err.println("service收到定时任务");
        timeList = event.getTimeList();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent intent1 = new Intent(getApplicationContext(), DefiniteReceive.class);
        intent1.setAction("android.intent.action.ALARM_RECEIVER");

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent1, 0);
        manager.set(AlarmManager.RTC_WAKEUP,2000,pendingIntent);

       /* for (Plan plan:timeList) {

            String temp[] = plan.getStartTime().split(":");
            Long startTime = Long.parseLong(temp[0])*60*60*1000 + Long.parseLong(temp[1])*60*1000 + Long.parseLong(temp[2])*1000;
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            Calendar useTime = Calendar.getInstance();
            useTime.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_YEAR),0,0,0);
            int week = getWeek(calendar);
            boolean[] round = plan.getRound();
            List<Integer> alarmsList = new ArrayList<>();
            for (int i = 0;i <round.length;i++) {
                if (round[i]){
                    alarmsList.add(i+1);
                }
            }
            getMinWeek(alarmsList,week);
            for (Integer i:alarmsList) {
                if(week==i){
                    if(calendar.getTimeInMillis()< (useTime.getTimeInMillis()+startTime)){
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, (int) Math.round(Math.random() * Integer.MAX_VALUE), intent1, 0);
                        manager.set(AlarmManager.RTC_WAKEUP,useTime.getTimeInMillis()+startTime,pendingIntent);
                    }
                }else if(week)
            }
            calendar.add(Calendar.SECOND,startTime);
        }
       // PendingIntent.getBroadcast()*/
    }

    private int getMinWeek(List<Integer> alarmsList, int week) {
        int temp;
        for (Integer i: alarmsList) {
        }
        return 0;
    }

    private int getWeek(Calendar calendar) {
        int[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

}
