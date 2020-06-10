package com.hef.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 环绕增强
 * @author lifei
 * @since 2020/6/9
 */
public class GreetingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        // 目标方法的入参
        Object[] args = methodInvocation.getArguments();
        String clientName = (String)args[0];
        // 在目标方法执行前调用
        System.out.println("How are you! Mr." + clientName + ".");
        // 通过反射机制调用目标方法
        Object obj = methodInvocation.proceed();
        // 在目标方法执行后调用
        System.out.println("Place enjoy yourself!");
        return obj;
    }
}
