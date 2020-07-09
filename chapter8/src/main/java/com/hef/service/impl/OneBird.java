package com.hef.service.impl;

import com.hef.service.Bird;
import com.hef.service.Fly;

/**
 * @author lifei
 * @since 2020/7/9
 */
public class OneBird implements Bird {
    @Override
    public void action(Fly fly) {
        fly.fly();
    }
}
