package com.hef.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * @author lifei
 * @since 2020/8/9
 */
@ContextConfiguration(locations = {"classpath:spring-db.xml"})
public class TopicDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    private TopicDao topicDao;

    @Autowired
    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    /**
     * 通过行集获取数据
     */
    @Test
    public void getTopicRowSetTest(){
        SqlRowSet topicRowSet = topicDao.getTopicRowSet(1);
        while (topicRowSet.next()){
            System.out.println(topicRowSet.getString("topic_title"));
        }
    }
}
