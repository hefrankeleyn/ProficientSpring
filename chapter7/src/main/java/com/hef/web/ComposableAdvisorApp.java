package com.hef.web;

import com.hef.service.Waiter;
import com.hef.service.impl.WaiterDelegate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lifei
 * @since 2020/6/21
 */
public class ComposableAdvisorApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beansComposableAdvisor.xml");
        Waiter waiter = context.getBean("waiter05", Waiter.class);
        WaiterDelegate waiterDelegate = new WaiterDelegate();
        waiterDelegate.setWaiter(waiter);

        waiter.serveTo("Peter");
        waiter.greetTo("Peter");

        waiterDelegate.service("Peter");
    }
}
