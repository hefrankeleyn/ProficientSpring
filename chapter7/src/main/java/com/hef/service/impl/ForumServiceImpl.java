package com.hef.service.impl;

import com.hef.service.ForumService;

/**
 * @Date 2020/6/7
 * @Author lifei
 */
public class ForumServiceImpl implements ForumService {


    public void removeTopic(int topicId) {
        // 开始监控
//        PerformanceMonitor.begin("com.hef.service.impl.ForumServiceImpl.removeTopic");
        System.out.println("模拟删除Topic记录: " + topicId);
        try {
            Thread.currentThread().sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 结束监控
//        PerformanceMonitor.end();
    }

    public void removeForum(int forumId) {
        // 开始监控
//        PerformanceMonitor.begin("com.hef.service.impl.ForumServiceImpl.removeForum");
        System.out.println("模拟删除Forum记录: " + forumId);
        try {
            Thread.currentThread().sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 结束监控
//        PerformanceMonitor.end();
    }
}
