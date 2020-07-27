package com.hef.services;

import com.hef.beans.Book;
import com.hef.beans.LoginLog;
import com.hef.beans.User;
import com.hef.beans.User02;
import com.hef.converter.DateConverter;
import com.hef.converter.InstanceConverter;
import com.hef.utils.MyInstantUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * @author lifei
 * @since 2020/7/26
 */
public class XStreamUser02 {

    private static XStream xStream;

    static {
        // 创建XStream 实例，并指定一个XML解析器
        xStream = new XStream(new DomDriver());

        /** 自动加载注解 */
        xStream.autodetectAnnotations(true);

    }


    public static User02 getUser02(){
        Book book = new Book();
        book.setBookId(0);
        book.setBookName("《book01》");
        book.setUserId(1);
        User02 user = new User02();
        user.setUserId(1);
        user.setUserName("xstream");
        user.setBirthday(Instant.now());
        user.setBookList(Arrays.asList(book));
        return user;
    }

    /** Java 对象 转换为XML */
    public static void objectToXML() throws FileNotFoundException {
        User02 user = getUser02();
        FileOutputStream outputStream = new FileOutputStream("XStreamUser02.xml");
        xStream.toXML(user, outputStream);
    }

    public static void XMLToObject() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("XStreamUser02.xml");
        User02 user = (User02) xStream.fromXML(inputStream);
        System.out.println(user);
        System.out.println(user.getBookList());
    }

    public static void main(String[] args) {
        try {
            objectToXML();
            XMLToObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
