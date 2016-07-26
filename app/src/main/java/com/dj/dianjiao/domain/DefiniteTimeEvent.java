package com.dj.dianjiao.domain;

import java.util.List;

/**
 * Created by Administrator on 2016/7/26.
 */
public class DefiniteTimeEvent {
    private List<Plan> timeList;

    public List<Plan> getTimeList() {
        return timeList;
    }

    public DefiniteTimeEvent(List<Plan> timeList) {
        this.timeList = timeList;
    }
}
