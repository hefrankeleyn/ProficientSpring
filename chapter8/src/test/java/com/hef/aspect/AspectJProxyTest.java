package com.hef.aspect;

import com.hef.aspectj.PreGreetingAspect;
import com.hef.service.Waiter;
import com.hef.service.impl.NaiveWaiter;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 * @author lifei
 * @since 2020/6/28
 */
public class AspectJProxyTest {

    @Test
    public void proxy(){
        Waiter waiter = new NaiveWaiter();
        AspectJProxyFactory factory = new AspectJProxyFactory();
        // 设置目标对象
        factory.setTarget(waiter);
        // 添加切面
        factory.addAspect(PreGreetingAspect.class);
        // 生成织入切面的代理对象
        Waiter proxy = factory.getProxy();

        proxy.greetTo("XiaoMing");
        proxy.serveTo("XiaoMing");
    }
}
