package com.hef.editor;

import com.hef.beans.Car;

import java.beans.PropertyEditorSupport;

/**
 * 自定义属性编辑器
 *
 * @Date 2020/5/31
 * @Author lifei
 */
public class CustomCarEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text==null || text.indexOf(",")==-1){
            throw new IllegalArgumentException("设置的字符串格式不正确");
        }
        String[] infos = text.split(",");
        Car car = new Car();
        car.setBrand(infos[0]);
        car.setColor(infos[1]);
        car.setMaxSpeed(Integer.parseInt(infos[2]));

        // 调用父类的setValue() 方法设置转换后的属性对象
        setValue(car);
    }
}
