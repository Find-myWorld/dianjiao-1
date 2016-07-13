package com.dj.dianjiao.domain;

import java.io.Serializable;

/**
 * Created by wmxxkj on 2016/6/30.
 */
public class RestartTime implements Serializable {
    private String name;
    private String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public RestartTime() {
    }

    public RestartTime(String name, String time) {
        this.name = name;
        this.time = time;
    }
}
