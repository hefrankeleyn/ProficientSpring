package com.hef.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @Date 2020/6/6
 * @Author lifei
 */
public class ResourceLocalApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        resourceBoundleMessageSourceTest(context);
//        reloadableResourceBoundleMessageSourceTest(context);

        Object[] params = {"John", new GregorianCalendar().getTime(), 1.0E3};
        String format1 = context.getMessage("greeting.welcome", params, Locale.US);
        String format2 = context.getMessage("greeting.welcome", params, Locale.CHINA);
        System.out.println(format1);
        System.out.println(format2);
    }

    /**
     * 定时刷新配置文件
     * @param context
     */
    private static void reloadableResourceBoundleMessageSourceTest(ApplicationContext context) {
        MessageSource myReloadResource = context.getBean("myReloadResource", MessageSource.class);
        Object[] params = {"John", new GregorianCalendar().getTime(), 1.0E3};
        try {
            for (int i = 0; i < 10; i++) {
                String format1 = myReloadResource.getMessage("greeting.welcome", params, Locale.US);
                String format2 = myReloadResource.getMessage("greeting.welcome", params, Locale.CHINA);
                System.out.println(format1);
                System.out.println(format2);
                // 模拟程序应用， 在次期间，更改资源文件
                System.out.println("间隔十秒...");
                Thread.currentThread().sleep(10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载配置文件
     *
     * @param context
     */
    private static void resourceBoundleMessageSourceTest(ApplicationContext context) {
        MessageSource myResource = context.getBean("myResource", MessageSource.class);
        Object[] params = {"John", new GregorianCalendar().getTime(), 1.0E3};
        String format1 = myResource.getMessage("greeting.welcome", params, Locale.US);
        String format2 = myResource.getMessage("greeting.welcome", params, Locale.CHINA);
        System.out.println(format1);
        System.out.println(format2);
    }
}
