package com.hef.conf;

import com.hef.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Date 2020/5/24
 * @Author lifei
 */
@Configuration
@Import(UserConf.class)
public class AppConf {
    @Bean
    public UserDao userDao(){
        return new UserDao();
    }
}
