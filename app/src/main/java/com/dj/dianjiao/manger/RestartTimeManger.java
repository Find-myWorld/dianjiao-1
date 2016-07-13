package com.dj.dianjiao.manger;

import android.content.Context;

import com.dj.dianjiao.domain.RestartTime;
import com.dj.dianjiao.domain.SendPicture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class RestartTimeManger {
    private static final String TAG = "RestartTimeManger";
    private Context mContext;

    private static final boolean USE_LOCAL_DATA = true;

    private List<RestartTime> mRestartTimeList;

    private CallBack mCallBack;

    public RestartTimeManger(Context context) {

        mContext = context;
        mRestartTimeList = new ArrayList<RestartTime>();
    }

    public interface CallBack {
        void onGetRestartTimeListCompleted(List<RestartTime> restartTimeList);
    }

    public void setCallback(CallBack callback) {
        mCallBack = callback;
    }


    public void loadRestartTimeListList(){

        if(mRestartTimeList != null){
            mRestartTimeList.clear();
        }

        if (USE_LOCAL_DATA) {//  Use local data for debug
            for (int i = 1; i <= 2; i++) {
                RestartTime restartTime = new RestartTime("时间"+i,"08:30:00");
                mRestartTimeList.add(restartTime);
            }
            setRestartTimeList();
        } else {
            //do online
            setRestartTimeList();
        }
    }

    private void setRestartTimeList(){

        //  get online news info complete,do refresh views here

        if (mCallBack != null){
            mCallBack.onGetRestartTimeListCompleted(mRestartTimeList);
        }
    }
}
