package com.teamwatchers.buddybuddyclone.models;

import com.teamwatchers.buddybuddyclone.daos.FriendDao;
import com.teamwatchers.buddybuddyclone.daos.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class FriendTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private FriendDao friendDao;

    @Test
    public void test() {
        // Given
        User user = new User();
        user.setUsername("구리왕만두");
        user.setPassword("secret");
        user.setStatusMessage("10시 이후엔 쪽지ㄴ");
        userDao.save(user);

        Friend friend = new Friend();
        friend.setUser(user);

        // When
        friendDao.save(friend);

        // Then
        assertEquals(user, friend.getUser());
    }
}
