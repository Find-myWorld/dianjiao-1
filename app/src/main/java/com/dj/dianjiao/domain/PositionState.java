package com.dj.dianjiao.domain;

import java.util.List;

/*
 * 用来传递频道的事件类型
 * Created by Administrator on 2016/7/30.
 */
public class PositionState extends BaseEvent{
    private List<String> position;
    private int state;
    private String movieMsg;
    private int channel;

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public PositionState(List<String> position, int state, String movieMsg,int channel) {
        this.position = position;
        this.state = state;
        this.movieMsg = movieMsg;
        this.channel = channel;
    }

    public List<String> getPosition() {
        return position;
    }

    public void setPosition(List<String> position) {
        this.position = position;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMoviemsg() {
        return movieMsg;
    }

    public void setMoviemsg(String movieMsg) {
        this.movieMsg = movieMsg;
    }
}
