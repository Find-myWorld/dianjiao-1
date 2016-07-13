package com.dj.dianjiao.manger;

import android.content.Context;

import com.dj.dianjiao.domain.Jianqu;
import com.dj.dianjiao.domain.OnlineMonitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class OnlineMonitorManger {
    private static final String TAG = "OnlineMonitorManger";
    private Context mContext;

    private static final boolean USE_LOCAL_DATA = true;

    private List<OnlineMonitor> mOnlineMonitorList;

    private CallBack mCallBack;

    public OnlineMonitorManger(Context context) {

        mContext = context;
        mOnlineMonitorList = new ArrayList<OnlineMonitor>();
    }

    public interface CallBack {
        void onGetOnlineMonitorListCompleted(List<OnlineMonitor> onlineMonitorList);//从网络获得完数据后，刷新ui。
    }

    public void setCallback(CallBack callback) {
        mCallBack = callback;
    }


    public void loadOnlineMonitorList(){

        if(mOnlineMonitorList != null){
            mOnlineMonitorList.clear();
        }
        //TODO 从网络获取具体的在线监室数量  异步。
        if (USE_LOCAL_DATA) {//  Use local data for debug
            for (int i = 1; i <= 9; i++) {
                OnlineMonitor onlineMonitor = new OnlineMonitor("00"+i,"监室00"+i,"正常");
                mOnlineMonitorList.add(onlineMonitor);
            }
            mOnlineMonitorList.add(new OnlineMonitor("010","监室010","网络异常"));
            setOnlineMonitorList();//刷新ui？
        } else {
            //do online
            setOnlineMonitorList();
        }
    }

    private void setOnlineMonitorList(){

        //  get online news info complete,do refresh views here
        //

        if (mCallBack != null){
            mCallBack.onGetOnlineMonitorListCompleted(mOnlineMonitorList);
        }
    }
}
