package com.hef.service.impl;

import com.hef.service.Seller;

/**
 * @author lifei
 * @since 2020/6/14
 */
public class NativeSeller implements Seller {
    @Override
    public void greetTo(String name) {
        System.out.println("seller greet to " + name + "...");
    }
}
