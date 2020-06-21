package com.hef.advice;

import com.hef.service.Waiter;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * 定义切点
 * @author lifei
 * @since 2020/6/14
 */
public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return "greetTo".equals(method.getName());
    }

    @Override
    public ClassFilter getClassFilter() {
        return (clazz)->{return Waiter.class.isAssignableFrom(clazz);};
    }
}
