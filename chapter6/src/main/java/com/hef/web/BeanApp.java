package com.hef.web;

import com.hef.beans.Boss;
import com.hef.conf.MyConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @Date 2020/5/31
 * @Author lifei
 */
public class BeanApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        MyConfig myConfig = context.getBean("myConfig", MyConfig.class);
        System.out.println(myConfig);
    }

    private static void customBoss(ApplicationContext context){
        Boss boss01 = context.getBean("boss01", Boss.class);
        boss01.bossDesc();

        Boss bossCustom = context.getBean("bossCustom", Boss.class);
        bossCustom.bossDesc();
    }
}
