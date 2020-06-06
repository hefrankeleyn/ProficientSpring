package com.hef.beans;

/**
 * @Date 2020/5/24
 * @Author lifei
 */
public class Boss {

    private Car car;
    private Car iniCar = new Car(100);

    public Car getIniCar() {
        return iniCar;
    }

    public void setIniCar(Car iniCar) {
        this.iniCar = iniCar;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void bossDesc(){
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
