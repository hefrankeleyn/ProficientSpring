package com.hef.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author lifei
 * @since 2020/7/9
 */
@Aspect
public class ArgsTestAspect {

    @Before("args(com.hef.service.Fly)")
    public void readyFly(){
        System.out.println("There is bird ready fly...");
    }
}
