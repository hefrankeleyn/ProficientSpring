package com.hef.service.impl;

import com.hef.service.Waiter;

/**
 * @author lifei
 * @since 2020/6/21
 */
public class WaiterDelegate {

    private Waiter waiter;

    public void service(String clientName){
        waiter.greetTo(clientName);
        waiter.serveTo(clientName);
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }
}
