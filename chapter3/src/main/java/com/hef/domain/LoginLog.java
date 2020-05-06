package com.hef.domain;

import java.time.Instant;

/**
 * @Date 2020/4/21
 * @Author lifei
 */
public class LoginLog {

    private int loginLogId;
    private int userId;
    private String ip;
    private Instant loginDate;

    public int getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(int loginLogId) {
        this.loginLogId = loginLogId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Instant getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Instant loginDate) {
        this.loginDate = loginDate;
    }
}
