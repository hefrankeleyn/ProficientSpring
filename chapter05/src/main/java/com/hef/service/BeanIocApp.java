package com.hef.service;

import com.hef.beans.Boss;
import com.hef.beans.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.RequestContextFilter;

/**
 * @Date 2020/5/24
 * @Author lifei
 */
public class BeanIocApp {

    public static void main(String[] args) {
        contextRun();
//        parentBean();

    }

    public static void contextRun(){
        ApplicationContext context  = new ClassPathXmlApplicationContext("classpath:beans.xml");
//        createCar(context);
//        factoryMethod(context);

//        createBoss(context);
//        innerCar(context);
//        carNull(context);
        iniCarBoss(context);
    }



    public static void carNull(ApplicationContext context){
        Car carNull = context.getBean("carNull", Car.class);
        carNull.carDesc();
    }
    public static void iniCarBoss(ApplicationContext context){
        Boss iniCarBoss = context.getBean("iniCarBoss", Boss.class);
        System.out.println(iniCarBoss.getIniCar());
    }

    public static void innerCar(ApplicationContext context){
        Boss bossInner = context.getBean("bossInner", Boss.class);
        bossInner.bossDesc();
    }

    public static void parentBean(){
        System.out.println("父子容器：");
        ClassPathXmlApplicationContext contextParent = new ClassPathXmlApplicationContext("beansParent.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"}, contextParent);
        Boss bossHongQiFromParent = context.getBean("bossHongQiFromParent", Boss.class);
        bossHongQiFromParent.bossDesc();
    }

    private static void createBoss(ApplicationContext context){
        System.out.println("创建boss： ");
        Boss boss = context.getBean("boss", Boss.class);
        boss.bossDesc();
    }

    private static void createCar(ApplicationContext context){
        //        System.out.println("属性注入：");
//        Car car = context.getBean("car", Car.class);
//        car.carDesc();
        System.out.println("构造函数注入");
        Car car01 = context.getBean("car01", Car.class);
        car01.carDesc();
        System.out.println("索引和类型混合使用");
        Car car02 = context.getBean("car02", Car.class);
        car02.carDesc();
    }

    private static void factoryMethod(ApplicationContext context){
        System.out.println("非静态工厂：");
        Car hongQiCar = context.getBean("hongQiCar", Car.class);
        hongQiCar.carDesc();
        System.out.println("静态工厂：");
        Car btCar = context.getBean("btCar", Car.class);
        btCar.carDesc();
    }
}
