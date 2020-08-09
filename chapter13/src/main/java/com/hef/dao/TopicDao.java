package com.hef.dao;

import com.hef.beans.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lifei
 * @since 2020/8/9
 */
@Repository
public class TopicDao {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

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

    /**
     * 使用 NamedParameterJdbcTemplate
     * @param topic
     * @return
     */
    public List<Topic> findTopicList(Topic topic){
        String sql = "select topic_title from t_topic where user_id=:userId";
        Map<String, Integer> paramMap = new HashMap<>();
        paramMap.put("userId", topic.getUserId());
        return namedParameterJdbcTemplate.query(sql, paramMap, new RowMapper<Topic>() {
            @Override
            public Topic mapRow(ResultSet resultSet, int i) throws SQLException {
                Topic oneTopic = new Topic();
                oneTopic.setTopicTitle(resultSet.getString("topic_title"));
                return oneTopic;
            }
        });
    }
}
