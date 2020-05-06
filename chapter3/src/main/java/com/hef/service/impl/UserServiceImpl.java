package com.hef.service.impl;

import com.hef.dao.LoginLogDao;
import com.hef.dao.UserDao;
import com.hef.domain.LoginLog;
import com.hef.domain.User;
import com.hef.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Date 2020/4/21
 * @Author lifei
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private LoginLogDao loginLogDao;

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean hasMatchUser(String userName, String password) {
        int matchCount = userDao.getMatchCount(userName, password);
        return matchCount>0;
    }

    @Override
    public User findUserByUserName(String userName) {
        return userDao.findUserByUsername(userName);
    }

    @Override
    @Transactional
    public void loginSuccess(User user) {
        user.setCredits(5 + user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }
}
