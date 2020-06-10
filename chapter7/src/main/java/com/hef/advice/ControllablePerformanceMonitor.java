package com.hef.advice;

import com.hef.service.MonitorAble;
import com.hef.service.impl.PerformanceMonitor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * 引介增强
 * @author lifei
 * @since 2020/6/10
 */
public class ControllablePerformanceMonitor extends DelegatingIntroductionInterceptor implements MonitorAble {

    private ThreadLocal<Boolean> monitorStatusMap = new ThreadLocal<>();
    @Override
    public void setMonitorActive(boolean active) {
        monitorStatusMap.set(active);
    }
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object obj = null;
        // 对于支持性能监控可控代理，通过判断其状态决定是是否开启性能监控功能
        if (monitorStatusMap.get()!=null && monitorStatusMap.get()){
            PerformanceMonitor.begin(mi.getClass().getName() + "." + mi.getMethod().getName());
            obj = super.invoke(mi);
            PerformanceMonitor.end();
        }else {
            obj = super.invoke(mi);
        }
        return obj;
    }
}
