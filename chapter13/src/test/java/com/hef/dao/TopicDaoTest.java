package com.hef.dao;

import com.hef.beans.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

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

    @Test
    public void findTopicListTest(){
        Topic topic = new Topic();
        topic.setUserId(2);
        List<Topic> topicList = topicDao.findTopicList(topic);
        System.out.println(topicList);
    }
}
