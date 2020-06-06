package com.hef.injectfun;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * @Date 2020/5/24
 * @Author lifei
 */
public class Boss2 implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        Car car = new Car();
        car.setBrand("ADQ6");
        return car;
    }
}
