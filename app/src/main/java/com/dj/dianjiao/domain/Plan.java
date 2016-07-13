package com.dj.dianjiao.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wmxxkj on 2016/6/28.
 */
public class Plan implements Serializable{
    public static final String ACTION[] = {"开机","关机"};
    private String taskName;
    private String startTime;
    private String overTime;
    private boolean[] round;
    private String action;
    private List<String> fileList;
    private String remark;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getOverTime() {
        return overTime;
    }

    public void setOverTime(String overTime) {
        this.overTime = overTime;
    }

    public boolean[] getRound() {
        return round;
    }

    public void setRound(boolean[] round) {
        this.round = round;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Plan() {
    }

    public Plan(String taskName, String startTime, String overTime, boolean[] round, String action, List<String> fileList, String remark) {
        this.taskName = taskName;
        this.startTime = startTime;
        this.overTime = overTime;
        this.round = round;
        this.action = action;
        this.fileList = fileList;
        this.remark = remark;
    }

    public static Plan getEmptyInstance(){
        return new Plan("","无","无",new boolean[]{true,true,true,true,true,false,false},Plan.ACTION[0],new ArrayList<String>(),"");
    }
}
