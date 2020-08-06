package com.hef.service;


import com.hef.beans.Forum;
import com.hef.beans.Topic;
import com.hef.dao.ForumDao;
import com.hef.dao.PostDao;
import com.hef.dao.TopicDao;
import org.apache.commons.lang.builder.ToStringBuilder;


public class BbtForum {
    public ForumDao forumDao;

    public TopicDao topicDao;

    public PostDao postDao;

    public void addTopic(Topic topic) throws Exception {
        topicDao.addTopic(topic);
//		if(true) throw new PessimisticLockingFailureException("fail");
        postDao.addPost(topic.getPost());
    }


    public Forum getForum(int forumId) {
        return forumDao.getForum(forumId);
    }

    public void updateForum(Forum forum) {
        forumDao.updateForum(forum);
    }

    public int getForumNum() {
        return forumDao.getForumNum();
    }

    public void setForumDao(ForumDao forumDao) {
        this.forumDao = forumDao;
    }

    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
