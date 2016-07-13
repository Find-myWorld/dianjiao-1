package com.dj.dianjiao.manger;

import android.content.Context;

import com.dj.dianjiao.domain.Jianqu;
import com.dj.dianjiao.domain.SendPicture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class SendPictureManger {
    private static final String TAG = "SendPictureManger";
    private Context mContext;

    private static final boolean USE_LOCAL_DATA = true;

    private List<SendPicture> mSendPictureList;

    private CallBack mCallBack;

    public SendPictureManger(Context context) {

        mContext = context;
        mSendPictureList = new ArrayList<SendPicture>();
    }

    public interface CallBack {
        void onGetSendPictureListCompleted(List<SendPicture> sendPictureList);
    }

    public void setCallback(CallBack callback) {
        mCallBack = callback;
    }


    public void loadSendPictureListList(){

        if(mSendPictureList != null){
            mSendPictureList.clear();
        }

        if (USE_LOCAL_DATA) {//  Use local data for debug
            for (int i = 1; i <= 9; i++) {
                SendPicture sendPicture = new SendPicture("IMG0"+i,"IMG0"+i,false);
                mSendPictureList.add(sendPicture);
            }
            setSendPictureList();
        } else {
            //do online
            setSendPictureList();
        }
    }

    private void setSendPictureList(){

        //  get online news info complete,do refresh views here

        if (mCallBack != null){
            mCallBack.onGetSendPictureListCompleted(mSendPictureList);
        }
    }
}
