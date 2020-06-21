package com.hef.advice;

import com.hef.service.impl.WaiterDelegate;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * @author lifei
 * @since 2020/6/21
 */
public class GreetingComposablePointcut {

    public Pointcut getIntersectionPointcut(){
        // 创建一个复合切点
        ComposablePointcut cp = new ComposablePointcut();

        // 创建一个流程切面
        Pointcut pt1 = new ControlFlowPointcut(WaiterDelegate.class, "service");
        // 创建一个方法名切点
        NameMatchMethodPointcut pt2 = new NameMatchMethodPointcut();
        pt2.addMethodName("serveTo");

        return cp.intersection(pt1).intersection((MethodMatcher) pt2);
    }
}
