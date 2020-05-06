package com.hef.dao;

import com.hef.domain.User;

public interface UserDao {

    int getMatchCount(String userName,String password);

    User findUserByUsername(String userName);

    void updateLoginInfo(User user);
}
