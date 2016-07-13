package com.dj.dianjiao.manger;

import android.content.Context;
import android.util.Log;

import com.dj.dianjiao.domain.Jianqu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class JianquManger {
    private static final String TAG = "JianquManger";
    private Context mContext;

    private static final boolean USE_LOCAL_DATA = true;

    private List<Jianqu> mJianquList;

    private CallBack mCallBack;

    public JianquManger(Context context) {

        mContext = context;
        mJianquList = new ArrayList<Jianqu>();
    }

    public interface CallBack {
        void onGetJianquListCompleted(List<Jianqu> jianquList);
    }

    public void setCallback(CallBack callback) {
        mCallBack = callback;
    }


    public void loadJianquList(){

        if(mJianquList != null){
            mJianquList.clear();
        }

        if (USE_LOCAL_DATA) {//  Use local data for debug
            for (int i = 1; i <= 4; i++) {
                Jianqu jianqu = new Jianqu("监区0"+i,i==1?true:false);
                mJianquList.add(jianqu);
            }
            setJianquList();
        } else {
            //do online
            setJianquList();
        }
    }

    private void setJianquList(){

        //  get online news info complete,do refresh views here

        if (mCallBack != null){
            mCallBack.onGetJianquListCompleted(mJianquList);
        }
    }
}
