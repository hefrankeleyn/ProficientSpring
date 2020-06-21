package com.hef.web;

import com.hef.service.Waiter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lifei
 * @since 2020/6/21
 */
public class DynamicAdvisorApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beansAdvisor.xml");
        Waiter waiter2 = context.getBean("waiter2", Waiter.class);
        waiter2.greetTo("Peter");
        waiter2.serveTo("Peter");
        waiter2.serveTo("John");
        waiter2.greetTo("Peter");
    }
}
