package com.hef.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 定义增强
 * @Date 2020/6/7
 * @Author lifei
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object o) {
        System.out.println("前置增强开始运行...");
        System.out.println(o.getClass() + "." + method.getName());
        String clientName = (String)args[0];
        System.out.println("How are you! Mr. " + clientName + ".");
        System.out.println("前置增强运行完成！");
    }
}
