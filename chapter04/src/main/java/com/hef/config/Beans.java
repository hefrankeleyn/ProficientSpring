package com.hef.config;

import com.hef.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Date 2020/5/21
 * @Author lifei
 */
@Configuration
public class Beans {

    @Bean(name = "car")
    public Car buildCar(){
        Car car = new Car();
        car.setBrand("AK47");
        car.setColor("block");
        car.setMaxSpeed(300);
        return car;
    }
}
