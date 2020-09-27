package com.teamwatchers.buddybuddyclone.services.auth;

import com.teamwatchers.buddybuddyclone.controllers.request.AuthSignUpRequest;
import com.teamwatchers.buddybuddyclone.daos.UserDao;
import com.teamwatchers.buddybuddyclone.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private UserDao userDao;
    @Autowired
    public AuthServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean signUp(AuthSignUpRequest request) {
        if (!request.getPassword().equals(request.getMatchingPassword())) {
            return false;
        }

        String password = request.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(password);

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encodedPassword);

        userDao.save(user);

        return true;
    }
}
