package com.hef.aspectj;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author lifei
 * @since 2020/7/11
 */
@Aspect
public class ThisTestAspect {
    @AfterReturning("this(com.hef.service.Seller)")
    public void thisTest(){
        System.out.println("ThisTestAspect#thisTest() executed.");
    }
}
