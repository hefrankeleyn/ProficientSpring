package com.hef.advice;

import com.hef.service.ForumService;
import com.hef.service.MonitorAble;
import com.hef.service.Waiter;
import com.hef.service.impl.NativeWaiter;
import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Date 2020/6/7
 * @Author lifei
 */
public class BeforeAdviceTest {

    @Test
    public void before(){
        Waiter target = new NativeWaiter();
        BeforeAdvice beforeAdvice = new GreetingBeforeAdvice();
        ProxyFactory pf = new ProxyFactory();

        // 设置代理目标
        pf.setTarget(target);
        // 为代理目标添加增强
        pf.addAdvice(beforeAdvice);
        Waiter waiter = (Waiter) pf.getProxy();
        waiter.greetTo("XiaoMing");
        waiter.serveTo("XiaoHong");
    }

    @Test
    public void beforeContext(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Waiter waiter = (Waiter) context.getBean("waiter");
        waiter.greetTo("xiaoMing");
    }

    @Test
    public void aroundContext(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Waiter waiter = (Waiter) context.getBean("waiter02");
        waiter.greetTo("xiaoMing");
    }

    @Test
    public void throwContext(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        ForumService exceptionForum = (ForumService) context.getBean("exceptionForum");
        exceptionForum.removeForum(23);
    }

    @Test
    public void introduceContext(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        ForumService exceptionForum = (ForumService) context.getBean("monitorService");
        exceptionForum.removeForum(23);

        MonitorAble monitorAble = (MonitorAble) exceptionForum;
        monitorAble.setMonitorActive(true);
        exceptionForum.removeForum(23);
    }


}
