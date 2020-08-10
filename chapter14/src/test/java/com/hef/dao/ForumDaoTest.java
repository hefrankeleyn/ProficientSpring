package com.hef.dao;

import com.hef.domain.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author lifei
 * @since 2020/8/9
 */
@ContextConfiguration(locations = {"classpath:applicationContext-hbt.xml"})
public class ForumDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    private ForumHibernateDao forumHibernateDao;

    @Autowired
    public void setForumHibernateDao(ForumHibernateDao forumHibernateDao) {
        this.forumHibernateDao = forumHibernateDao;
    }

    @Test
    public void forumHibernateDaoTest(){
        Assert.assertNotNull(forumHibernateDao);
    }

    @Test
    public void getForumTest(){
        Forum forum = forumHibernateDao.getForum(5);
        System.out.println(forum);
        Assert.assertNotNull(forum);
    }
}
