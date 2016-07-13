package com.dj.dianjiao.domain;

import java.io.Serializable;

/**
 * Created by wmxxkj on 2016/6/27.
 */
public class SendMedia implements Serializable {
    private String picture;
    private String name;
    private String country;
    private String type;
    private String time;
    private boolean isChecked;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public SendMedia() {
    }

    public SendMedia(String picture, String name, String country, String type, String time, boolean isChecked) {
        this.picture = picture;
        this.name = name;
        this.country = country;
        this.type = type;
        this.time = time;
        this.isChecked = isChecked;
    }
}
