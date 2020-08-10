package com.hef.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Arrays;
import java.util.Date;

/**
 * @author lifei
 * @since 2020/8/8
 */
public class Post {
    private int postId;
    private int topicId;
    private int forumId;
    private int userId;
    private String postText;
    private byte[] postAttach;
    private Date postTime;
    private Topic topic;
    public int getForumId() {
        return forumId;
    }
    public void setForumId(int forumId) {
        this.forumId = forumId;
    }
    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }
    public String getPostText() {
        return postText;
    }
    public void setPostText(String postText) {
        this.postText = postText;
    }
    public Date getPostTime() {
        return postTime;
    }
    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }
    public int getTopicId() {
        return topicId;
    }
    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public byte[] getPostAttach() {
        return postAttach;
    }
    public void setPostAttach(byte[] postAttach) {
        this.postAttach = postAttach;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
