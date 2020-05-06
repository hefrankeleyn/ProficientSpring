package com.hef.dao.impl;

import com.hef.dao.LoginLogDao;
import com.hef.domain.LoginLog;
import com.hef.utils.MyInstanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Date 2020/4/21
 * @Author lifei
 */
@Repository
public class LoginLogDaoImpl implements LoginLogDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String INSERT_LOGIN_LOG_SQL = "insert into t_login_log(user_id,ip,login_datetime) values(?,?,?)";

    @Override
    public void insertLoginLog(LoginLog loginLog) {
        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL,new Object[]{loginLog.getUserId(),loginLog.getIp(), MyInstanceUtil.instantToDefaultStr(loginLog.getLoginDate())});
    }
}
