package com.hef.web.autoproxy;

import com.hef.service.Seller;
import com.hef.service.Waiter;
import com.hef.service.impl.ProblemSeller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lifei
 * @since 2020/6/21
 */
public class ProblemApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beansAdvisorProblem.xml");
        ProblemSeller sellerTarget = context.getBean("sellerTarget", ProblemSeller.class);

        sellerTarget.greetTo("tom");
    }
}
