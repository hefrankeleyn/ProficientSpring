package com.hef.conf;

import com.hef.beans.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Date 2020/5/24
 * @Author lifei
 */
@Configuration
@ImportResource("classpath:beans.xml")
public class UserConf {
    @Bean
    public User user(){
        return new User();
    }
}
