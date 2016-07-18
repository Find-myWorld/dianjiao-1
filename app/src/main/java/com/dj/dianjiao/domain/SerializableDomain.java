package com.dj.dianjiao.domain;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class SerializableDomain implements Serializable{//
    public SerializableDomain(){}
    private List<Jianshi> jianshiList;

    public List<Jianshi> getJianshiList() {
        return jianshiList;
    }

    public void setJianshiList(List<Jianshi> jianshiList) {
        this.jianshiList = jianshiList;
    }

    public SerializableDomain(List<Jianshi> jianshiList) {
        this.jianshiList = new ArrayList<Jianshi>();
        this.jianshiList.addAll(jianshiList);
    }


    private Context mContext;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public SerializableDomain(Context mContext) {
        this.mContext = mContext;
    }
}
