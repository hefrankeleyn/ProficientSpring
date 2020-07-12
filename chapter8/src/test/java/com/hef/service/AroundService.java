package com.hef.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lifei
 * @since 2020/7/12
 */
public class AroundService {

    @Test
    public void aroundService(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-aop-around.xml");
        Waiter waiter = context.getBean("waiter", Waiter.class);
        waiter.greetTo("xiaoMing");
    }
}
