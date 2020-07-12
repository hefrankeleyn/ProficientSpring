package com.hef.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author lifei
 * @since 2020/7/12
 */
@Aspect
public class AroundTestAspect {

    @Around("execution(* greetTo(..)) && target(com.hef.service.impl.NaiveWaiter)")
    public void jointPointAccess(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("---- begin jointPointAccess ---");

        System.out.println("args[0]: " + pjp.getArgs()[0]);
        System.out.println("signature: " + pjp.getTarget().getClass());
        pjp.proceed();
        System.out.println("--- end jointPointAccess --- ");
    }
}
