package com.hef.util;

import com.hef.utils.MyInstanceUtil;
import org.junit.Test;

import java.time.Instant;

/**
 * @Date 2020/4/21
 * @Author lifei
 */
public class MyInstanceUtilTest {

    @Test
    public void instantStrTest(){
        String str = MyInstanceUtil.instantToDefaultStr(Instant.now());
        System.out.println(str);
    }
}
