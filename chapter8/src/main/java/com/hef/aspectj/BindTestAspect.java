package com.hef.aspectj;

import com.hef.anno.NeedTest;
import com.hef.service.Waiter;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import javax.management.monitor.Monitor;

/**
 * @author lifei
 * @since 2020/7/12
 */
@Aspect
public class BindTestAspect {

    /**
     * 将匹配 smile(String name, int num)
     * @param num
     * @param name
     */
    @Before("target(com.hef.service.impl.NaiveWaiter) && args(name, num,..)")
    public void bindJoinPointParams(int num, String name){
        System.out.println("--bindJoinPointParams()---");
        System.out.println("name: " + name);
        System.out.println("num: " + num);
        System.out.println("--bindJoinPointParams()---");
    }

    @Before("this(waiter)")
    public void bindProxyObj(Waiter waiter){
        System.out.println("-- bindProxyObj() --");
        System.out.println(waiter.getClass().getName());
        System.out.println("-- bindProxyObj() --");
    }

    @Before("@within(needTest)")
    public void bindTypeAnnoObject(NeedTest needTest){
        System.out.println("-- bindTypeAnnoObject -- ");
        System.out.println(needTest.getClass().getName());
        System.out.println("-- bindTypeAnnoObject -- ");
    }

    @AfterReturning(value = "target(com.hef.service.impl.SmartSeller)", returning = "retVAl")
    public void bindReturnValue(int retVAl){
        System.out.println("-- bindReturnValue()--");
        System.out.println("returnVAlue: " + retVAl);
        System.out.println("-- bindReturnValue()--");
    }

    @AfterThrowing(value = "target(com.hef.service.impl.SmartSeller)", throwing = "iae")
    public void bindException(IllegalArgumentException iae){
        System.out.println("-- bindException() --");
        System.out.println("exception: " + iae.getMessage());
        System.out.println("-- bindException() --");
    }
}
