package com.hef.dao;

import com.hef.beans.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

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


}
