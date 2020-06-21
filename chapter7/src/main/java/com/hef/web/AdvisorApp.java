package com.hef.web;

import com.hef.service.Seller;
import com.hef.service.Waiter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lifei
 * @since 2020/6/14
 */
public class AdvisorApp {
    public static void main(String[] args) {
//        beforeAdvisorTest();
        regexpAdvisorTest();
    }

    /**
     * 发现增强只在 Waiter 上生效
     */
    private static void beforeAdvisorTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beansAdvisor.xml");
        // 发现增强只在 Waiter 上生效
        Waiter waiter = context.getBean("waiter", Waiter.class);
        waiter.greetTo("dream...");
        Seller seller = context.getBean("seller", Seller.class);
        seller.greetTo("seller run..");
    }

    private static void regexpAdvisorTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beansAdvisor.xml");
        // 发现增强只在 Waiter 上生效
        Waiter waiter = context.getBean("waiterRegexp", Waiter.class);
        waiter.greetTo("dream...");
        Seller seller = context.getBean("sellerRegexp", Seller.class);
        seller.greetTo("seller run..");
    }
}
