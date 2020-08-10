package com.hef.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author lifei
 * @since 2020/8/9
 */
public class Topic {

    private String topicTitle;
    private Integer userId;

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
