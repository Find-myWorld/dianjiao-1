package com.dj.dianjiao.manger;

import android.content.Context;

import com.dj.dianjiao.domain.SendMedia;
import com.dj.dianjiao.domain.SendPicture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class SendMediaManger {
    private static final String TAG = "SendMediaManger";
    private Context mContext;

    private static final boolean USE_LOCAL_DATA = true;

    private List<SendMedia> mSendMediaList;

    private CallBack mCallBack;

    public SendMediaManger(Context context) {

        mContext = context;
        mSendMediaList = new ArrayList<SendMedia>();
    }

    public interface CallBack {
        void onGetSendMediaListCompleted(List<SendMedia> sendMediaList);
    }

    public void setCallback(CallBack callback) {
        mCallBack = callback;
    }


    public void loadSendMediaList(){

        if(mSendMediaList != null){
            mSendMediaList.clear();
        }

        if (USE_LOCAL_DATA) {//  Use local data for debug
            for (int i = 1; i <= 3; i++) {
                SendMedia sendMedia = new SendMedia("IMG0"+i,"007-量子危机","美国","科幻动作","1小时23分",false);
                mSendMediaList.add(sendMedia);
            }
            setSendMediaList();
        } else {
            //do online
            setSendMediaList();
        }
    }

    private void setSendMediaList(){

        //  get online news info complete,do refresh views here

        if (mCallBack != null){
            mCallBack.onGetSendMediaListCompleted(mSendMediaList);
        }
    }
}
