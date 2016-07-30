package com.dj.dianjiao.domain;

/**
 * Created by wmxxkj on 2016/6/23.
 */
public class   JianshiItemClickEvent extends BaseEvent {
    private boolean isCheck;
    private String name;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JianshiItemClickEvent (){}

    public JianshiItemClickEvent(String name, boolean isCheck,int position) {
        this.isCheck = isCheck;
        this.name = name;
        this.position = position;
    }
}
