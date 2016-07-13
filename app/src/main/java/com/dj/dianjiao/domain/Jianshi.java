package com.dj.dianjiao.domain;

import java.io.Serializable;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class Jianshi implements Serializable{
    public static final int TYPE_01 = 0;
    public static final int TYPE_02 = 1;
    public static final int TYPE_03 = 2;
    private String name;
    private String jianquName;
    private int type;
    private boolean isChecked;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJianquName() {
        return jianquName;
    }

    public void setJianquName(String jianquName) {
        this.jianquName = jianquName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }


    public Jianshi() {
    }
    public Jianshi(String name, String jianquName, int type, boolean isChecked) {
        this.name = name;
        this.jianquName = jianquName;
        this.type = type;
        this.isChecked = isChecked;
    }
}
