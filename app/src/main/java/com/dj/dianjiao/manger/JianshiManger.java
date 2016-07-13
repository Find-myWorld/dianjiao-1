package com.dj.dianjiao.manger;

import android.content.Context;

import com.dj.dianjiao.domain.Jianqu;
import com.dj.dianjiao.domain.Jianshi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class JianshiManger {
    private static final String TAG = "JianshiManger";
    private Context mContext;

    private static final boolean USE_LOCAL_DATA = true;

    private List<Jianshi> mJianshiList;

    private CallBack mCallBack;

    public JianshiManger(Context context) {

        mContext = context;
        mJianshiList = new ArrayList<Jianshi>();
    }

    public interface CallBack {
        void onGetJianshiListCompleted(List<Jianshi> jianquList);
    }

    public void setCallback(CallBack callback) {
        mCallBack = callback;
    }


    public void loadJianshiList(String jianquName){

        if(mJianshiList != null){
            mJianshiList.clear();
        }

        if (USE_LOCAL_DATA) {//  Use local data for debug
            for (int i = 1; i <= 39; i++) {
                Jianshi jianshi = new Jianshi("监室0"+i,jianquName,i%3,false);
                mJianshiList.add(jianshi);
            }
            setJianshiList();
        } else {
            //do online
            setJianshiList();
        }
    }

    private void setJianshiList(){

        //  get online news info complete,do refresh views here

        if (mCallBack != null){
            mCallBack.onGetJianshiListCompleted(mJianshiList);
        }
    }
}
