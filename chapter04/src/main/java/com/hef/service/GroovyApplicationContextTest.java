package com.hef.service;

import com.hef.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

/**
 * @Date 2020/5/21
 * @Author lifei
 */
public class GroovyApplicationContextTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new GenericGroovyApplicationContext("classpath:groovy-beans.groovy");
        Car car = ctx.getBean("car", Car.class);
        System.out.println(car.toString());
    }
}
