package com.hef.service;

import com.hef.injectfun.Boss1;
import com.hef.injectfun.Car;
import com.hef.injectfun.MagicBoss;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Date 2020/5/24
 * @Author lifei
 */
public class MagicApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beansMethodInject.xml");
        MagicBoss magicBoss = context.getBean("magicBoss", MagicBoss.class);
        System.out.println(magicBoss.getCar());

        Boss1 boss1 = context.getBean("boss1", Boss1.class);
        boss1.getCar().carDesc();

        Car car05 = context.getBean("car05", Car.class);
        car05.carDesc();
        Car car06 = context.getBean("car06", Car.class);
        car06.carDesc();
    }


}
