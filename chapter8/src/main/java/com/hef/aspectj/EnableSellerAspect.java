package com.hef.aspectj;

import com.hef.service.Seller;
import com.hef.service.impl.SmartSeller;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * @author lifei
 * @since 2020/7/6
 */
@Aspect
public class EnableSellerAspect {

    // 默认的接口实现
    @DeclareParents(value = "com.hef.service.impl.NaiveWaiter", defaultImpl = SmartSeller.class)
    public Seller seller; // 要实现的目标接口
}
