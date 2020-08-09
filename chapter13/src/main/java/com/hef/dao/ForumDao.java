package com.hef.dao;

import com.hef.beans.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lifei
 * @since 2020/8/8
 */
@Repository
public class ForumDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String ADD_FORUM_SQL = "insert into t_forum(forum_name, forum_desc) values(?,?)";

    /**
     * 添加数据
     * @param forum
     */
    public void addForum(Forum forum){
        String sql = "insert into t_forum(forum_name, forum_desc) values(?,?)";
        Object[] params = new Object[]{forum.getForumName(), forum.getForumDesc()};

        // 第一种写法：
//        jdbcTemplate.update(sql, params);
        // 第二种写法：
        jdbcTemplate.update(sql, params, new int[]{Types.VARCHAR, Types.VARCHAR});
        // 第三种写法： 通过匿名类回调函数——参数索引从 1 开始 而非从0 开始
        /**
         * 在实际使用中， 应优先考虑使用不带回调接口调JdbcTemplate 方法。
         * 首先， 回调使代码显得臃肿；
         * 其次， 回调并不能带来额外调好处；
         */
        /*jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, forum.getForumName());
                ps.setString(2, forum.getForumDesc());
            }
        });*/

    }

    /**
     * 添加一条记录，并返回绑定一个主键值
     * @param forum
     */
    public void addForumAndFetchId(Forum forum){
        // 创建一个主键持有者
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                /**
                 * Statement.RETURN_GENERATED_KEYS 绑定主键值
                 * Statement.NO_GENERATED_KEYS  不绑定主键值
                 */
                PreparedStatement ps = connection.prepareStatement(ADD_FORUM_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, forum.getForumName());
                ps.setString(2, forum.getForumDesc());
                return ps;
            }
        }, keyHolder);
        forum.setForumId(keyHolder.getKey().intValue());
    }

    /**
     * 批量更新数据
     * @param forumList
     */
    public void addForums(List<Forum> forumList){
        jdbcTemplate.batchUpdate(ADD_FORUM_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Forum forum = forumList.get(i);
                preparedStatement.setString(1, forum.getForumName());
                preparedStatement.setString(2, forum.getForumDesc());
            }
            /**
             * 指定该批次的记录数
             * @return
             */
            @Override
            public int getBatchSize() {
                return forumList.size();
            }
        });
    }

    /**
     * 查询数据
     * @param forumId
     * @return
     */
    public Forum getForum(int forumId){
        String sql = "select forum_name, forum_desc from t_forum where forum_id=?";
        Forum forum = new Forum();
        jdbcTemplate.query(sql, new Object[]{forumId}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                forum.setForumId(forumId);
                forum.setForumName(resultSet.getString("forum_name"));
                forum.setForumDesc(resultSet.getString("forum_desc"));
            }
        });
        return forum;
    }

    /**
     * 使用 RowCallbackHandler 查询多个
     * @param forumId
     * @param forumId02
     * @return
     */
    public List<Forum> getForumByForumIds(int forumId, int forumId02){
        String sql = "select forum_id, forum_name, forum_desc from t_forum where forum_id in (?,?) ";
        List<Forum> forumList = new ArrayList<>();
        jdbcTemplate.query(sql, new Object[]{forumId, forumId02}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Forum forum = new Forum();
                forum.setForumId(resultSet.getInt("forum_id"));
                forum.setForumName(resultSet.getString("forum_name"));
                forum.setForumDesc(resultSet.getString("forum_desc"));
                forumList.add(forum);
            }
        });
        return forumList;
    }

    /**
     * 通过 RollMapper 查询多个
     * @param forumId
     * @param forumId02
     * @return
     */
    public List<Forum> getForumByForumIdsAndRollMapper(int forumId, int forumId02){
        String sql = "select forum_id, forum_name, forum_desc from t_forum where forum_id in (?,?) ";
        return jdbcTemplate.query(sql, new Object[]{forumId, forumId02}, new RowMapper<Forum>() {
            @Override
            public Forum mapRow(ResultSet resultSet, int i) throws SQLException {
                Forum forum = new Forum();
                forum.setForumId(resultSet.getInt("forum_id"));
                forum.setForumName(resultSet.getString("forum_name"));
                forum.setForumDesc(resultSet.getString("forum_desc"));
                return forum;
            }
        });
    }

    public int getForumNum(){
        String sql = "select count(*) from t_forum";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }



}
