package com.dj.dianjiao.domain;

/**
 * Created by wmxxkj on 2016/6/23.
 */
public class JianshiItemAllEvent extends BaseEvent{
    private boolean isSelectAll;

    public boolean isSelectAll() {
        return isSelectAll;
    }

    public void setIsSelectAll(boolean isSelectAll) {
        this.isSelectAll = isSelectAll;
    }

    public JianshiItemAllEvent() {
    }

    public JianshiItemAllEvent(boolean isSelectAll) {
        this.isSelectAll = isSelectAll;
    }
}
