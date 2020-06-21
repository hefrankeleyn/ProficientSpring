package com.hef.web;

import com.hef.service.Waiter;
import com.hef.service.impl.WaiterDelegate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lifei
 * @since 2020/6/21
 */
public class FollowAdvisorApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beansFollowAdvisor.xml");
        Waiter waiter03 = context.getBean("waiter03", Waiter.class);
        WaiterDelegate waiterDelegate = new WaiterDelegate();
        waiterDelegate.setWaiter(waiter03);

        waiter03.serveTo("Peter");
        waiter03.greetTo("Peter");

        waiterDelegate.service("Peter");
    }
}
