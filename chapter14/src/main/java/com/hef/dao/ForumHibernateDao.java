package com.hef.dao;

import com.hef.domain.Forum;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lifei
 * @since 2020/8/9
 */
@Repository
public class ForumHibernateDao extends BaseDao<Forum>{

    public void addForum(Forum forum){
        getHibernateTemplate().save(forum);
    }

    public void updateForum(Forum forum){
        getHibernateTemplate().update(forum);
    }

    public Forum getForum(int forumId){
        return getHibernateTemplate().get(Forum.class, forumId);
    }

    public List<Forum> findForumByName(String forumName){
        return (List<Forum>) getHibernateTemplate().find("from Forum f where f.forumName like ?", forumName + "%");
    }

    public long getForumNum(){
        Object obj = getHibernateTemplate().iterate("select count(f.forumId) from Forum f").next();
        return (long) obj;
    }

    /**
     * 使用回调函数
     * @return
     */
    public long getForumNum2(){
        return getHibernateTemplate().execute(new HibernateCallback<Long>() {
            @Override
            public Long doInHibernate(Session session) throws HibernateException {
                Object obj = session.createQuery("select count(f.forumId) from Forum f").list().iterator().next();
                return (Long) obj;
            }
        });
    }
}
