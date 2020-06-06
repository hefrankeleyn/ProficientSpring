package com.hef.factory;

import com.hef.beans.Car;

/**
 * @Date 2020/5/24
 * @Author lifei
 */
public class CarStaticFactory {

    public static Car createBTCar(){
        Car car = new Car(120);
        car.setBrand("BT");
        return car;
    }
}
