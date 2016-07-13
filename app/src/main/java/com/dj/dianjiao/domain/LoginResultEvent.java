package com.dj.dianjiao.domain;

/**
 * Created by Administrator on 2016/7/8.
 */
public class LoginResultEvent extends BaseEvent{
/*返回登陆结果*/
    private int loginResult;

    public LoginResultEvent(int loginResult) {
        this.loginResult = loginResult;
    }

    public int getLoginResult() {
        return loginResult;
    }
}
