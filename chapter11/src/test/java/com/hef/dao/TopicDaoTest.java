package com.hef.dao;

import com.hef.beans.Forum;
import com.hef.service.BbtForum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * 继承AbstractTransactionalTestNGSpringContextTests类，test方法中的测试数据不会真的提交数据库，他将在test方法执行完毕后进行回滚
 * @author lifei
 * @since 2020/8/5
 */
@ContextConfiguration(locations = {"classpath:spring-db.xml"})
public class TopicDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    private ForumDao forumDao;
    private BbtForum bbtForum;
    @Autowired
    public void setForumDao(ForumDao forumDao) {
        this.forumDao = forumDao;
    }

    @Autowired
    public void setBbtForum(BbtForum bbtForum) {
        this.bbtForum = bbtForum;
    }

    @Test
    public void testBean(){
//        System.out.println(forumDao);
        System.out.println(bbtForum);
    }

    @Rollback(value = false)
    @Test
    public void addForum(){
        Forum forum = new Forum();
        forum.setForumName("Java01");
        forum.setForumDesc("Learning Java forum.");
        forumDao.addForum(forum);
        System.out.println(forum);
    }

    @Test
    public void getForum(){
        Forum forum = forumDao.getForum(5);
        System.out.println(forum);
    }
}
