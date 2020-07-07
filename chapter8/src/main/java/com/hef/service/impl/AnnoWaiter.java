package com.hef.service.impl;

import com.hef.anno.NeedTest;
import com.hef.service.Waiter;

/**
 * @author lifei
 * @since 2020/7/7
 */
public class AnnoWaiter implements Waiter {

    @Override
    @NeedTest
    public void greetTo(String clientName) {
        System.out.println("anno waiter greet to " + clientName);
    }

    @Override
    public void serveTo(String clientName) {
        System.out.println("anno waiter serve to " + clientName);
    }
}
