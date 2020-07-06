package com.hef.service.impl;

import com.hef.service.Seller;

/**
 * @author lifei
 * @since 2020/7/6
 */
public class SmartSeller implements Seller {
    @Override
    public void sell(String goods) {
        System.out.println("There sell " + goods);
    }
}
