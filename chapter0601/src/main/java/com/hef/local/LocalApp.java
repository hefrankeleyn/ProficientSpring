package com.hef.local;

import java.text.MessageFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Date 2020/6/4
 * @Author lifei
 */
public class LocalApp {

    public static void main(String[] args) {
//        messageTest();
        resourceBundle();
    }

    private static void resourceBundle(){
        ResourceBundle rb1 = ResourceBundle.getBundle("i18n/resource", Locale.US);
        ResourceBundle rb2 = ResourceBundle.getBundle("i18n/resource", Locale.CHINA);
        System.out.println("us: " + rb1.getString("greeting.afternoon"));
        System.out.println("cn: " + rb2.getString("greeting.afternoon"));

        Object[] params = {"John", new GregorianCalendar().getTime(), 1.0E3};
        String format1 = new MessageFormat(rb1.getString("greeting.welcome"),Locale.US).format(params);
        String format2 = new MessageFormat(rb2.getString("greeting.welcome"),Locale.CHINA).format(params);
        System.out.println(format1);
        System.out.println(format2);
    }

    private static void messageTest(){
        // 格式化信息
        String pattern1 = "{0}, 您好,{1} {2}";
        String pattern2 = "At {0},{1} {2}";

        Object[] params = {"John", new GregorianCalendar().getTime(), 1.0E3};

        String format1 = MessageFormat.format(pattern1, params);

        MessageFormat mf = new MessageFormat(pattern2, Locale.US);
        String format2 = mf.format(params);
        System.out.println(format1);
        System.out.println(format2);

    }
}
