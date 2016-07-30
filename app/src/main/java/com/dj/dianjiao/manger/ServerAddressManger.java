package com.dj.dianjiao.manger;

import android.content.Context;
import android.util.Log;

import com.dj.dianjiao.domain.Jianqu;
import com.dj.dianjiao.domain.ServerAddress;
import com.dj.dianjiao.utils.SPUtils;

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
    private ServerAddress serverAddressm;

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
            /*拿到对应的sp保存集合的长度与数据*/
            int serverIpSize = (int) SPUtils.get(mContext, "serverIpSize", 1);
            ServerAddress serverAddress = null;
            for (int i = 0;i < serverIpSize ; i++){
                Log.d("tag","serverIp"+i);
                String serverIp = (String) SPUtils.get(mContext, "serverIp"+i, "192.168.001.00");
                String serverPort = (String) SPUtils.get(mContext, "serverPort"+i, "0000");
                serverAddress =  new ServerAddress("地址：",serverIp,serverPort);
            }

            mServerAddressList.add(serverAddress);
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


                ServerAddress serverAddress = new ServerAddress("地址：","192.168.001.00","0000");
                mServerAddressList.add(serverAddress);

            setStreamMediaAddressList();
        } else {
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
