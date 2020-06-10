package com.hef.service.impl;

/**
 * @Date 2020/6/7
 * @Author lifei
 */
public class PerformanceMonitor {

    // ThreadLocal是将非线程安全的类改造为线程安全的类的"法宝"
    private static ThreadLocal<MethodPerformance> performanceRecord = new ThreadLocal<MethodPerformance>();


    public static void begin(String method){
        System.out.println("begin monitor...");
        MethodPerformance mp = new MethodPerformance(method);
        performanceRecord.set(mp);
    }

    public static void end(){
        System.out.print("end monitor: ");
        MethodPerformance mp = performanceRecord.get();
        mp.printPerformance();
    }
}
