package com.teamwatchers.buddybuddyclone.models;

import com.teamwatchers.buddybuddyclone.daos.FriendDao;
import com.teamwatchers.buddybuddyclone.daos.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void test() {
        // Given & When
        User user = new User();
        user.setUsername("구리왕만두");
        user.setPassword("secret");
        user.setStatusMessage("10시 이후엔 쪽지ㄴ");
        userDao.save(user);

        // Then
        Iterable<User> users = userDao.findAll();

        User userFromDB = users.iterator().next();

        assertNotNull(userFromDB);
        assertEquals(userFromDB.getId(), user.getId());
        assertEquals(userFromDB.getUsername(), user.getUsername());
        assertEquals(userFromDB.getPassword(), user.getPassword());
        assertEquals(userFromDB.getStatusMessage(), user.getStatusMessage());
    }

    @Test
    public void testWithFriends() {
        // Given
        User user1 = new User();
        user1.setUsername("구리왕만두");
        user1.setPassword("secret");
        user1.setStatusMessage("10시 이후엔 쪽지ㄴ");

        User user2 = new User();
        user2.setUsername("판교왕교자");
        user2.setPassword("secret");
        user2.setStatusMessage("11시 이후엔 쪽지ㄴ");
        userDao.save(user2);

        // When
        user1.addFriend(user2);
        userDao.save(user1);

        // Then
        user1 = userDao.findById(user1.getId()).orElse(null);
        Friend friend = user1.getFriends().iterator().next();
        assertEquals(user1.getId(), friend.getUser().getId());
        assertEquals(user2.getId(), friend.getFriend().getId());
    }
}
