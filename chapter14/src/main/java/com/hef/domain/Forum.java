package com.hef.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author lifei
 * @since 2020/8/8
 */
public class Forum {

    private Integer forumId;
    private String forumName;
    private String forumDesc;

    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public String getForumDesc() {
        return forumDesc;
    }

    public void setForumDesc(String forumDesc) {
        this.forumDesc = forumDesc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
