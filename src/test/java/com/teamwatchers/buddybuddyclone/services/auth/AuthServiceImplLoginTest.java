package com.teamwatchers.buddybuddyclone.services.auth;

import com.teamwatchers.buddybuddyclone.controllers.request.AuthLoginRequest;
import com.teamwatchers.buddybuddyclone.daos.UserDao;
import com.teamwatchers.buddybuddyclone.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AuthServiceImplLoginTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserDao userDao;

    private String userName;

    private String password;

    private User user;

    @BeforeEach
    public void setup()
    {
        userName = "킹왕짱zZ";
        password = "비밀번호닷!";

        user = new User();
        user.setUsername(userName);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        userDao.save(user);
    }

    @Test
    public void test() {
        // Given
        AuthLoginRequest request = new AuthLoginRequest();
        request.setUsername(userName);
        request.setPassword(password);

        // When
        boolean result = authService.login(request);

        // Then
        assertTrue(result);
    }


    @Test
    public void testWhenUserNameNotFound() {
        // Given
        AuthLoginRequest request = new AuthLoginRequest();
        request.setUsername("이건누구지?");
        request.setPassword(password);

        // When
        boolean result = authService.login(request);

        // Then
        assertFalse(result);
    }

    @Test
    public void testWhenPasswordNotMatched() {
        // Given
        AuthLoginRequest request = new AuthLoginRequest();
        request.setUsername(userName);
        request.setPassword("틀린패스워드 입력했습니다..");

        // When
        boolean result = authService.login(request);

        // Then
        assertFalse(result);
    }
}
