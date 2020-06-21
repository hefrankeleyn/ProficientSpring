package com.hef.web;

import com.hef.service.ForumService;
import com.hef.service.MonitorAble;
import com.hef.service.Waiter;
import com.hef.service.impl.WaiterDelegate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lifei
 * @since 2020/6/21
 */
public class IntroductionAdvisorApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beansIntroductionAdvisor.xml");
        ForumService forumService = context.getBean("forumService", ForumService.class);

        forumService.removeForum(23);
        forumService.removeTopic(23);

        MonitorAble monitorAble = (MonitorAble) forumService;
        monitorAble.setMonitorActive(true);
        forumService.removeForum(23);
    }
}
