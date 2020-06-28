package com.hef.advice;


import org.springframework.core.annotation.Order;

public interface SystemBootAddon extends Order {
    /**
     * 在系统就绪后调用的方法
     */
    void onReady();
}
