package com.hef.dao;

import com.hef.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * @author lifei
 * @since 2020/8/10
 */
@ContextConfiguration(locations = {"classpath:applicationContext-hbt.xml"})
public class PostHibernateDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    private PostHibernateDao postHibernateDao;

    @Autowired
    public void setPostHibernateDao(PostHibernateDao postHibernateDao) {
        this.postHibernateDao = postHibernateDao;
    }

    @Test
    public void findPostById(){
        Post post = postHibernateDao.findPostById(2);
        System.out.println(post);
    }
}
