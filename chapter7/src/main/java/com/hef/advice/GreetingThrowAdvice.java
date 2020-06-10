package com.hef.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * throwsAdvice 是一个标签接口
 * @author lifei
 * @since 2020/6/10
 */
public class GreetingThrowAdvice implements ThrowsAdvice {

    /**
     * 方法名必须为 afterThrowing ,前三个入参可选，要么提供，要么不提供。而最后一个是 Throwable或子类
     * @param method
     * @param args
     * @param target
     * @param exc
     */
    public void afterThrowing(Method method, Object[] args, Object target, Exception exc){
        System.out.println("--------");
        System.out.println("method: " + method.getName());
        System.out.println("抛出异常： " + exc.getLocalizedMessage());
        System.out.println("成功回滚事务！");
    }
}
