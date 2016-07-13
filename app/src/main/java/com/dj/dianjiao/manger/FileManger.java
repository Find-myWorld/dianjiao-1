package com.dj.dianjiao.manger;

import android.content.Context;

import com.dj.dianjiao.domain.Plan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class FileManger {
    private static final String TAG = "FileManger";
    private Context mContext;

    private static final boolean USE_LOCAL_DATA = true;

    private List<String> mFileList;

    private CallBack mCallBack;

    public FileManger(Context context) {

        mContext = context;
        mFileList = new ArrayList<String>();
    }

    public interface CallBack {
        void onGetFileListCompleted(List<String> fileList);
    }

    public void setCallback(CallBack callback) {
        mCallBack = callback;
    }


    public void loadFileList(){

        if(mFileList != null){
            mFileList.clear();
        }

        if (USE_LOCAL_DATA) {//  Use local data for debug
            for (int i = 1; i <= 9; i++) {
                mFileList.add("文件0"+i+".mp4");
            }
            setFileList();
        } else {
            //do online
            setFileList();
        }
    }

    private void setFileList(){

        //  get online news info complete,do refresh views here

        if (mCallBack != null){
            mCallBack.onGetFileListCompleted(mFileList);
        }
    }
}
