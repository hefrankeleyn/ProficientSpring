package com.hef.services;

import com.hef.beans.LoginLog;
import com.hef.beans.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Instant;
import java.util.Date;

/**
 * @author lifei
 * @since 2020/7/26
 */
public class XStreamSample {

    private static XStream xStream;

    static {
        // 创建XStream 实例，并指定一个XML解析器
        xStream = new XStream(new DomDriver());
    }


    public static User getUser(){
        LoginLog log1 = new LoginLog();
        log1.setIp("192.168.1.91");
        log1.setLoginDate(new Date());
        User user = new User();
        user.setUserId(1);
        user.setUserName("xstream");
        user.addLog(log1);
        return user;
    }

    /** Java 对象 转换为XML */
    public static void objectToXML() throws FileNotFoundException {
        User user = getUser();
        FileOutputStream outputStream = new FileOutputStream("XStreamSample.xml");
        xStream.toXML(user, outputStream);
    }

    public static void XMLToObject() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("XStreamSample.xml");
        User user = (User) xStream.fromXML(inputStream);
        for (LoginLog log : user.getLogs()) {
            System.out.println("访问IP： " + log.getIp());
            System.out.println("访问时间： " + log.getLoginDate());
        }
    }
}
