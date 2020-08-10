package com.hef.dao;

import com.hef.domain.Post;
import org.springframework.stereotype.Repository;

/**
 * @author lifei
 * @since 2020/8/10
 */
@Repository
public class PostHibernateDao extends BaseDao<Post> {

    public Post findPostById(Integer postId){
        Post post = getHibernateTemplate().get(Post.class, postId);
        return post;
    }


}
