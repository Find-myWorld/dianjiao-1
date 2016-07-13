package com.dj.dianjiao.domain;

import java.io.Serializable;

/**
 * Created by wmxxkj on 2016/6/27.
 */
public class SendPicture implements Serializable{
    private String picture;
    private String name;
    private boolean isCheck;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SendPicture() {
    }

    public SendPicture(String picture, String name, boolean isCheck) {
        this.picture = picture;
        this.name = name;
        this.isCheck = isCheck;
    }
}
