package com.hef;

import com.hef.service.ForumService;
import com.hef.service.impl.ForumServiceImpl;

/**
 * @Date 2020/6/7
 * @Author lifei
 */
public class TestForumService {

    public static void main(String[] args) {
        ForumService forumService = new ForumServiceImpl();
        forumService.removeForum(23);
        forumService.removeTopic(2);
    }
}
