package com.dj.dianjiao.domain;

/**
 * Created by wmxxkj on 2016/6/23.
 */
public class SendPictureItemClickEvent extends BaseEvent{
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public SendPictureItemClickEvent() {
    }

    public SendPictureItemClickEvent(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
