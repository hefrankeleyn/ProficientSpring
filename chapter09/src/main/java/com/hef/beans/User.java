package com.hef.beans;

/**
 * @author lifei
 * @since 2020/7/12
 */
public class User {

    private String userName;
    private int credits;

    public User() {}

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public boolean isVipMember(String userName){
        if ("tom".equals(userName)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", credits=" + credits +
                '}';
    }
}
