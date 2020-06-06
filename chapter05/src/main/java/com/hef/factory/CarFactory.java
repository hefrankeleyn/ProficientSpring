package com.hef.factory;

import com.hef.beans.Car;

/**
 * @Date 2020/5/24
 * @Author lifei
 */
public class CarFactory {

    public Car createHongQiCar(){
        Car car = new Car(300);
        car.setBrand("HongQi 001");
        return car;
    }
}
