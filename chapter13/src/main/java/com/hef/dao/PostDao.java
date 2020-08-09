package com.hef.dao;

import com.hef.beans.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    /**
     * 以块数据方式读取LOB数据
     * 由于Lob数据的体积可能很大（如100MB），如果直接以块的方式操作LOB数据，则需要消耗大量的内存，直接影响程序的整体运行。对于体积很大的LOB数据，可以使用流的方式进行访问。
     * @param userId
     * @return
     */
    public List<Post> getAttachs(int userId){
        String sql = "select post_id, post_attach from t_post where user_id=? and post_attach is not null";
        return jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<Post>() {
            @Override
            public Post mapRow(ResultSet resultSet, int i) throws SQLException {
                int postId = resultSet.getInt(1);
                byte[] attach = lobHandler.getBlobAsBytes(resultSet, 2);

                Post post = new Post();
                post.setPostId(postId);
                post.setPostAttach(attach);
                return post;
            }
        });
    }

    /**
     * 以流的形式读取LOB数据
     * @param postId
     * @param outputStream
     */
    public void getAttachsByStream(int postId, OutputStream outputStream){
        String sql = "select post_attach from t_post where post_id=? and post_attach is not null";
        jdbcTemplate.query(sql, new Object[]{postId}, new AbstractLobStreamingResultSetExtractor<Object>() {

            @Override
            protected void handleNoRowFound() throws DataAccessException {
                System.out.println("Not Found result!");
            }
            @Override
            protected void streamData(ResultSet resultSet) throws SQLException, IOException, DataAccessException {
                InputStream is = lobHandler.getBlobAsBinaryStream(resultSet, 1);
                if (is !=null){
                    FileCopyUtils.copy(is, outputStream);
                }
            }
        });
    }






}
