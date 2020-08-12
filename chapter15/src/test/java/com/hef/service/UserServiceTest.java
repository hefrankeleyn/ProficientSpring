package com.hef.service;

import com.hef.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * @author lifei
 * @since 2020/8/12
 */
@ContextConfiguration(locations = {"classpath:applicationContext-catch.xml"})
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserService userService;

    @Test
    public void getUserByIdTest(){
        System.out.println("First run method： ");
        User user = userService.getUserById("oneId");
        System.out.println(user);
        System.out.println("Second run method： ");
        User secondUser = userService.getUserById("oneId");
        System.out.println(user);

    }
}
