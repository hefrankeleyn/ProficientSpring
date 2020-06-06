package com.hef.beans;

/**
 * @Date 2020/5/24
 * @Author lifei
 */
public class Boss {

    private String name;
    private Car car = new Car();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "name=" + name +", "+
                "car=" + car +
                '}';
    }
}
