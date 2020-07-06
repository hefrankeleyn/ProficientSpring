package com.hef.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author lifei
 * @since 2020/6/28
 */
@Aspect
public class PreGreetingAspect {
    @Before("execution(* greetTo(..))")
    public void beforeGreeting(){
        System.out.println("How are you");
    }
}
