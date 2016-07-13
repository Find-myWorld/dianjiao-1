package com.dj.dianjiao.domain;

import java.io.Serializable;

/**
 * Created by wmxxkj on 2016/6/22.
 */
public class Jianqu implements Serializable{
    private String name;
    private boolean ischecked;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIschecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }

    public Jianqu() {
    }

    public Jianqu(String name, boolean ischecked) {
        this.name = name;
        this.ischecked = ischecked;
    }
}
