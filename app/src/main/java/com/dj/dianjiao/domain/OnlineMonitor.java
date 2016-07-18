package com.dj.dianjiao.domain;

import java.io.Serializable;

/**
 * Created by wmxxkj on 2016/6/30.
 */
public class OnlineMonitor implements Serializable{//记录在线监视的人员信息
    private String id;
    private String productName;
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public OnlineMonitor() {
    }

    public OnlineMonitor(String id, String productName, String state) {
        this.id = id;
        this.productName = productName;
        this.state = state;
    }
}
