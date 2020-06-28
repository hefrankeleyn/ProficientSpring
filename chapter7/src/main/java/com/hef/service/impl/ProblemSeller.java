package com.hef.service.impl;


/**
 * @author lifei
 * @since 2020/6/14
 */
public class ProblemSeller {
    public void greetTo(String name) {
        System.out.println("seller greet to " + name + "...");
        serveTo(name);
    }
    public void serveTo(String name) {
        System.out.println("serving " + name + "...");
    }
}
