package com.hef.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author lifei
 * @since 2020/8/12
 */
public class User {

    private String userId;
    private String username;
    private String email;

    public User() {}
    private User(Builder builder) {
        this.userId= builder.userId;
        this.username= builder.username;
        this.email = builder.email;
    }

    public static class Builder{
        private String userId;
        private String username;
        private String email;

        public Builder userId(String userId){this.userId=userId; return this;}
        public Builder username(String username){this.username=username; return this;}
        public Builder email(String email){this.email=email; return this;}

        public User builder(){
            return new User(this);
        }
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
