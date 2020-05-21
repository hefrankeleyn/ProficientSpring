package com.hef.service;

import com.hef.Car;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @Date 2020/5/21
 * @Author lifei
 */
public class ResourceApp {

    public static void main(String[] args) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource res = resolver.getResource("classpath:bean.xml");

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(res);

        Car car = factory.getBean("car1", Car.class);
        System.out.println(car.toString());

        try {
            System.out.println(res.getURL());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
