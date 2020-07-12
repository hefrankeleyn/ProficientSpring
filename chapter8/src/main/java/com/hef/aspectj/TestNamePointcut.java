package com.hef.aspectj;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @author lifei
 * @since 2020/7/12
 */
public class TestNamePointcut {

    @Pointcut("within(com.hef.service.*)")
    private void inPackage(){}

    @Pointcut("execution(* greetTo(..))")
    protected void greetTo(){}

    /** 引用命名切点定义的切点 */
    @Pointcut("inPackage() and greetTo()")
    public void inPackageGreetTo(){}
}
