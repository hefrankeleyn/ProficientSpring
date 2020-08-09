package com.hef.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

/**
 * @author lifei
 * @since 2020/8/9
 */
@Repository
public class TopicDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 返回行集数据
     * 对于大结果集的数据，使用SQLRowSet会造成很大的内存消耗，这点要铭记
     * @param userId
     * @return
     */
    public SqlRowSet getTopicRowSet(int userId){
        String sql = "select topic_id, topic_title from t_topic where user_id=?";
        return jdbcTemplate.queryForRowSet(sql, userId);
    }
}
