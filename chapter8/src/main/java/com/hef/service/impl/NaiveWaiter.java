package com.hef.service.impl;

import com.hef.service.Waiter;

/**
 * @author lifei
 * @since 2020/6/28
 */
public class NaiveWaiter implements Waiter {
    @Override
    public void greetTo(String clientName) {
        System.out.println("NaiveWaiter: greet to " + clientName);
    }

    @Override
    public void serveTo(String clientName) {
        System.out.println("NaiveWaiter: serving " + clientName);
    }
}
