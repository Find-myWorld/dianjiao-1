package com.dj.dianjiao.manger;

import android.content.Context;

import com.dj.dianjiao.domain.Jianqu;
import com.dj.dianjiao.domain.ServerAddress;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class ServerAddressManger {
    private static final String TAG = "ServerAddressManger";
    private Context mContext;

    private static final boolean USE_LOCAL_DATA = true;

    private List<ServerAddress> mServerAddressList;

    private CallBack mCallBack;

    public ServerAddressManger(Context context) {

        mContext = context;
        mServerAddressList = new ArrayList<ServerAddress>();
    }

    public interface CallBack {
        void onGetServerAddressListCompleted(List<ServerAddress> serverAddressList);
        void onGetStreamMediaAddressListCompleted(List<ServerAddress> streamMediaAddressList);
    }

    public void setCallback(CallBack callback) {
        mCallBack = callback;
    }


    public void loadServerAddressList(){

        if(mServerAddressList != null){
            mServerAddressList.clear();
        }

        if (USE_LOCAL_DATA) {//  Use local data for debug
            for (int i = 1; i <= 9; i++) {
                ServerAddress serverAddress = new ServerAddress("地址0"+i+"：","192.168.001.00"+i);
                mServerAddressList.add(serverAddress);
            }
            setServerAddressList();
        } else {
            //do online
            setServerAddressList();
        }
    }

    public void loadStreamMediaAddressList(){

        if(mServerAddressList != null){
            mServerAddressList.clear();
        }

        if (USE_LOCAL_DATA) {//  Use local data for debug
            for (int i = 9; i >= 1; i--) {
                ServerAddress serverAddress = new ServerAddress("地址0"+i+"：","192.168.001.00"+i);
                mServerAddressList.add(serverAddress);
            }
            setStreamMediaAddressList();
        } else {
            //do online
            setStreamMediaAddressList();
        }
    }

    private void setServerAddressList(){

        //  get online news info complete,do refresh views here

        if (mCallBack != null){
            mCallBack.onGetServerAddressListCompleted(mServerAddressList);
        }
    }
    private void setStreamMediaAddressList(){

        //  get online news info complete,do refresh views here

        if (mCallBack != null){
            mCallBack.onGetServerAddressListCompleted(mServerAddressList);
        }
    }

}
