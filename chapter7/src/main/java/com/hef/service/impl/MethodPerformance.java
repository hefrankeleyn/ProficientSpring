package com.hef.service.impl;

/**
 * @Date 2020/6/7
 * @Author lifei
 */
public class MethodPerformance {

    private long begin;
    private long end;
    private String serviceMethod;

    public MethodPerformance(String serviceMethod){
        this.serviceMethod = serviceMethod;
        // 记录目标方法开始执行的系统时间
        this.begin = System.currentTimeMillis();
    }

    public void printPerformance(){
        end = System.currentTimeMillis();
        long elapse = end - begin;
        System.out.println(serviceMethod + "花费" + elapse + "毫秒。");
    }


}
