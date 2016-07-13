package com.dj.dianjiao.domain;

import java.io.Serializable;

/**
 * Created by wmxxkj on 2016/6/30.
 */
public class ServerAddress implements Serializable{
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ServerAddress() {
    }

    public ServerAddress(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
