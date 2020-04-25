package com.hef.service;


import com.hef.domain.User;

/**
 * @Date 2020/4/21
 * @Author lifei
 */
public interface UserService {

    boolean hasMatchUser(String userName, String password);

    User findUserByUserName(String userName);

    void loginSuccess(User user);
}
