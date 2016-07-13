package com.dj.dianjiao.domain;

/**
 * Created by wmxxkj on 2016/6/23.
 */
public class SendMediaItemClickEvent extends BaseEvent{
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public SendMediaItemClickEvent() {
    }

    public SendMediaItemClickEvent(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
