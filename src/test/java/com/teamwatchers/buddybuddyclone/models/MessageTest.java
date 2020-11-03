package com.teamwatchers.buddybuddyclone.models;

import com.teamwatchers.buddybuddyclone.daos.FriendDao;
import com.teamwatchers.buddybuddyclone.daos.MessageDao;
import com.teamwatchers.buddybuddyclone.daos.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MessageTest {
    @Autowired
    private UserDao userDao;

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private MessageDao messageDao;

    @Test
    public void test() {
        // Given
        Message message = new Message();

        User from = new User();
        from.setUsername("조두희");
        from.setPassword("password");

        User to = new User();
        to.setUsername("박현도");
        to.setPassword("password");

        userDao.save(to);
        userDao.save(from);

        from.addFriend(to);
        userDao.save(to);

        String contents = "안녕하세요???";
        LocalDateTime sentAt = LocalDateTime.now();

        Friend friend = friendDao.findAll().iterator().next();

        // When
        message.setContents(contents);
        message.setSentAt(sentAt);
        message.setFriend(friend);
        messageDao.save(message);

        // Then
        message = messageDao.findAll().iterator().next();

        assertEquals(contents, message.getContents());
        assertEquals(sentAt.toString(), message.getSentAt().toString());
        assertEquals(friend.getId(), message.getFriend().getId());
    }
}
