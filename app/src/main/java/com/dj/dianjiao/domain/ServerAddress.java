package com.dj.dianjiao.domain;

import java.io.Serializable;

/**
 * Created by wmxxkj on 2016/6/30.
 */
public class ServerAddress implements Serializable{
    private String name;
    private String address;
    private String port;

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

    public ServerAddress(String name, String address, String port) {
        this.name = name;
        this.address = address;
        this.port = port;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "ServerAddress{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
