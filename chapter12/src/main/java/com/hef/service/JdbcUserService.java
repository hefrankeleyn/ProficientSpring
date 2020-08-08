package com.hef.service;

import com.hef.util.MyInstantUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.time.Instant;

@Service("jdbcUserService")
public class JdbcUserService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void logon(String userName) {
        Connection conn = null;
        try {
            // 通过 jdbcTemplate.getDataSource().getConnection() 获取连接，并不主动归还，将造成数据连接泄露哦
//            conn = jdbcTemplate.getDataSource().getConnection();
            // 通过 DataSourceUtils 获取连接，在事务环境下，不会造成连接泄露
            conn = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());

            String sql = "UPDATE t_user SET last_visit=? WHERE user_name =?";
            jdbcTemplate.update(sql, MyInstantUtils.instantToDefaultFormatStr(Instant.now()), userName);
            Thread.sleep(1000);//②模拟程序代码的执行时间
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* 无事务的环境下，需要手动执行下面的代码，进行连接释放*/
        /*finally {
            DataSourceUtils.releaseConnection(conn, jdbcTemplate.getDataSource());
        }*/

    }


    public static void asynchrLogon(JdbcUserService userService, String userName) {
        UserServiceRunner runner = new UserServiceRunner(userService, userName);
        runner.start();
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void reportConn(BasicDataSource basicDataSource) {
        System.out.println("连接数[active:idle]-[" +
                       basicDataSource.getNumActive()+":"+basicDataSource.getNumIdle()+"]");
    }

    private static class UserServiceRunner extends Thread {
        private JdbcUserService userService;
        private String userName;

        public UserServiceRunner(JdbcUserService userService, String userName) {
            this.userService = userService;
            this.userName = userName;
        }

        public void run() {
            userService.logon(userName);
        }
    }


    /**
     * 情况一：当打开： Connection conn = jdbcTemplate.getDataSource().getConnection();
     * 执行该方法，会出现下面的信息：
     连接数[active:idle]-[0:0]
     连接数[active:idle]-[0:0]
     连接数[active:idle]-[1:1]
     连接数[active:idle]-[3:0]
     连接数[active:idle]-[2:1]
     *  情况二： Connection conn = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
     连接数[active:idle]-[0:0]
     连接数[active:idle]-[0:0]
     连接数[active:idle]-[0:1]
     连接数[active:idle]-[1:0]
     连接数[active:idle]-[0:1]
     **  说明： 没有连接泄露
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-db.xml");
        JdbcUserService userService = (JdbcUserService) ctx.getBean("jdbcUserService");

        BasicDataSource basicDataSource = (BasicDataSource) ctx.getBean("dataSource");
        JdbcUserService.reportConn(basicDataSource);
        
        JdbcUserService.asynchrLogon(userService, "tom");
        JdbcUserService.sleep(500);
        JdbcUserService.reportConn(basicDataSource);


        JdbcUserService.sleep(2000);
        JdbcUserService.reportConn(basicDataSource);


        JdbcUserService.asynchrLogon(userService, "john");
        JdbcUserService.sleep(500);
        JdbcUserService.reportConn(basicDataSource);


        JdbcUserService.sleep(2000);
        JdbcUserService.reportConn(basicDataSource);

    }
}