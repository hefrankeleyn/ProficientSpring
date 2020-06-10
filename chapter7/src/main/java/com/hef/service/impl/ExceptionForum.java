package com.hef.service.impl;

import com.hef.service.ForumService;

/**
 * @author lifei
 * @since 2020/6/10
 */
public class ExceptionForum implements ForumService {
    @Override
    public void removeTopic(int topicId) {
        throw new RuntimeException("异常topicId: " + topicId);
    }

    @Override
    public void removeForum(int forumId) {
        throw new RuntimeException("异常forumId: " + forumId);
    }
}
