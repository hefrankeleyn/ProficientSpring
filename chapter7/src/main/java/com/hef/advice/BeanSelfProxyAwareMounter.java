package com.hef.advice;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author lifei
 * @since 2020/6/21
 */
public class BeanSelfProxyAwareMounter implements SystemBootAddon, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void onReady() {
        Map<String, BeanSelfProxyAware> proxyAwareMap =
                applicationContext.getBeansOfType(BeanSelfProxyAware.class);
        if(proxyAwareMap!=null){
            for (BeanSelfProxyAware beanSelfProxyAware : proxyAwareMap.values()) {
                beanSelfProxyAware.setSelfProxy(beanSelfProxyAware);
                System.out.println("{}注册自身被代理的实例.");
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public int value() {
        return  Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
