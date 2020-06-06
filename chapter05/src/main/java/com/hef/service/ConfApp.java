package com.hef.service;

import com.hef.beans.User;
import com.hef.conf.AppConf;
import com.hef.conf.UserConf;
import com.hef.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Date 2020/5/24
 * @Author lifei
 */
public class ConfApp {

    public static void main(String[] args) {
//        appConfRun();
//        userConfRun();

        userConfRun();
    }


    public static void userConfRun(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConf.class);
        context.register(UserConf.class);
        context.refresh();

        User user = context.getBean(User.class);
        user.sayUser();
    }

    public static void appConfRun(){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConf.class);
        UserDao userDao = context.getBean(UserDao.class);
        userDao.runUser();
    }
}
