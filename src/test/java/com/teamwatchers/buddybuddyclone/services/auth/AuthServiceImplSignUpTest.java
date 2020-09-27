package com.teamwatchers.buddybuddyclone.services.auth;

import com.teamwatchers.buddybuddyclone.controllers.request.AuthSignUpRequest;
import com.teamwatchers.buddybuddyclone.daos.UserDao;
import com.teamwatchers.buddybuddyclone.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AuthServiceImplSignUpTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserDao userDao;

    @Test
    public void test() {
        // Given
        String username = "test";
        String matchingPassword = "1234";
        String password = "1234";

        AuthSignUpRequest request = new AuthSignUpRequest();

        request.setUsername(username);
        request.setMatchingPassword(matchingPassword);
        request.setPassword(password);

        // When
        boolean result = authService.signUp(request);

        // Then
        assertTrue(result);
    }

    @Test
    public void testNotEqualsPassword() {
        // Given
        String username = "test";
        String matchingPassword = "12345";
        String password = "1234";

        AuthSignUpRequest request = new AuthSignUpRequest();

        request.setUsername(username);
        request.setMatchingPassword(matchingPassword);
        request.setPassword(password);

        // When
        boolean result = authService.signUp(request);

        // Then
        assertFalse(result);
    }

    @Test
    public void testAlreadySignedUp() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            // Given
            String username = "test";
            String matchingPassword = "1234";
            String password = "1234";

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            // When
            userDao.save(user);

            AuthSignUpRequest request = new AuthSignUpRequest();

            request.setUsername(username);
            request.setMatchingPassword(matchingPassword);
            request.setPassword(password);

            // Then
            authService.signUp(request);
        });
    }
}
