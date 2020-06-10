package com.hef.service.impl;

import com.hef.service.Waiter;

/**
 * @since 2020/6/7
 * @author lifei
 */
public class NativeWaiter implements Waiter {
    @Override
    public void greetTo(String name) {
        System.out.println("greet to" + name + "...");
    }

    @Override
    public void serveTo(String name) {
        System.out.println("serving " + name + "...");
    }
}
