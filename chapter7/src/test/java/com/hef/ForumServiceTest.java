package com.hef;

import com.hef.proxy.CglibProxy;
import com.hef.proxy.PerformanceHandler;
import com.hef.service.ForumService;
import com.hef.service.impl.ForumServiceImpl;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @Date 2020/6/7
 * @Author lifei
 */
public class ForumServiceTest {

    @Test
    public void jdkDynamicProxy(){
        ForumService target = new ForumServiceImpl();
        PerformanceHandler performanceHandler = new PerformanceHandler(target);
        ForumService proxy = (ForumService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), performanceHandler);
        proxy.removeForum(10);
        proxy.removeTopic(10);
    }

    @Test
    public void cglibProxy(){
        CglibProxy proxy = new CglibProxy();
        ForumService forumService = (ForumService) proxy.getProxy(ForumServiceImpl.class);
        forumService.removeTopic(10);
        forumService.removeForum(10);
    }
}
