package com.hef.service;

import com.hef.Car;
import com.hef.config.Beans;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Date 2020/5/21
 * @Author lifei
 */
public class AnnotationApplicationContextTest {


    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
        Car car = ctx.getBean("car", Car.class);
        System.out.println(car.toString());
    }
}
