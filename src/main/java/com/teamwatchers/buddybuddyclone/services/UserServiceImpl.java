package com.teamwatchers.buddybuddyclone.services;

import java.util.Optional;

import com.teamwatchers.buddybuddyclone.daos.UserDao;
import com.teamwatchers.buddybuddyclone.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User changeStatusMessage(Long userId, String statusMessage) {
        User user = userDao.findById(userId).orElseThrow(
            () -> new RuntimeException("사용자 id가 올바르지 않습니다.")
        );

        user.setStatusMessage(statusMessage);

        return userDao.save(user);
    }
}
