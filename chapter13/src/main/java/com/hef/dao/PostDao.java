package com.hef.dao;

import com.hef.beans.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author lifei
 * @since 2020/8/8
 */
@Repository
public class PostDao {

    private JdbcTemplate jdbcTemplate;
    private LobHandler lobHandler;

    @Autowired
    public void setLobHandler(LobHandler lobHandler) {
        this.lobHandler = lobHandler;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addPost(Post post){
        String sql = "insert into t_post(topic_id,forum_id, user_id, post_text, post_attach) values(?,?,?,?,?)";
        jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
            @Override
            protected void setValues(PreparedStatement preparedStatement, LobCreator lobCreator) throws SQLException, DataAccessException {
                preparedStatement.setInt(1, post.getTopicId());
                preparedStatement.setInt(2, post.getForumId());
                preparedStatement.setInt( 3, post.getUserId());
                lobCreator.setClobAsString(preparedStatement, 4, post.getPostText());
                lobCreator.setBlobAsBytes(preparedStatement, 5, post.getPostAttach());
            }
        });
    }


}
