package com.hef.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author lifei
 * @since 2020/7/12
 */
@Aspect
public class UseNamePointcutAspect {

    @Before("TestNamePointcut.inPackage()")
    public void pkgGreetTo(){
        System.out.println("--pkgGreetTo() executed!--");
    }

    @Before(" !target(com.hef.service.impl.NaiveWaiter) && TestNamePointcut.inPackage()")
    public void pkgGreetToNoNaiveWaiter(){
        System.out.println("pkgGreetToNoNaiveWaiter() executed!--");
    }
}
