package com.hef.conf;

import com.mysql.cj.jdbc.Driver;

/**
 * @Date 2020/5/31
 * @Author lifei
 */
public class MyConfig {

    private String username;
    private String url;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MyConfig{" +
                "username='" + username + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
