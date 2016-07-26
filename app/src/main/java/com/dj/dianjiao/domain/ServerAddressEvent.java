package com.dj.dianjiao.domain;

/**
 * Created by Administrator on 2016/7/26.
 */
public class ServerAddressEvent {
    private boolean isChecked;
    private String position;

    public ServerAddressEvent(boolean isChecked, String position) {
        this.isChecked = isChecked;
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
