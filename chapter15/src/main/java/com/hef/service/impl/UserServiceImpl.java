package com.hef.service.impl;

import com.hef.domain.User;
import com.hef.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author lifei
 * @since 2020/8/12
 */
@Service
public class UserServiceImpl implements UserService {


    @Cacheable(cacheNames = "users")
    @Override
    public User getUserById(String userId) {
        System.out.println("real query user." + userId);
        return getFromDB(userId);
    }

    private User getFromDB(String userId){
        System.out.println("real querying db..." + userId);
        return new User.Builder().userId(userId).email("someone@gmail.com").builder();
    }
}
