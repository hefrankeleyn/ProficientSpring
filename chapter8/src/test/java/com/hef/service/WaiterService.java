package com.hef.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lifei
 * @since 2020/7/6
 */
public class WaiterService {


    @Test
    public void waiterTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Waiter waiter = context.getBean("waiter", Waiter.class);
        waiter.greetTo("XiaoMing");
    }

    @Test
    public void waiterTest02(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-aop.xml");
        Waiter waiter = context.getBean("waiter", Waiter.class);
        waiter.greetTo("XiaoHong");
    }

    @Test
    public void waiterSellTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-aop-declare.xml");
        Waiter waiter = (Waiter) context.getBean("waiter");
        waiter.greetTo("XiaoHong");
        Seller seller = (Seller) waiter;
        seller.sell("apple");
    }
}
