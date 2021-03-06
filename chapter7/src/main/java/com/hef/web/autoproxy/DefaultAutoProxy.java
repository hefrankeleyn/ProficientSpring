package com.hef.web.autoproxy;

import com.hef.service.Seller;
import com.hef.service.Waiter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lifei
 * @since 2020/6/21
 */
public class DefaultAutoProxy {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beansDefaultAdvisorAutoProxyAdvisor.xml");
        Waiter waiterTarget = context.getBean("waiterTarget", Waiter.class);
        Seller sellerTarget = context.getBean("sellerTarget", Seller.class);

        waiterTarget.greetTo("xiaoMing");
        waiterTarget.serveTo("xiaoMing");
        sellerTarget.greetTo("Dream");
    }
}
