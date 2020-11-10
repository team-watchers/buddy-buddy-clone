package com.teamwatchers.buddybuddyclone.services;

import com.teamwatchers.buddybuddyclone.daos.FriendDao;
import com.teamwatchers.buddybuddyclone.daos.UserDao;
import com.teamwatchers.buddybuddyclone.models.Friend;
import com.teamwatchers.buddybuddyclone.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class FriendServiceImplGetFriendTest {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private FriendDao friendDao;

    @Test
    public void test()
    {
        // Given
        User from = User.builder()
            .username("user1")
            .password("secret")
            .statusMessage("before")
            .build();

        userDao.save(from);

        User to = User.builder()
            .username("user2")
            .password("secret")
            .statusMessage("before")
            .build();

        userDao.save(to);

        Friend friend = new Friend();
        friend.setUser(from);
        friend.setFriend(to);

        friendDao.save(friend);
        
        // When
        Friend getFriend = friendService.getFriend(from.getId(), to.getId());

        // Then
        assertEquals(friend, getFriend);
    }
}
