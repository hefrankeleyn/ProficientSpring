package com.hef.service;

import com.hef.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Date 2020/5/21
 * @Author lifei
 */
public class AppConTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Car car = context.getBean("car1", Car.class);
        System.out.println(car.toString());
    }
}
