package com.hef.advice;

import com.hef.service.Waiter;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lifei
 * @since 2020/6/15
 */
public class GreetingDynamicPointcut extends DynamicMethodMatcherPointcut {

    private static List<String> specialClientList = new ArrayList<>();

    static {
        specialClientList.add("John");
        specialClientList.add("Tom");
    }

    @Override
    public ClassFilter getClassFilter() {
        return (clazz)->{
            System.out.println("调用getClassFilter()对" + clazz.getName() + "做静态检查。");
            return Waiter.class.isAssignableFrom(clazz);
        };
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object[] args) {
        System.out.println("调用matches(method,clazz)" + targetClass.getName() + "." + method.getName()+"做动态检查");
        String clientName = (String) args[0];
        return specialClientList.contains(clientName);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("调用matches(method,clazz)" +
                targetClass.getName() + "." + method.getName()+"做静态检查");
        return "greetTo".equals(method.getName());
    }
}
