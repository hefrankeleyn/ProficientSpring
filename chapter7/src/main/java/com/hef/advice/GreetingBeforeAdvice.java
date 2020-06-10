package com.hef.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Date 2020/6/7
 * @Author lifei
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object o) throws Throwable {
        String clientName = (String)args[0];
        System.out.println("How are you! Mr. " + clientName + ".");
    }
}
