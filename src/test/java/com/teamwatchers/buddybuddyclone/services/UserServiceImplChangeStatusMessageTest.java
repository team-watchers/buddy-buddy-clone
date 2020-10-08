package com.teamwatchers.buddybuddyclone.services;

import com.teamwatchers.buddybuddyclone.daos.UserDao;
import com.teamwatchers.buddybuddyclone.models.User;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserServiceImplChangeStatusMessageTest {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Test
    public void testSuccess() {
        // Given
        User user = User.builder()
            .username("user1")
            .password("secret")
            .statusMessage("before")
            .build();

        userDao.save(user);

        // When
        User changedUser = userService.changeStatusMessage(user.getId(), "after");

        System.out.println("test : " + changedUser.getStatusMessage());

        // Then
        assertNotNull(changedUser);
        assertEquals(user.getId(), changedUser.getId());
        assertEquals("after", changedUser.getStatusMessage());
    }
}
