package com.hef.aspectj;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author lifei
 * @since 2020/7/7
 */
@Aspect
public class AnnoTestAspect {

    @AfterReturning("@annotation(com.hef.anno.NeedTest)")
    public void needTestFun(){
        System.out.println("needTest run...");
    }
}
