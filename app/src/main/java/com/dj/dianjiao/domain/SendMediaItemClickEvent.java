package com.dj.dianjiao.domain;

/**
 * Created by wmxxkj on 2016/6/23.
 */
public class SendMediaItemClickEvent extends BaseEvent{
    private String movieName;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public SendMediaItemClickEvent() {
    }

    public SendMediaItemClickEvent(boolean isChecked,String movieName) {
        this.isChecked = isChecked;
        this.movieName = movieName;
    }
}
