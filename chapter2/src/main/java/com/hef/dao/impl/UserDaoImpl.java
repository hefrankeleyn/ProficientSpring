package com.hef.dao.impl;

import com.hef.dao.UserDao;
import com.hef.domain.User;
import com.hef.utils.MyInstanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Date 2020/4/21
 * @Author lifei
 */
@Repository
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String MATCH_COUNT_SQL = "select count(*) from t_user where user_name=? and password=?";

    private final static String QUERY_BY_USERNAME = "select `user_id`,`user_name`,`credit`,`password`,`last_visit`," +
            "`last_ip` from t_user where user_name=?";

    private final static String UPDATE_LOGIN_INFO_SQL = "update t_user set last_visit=?,last_ip=?,credit=? where user_id=?";

    @Override
    public int getMatchCount(String userName, String password) {
        return this.jdbcTemplate.queryForObject(MATCH_COUNT_SQL,new Object[]{userName,password},Integer.class);
    }

    @Override
    public User findUserByUsername(String userName) {
        User user = new User();
        this.jdbcTemplate.query(QUERY_BY_USERNAME, new Object[]{userName}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setCredits(resultSet.getInt("credit"));
                user.setPassword(resultSet.getString("password"));
                user.setLastVisit(MyInstanceUtil.changeInstantToDate(resultSet.getDate("last_visit")));
                user.setLastIp(resultSet.getString("last_ip"));
            }
        });
        return user;
    }

    @Override
    public void updateLoginInfo(User user) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL,new Object[]{MyInstanceUtil.instantToDefaultStr(user.getLastVisit()),user.getLastIp(),user.getCredits(),user.getUserId()});
    }


}
