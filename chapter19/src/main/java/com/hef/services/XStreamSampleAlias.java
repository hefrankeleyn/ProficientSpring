package com.hef.services;

import com.hef.beans.LoginLog;
import com.hef.beans.User;
import com.hef.converter.DateConverter;
import com.hef.converter.InstanceConverter;
import com.hef.utils.MyInstantUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

/**
 * @author lifei
 * @since 2020/7/26
 */
public class XStreamSampleAlias {

    private static XStream xStream;

    static {
        // 创建XStream 实例，并指定一个XML解析器
        xStream = new XStream(new DomDriver());

        // 使用别名
        /** 设置类别名，默认为当前类名称加上包名 */
        xStream.alias("loginLog", LoginLog.class);
        xStream.alias("user", User.class);
        /** 设置类成员别名 */
        xStream.aliasField("id", User.class, "userId");
        /** 把LoginLog的 userId 属性是为XML属性，默认为XML的元素 */
        xStream.aliasAttribute(LoginLog.class, "userId", "id");
        xStream.useAttributeFor(LoginLog.class, "userId");

        /** 去掉集合类型生成XML的父节点，即忽略XML中<logs></logs>标记 */
        xStream.addImplicitCollection(User.class, "logs");

        /** 注册自定义转换器 */
        xStream.registerConverter(new DateConverter(Locale.SIMPLIFIED_CHINESE));
        /** 自定义转换器 */
        xStream.registerConverter(new InstanceConverter());
    }


    public static User getUser(){
        LoginLog log1 = new LoginLog();
        log1.setIp("192.168.1.91");
        log1.setLoginInstant(Instant.now());
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
        FileOutputStream outputStream = new FileOutputStream("XStreamSampleWithAlias.xml");
        xStream.toXML(user, outputStream);
    }

    public static void XMLToObject() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("XStreamSampleWithAlias.xml");
        User user = (User) xStream.fromXML(inputStream);
        for (LoginLog log : user.getLogs()) {
            System.out.println("访问IP： " + log.getIp());
            System.out.println("访问时间： " + log.getLoginDate());
            System.out.println("Instant :" + MyInstantUtils.instantToFormatStr(MyInstantUtils.FORMAT01,log.getLoginInstant()));
        }
        System.out.println(user);
    }
}
