package com.teamwatchers.buddybuddyclone.services;

import java.time.LocalDateTime;

import com.teamwatchers.buddybuddyclone.daos.FriendDao;
import com.teamwatchers.buddybuddyclone.daos.MessageDao;
import com.teamwatchers.buddybuddyclone.daos.UserDao;
import com.teamwatchers.buddybuddyclone.models.Friend;
import com.teamwatchers.buddybuddyclone.models.Message;
import com.teamwatchers.buddybuddyclone.models.User;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class MessageServiceImplGetDetailTest {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private FriendDao friendDao;

    @Test
    public void test() {
        // Given
        User from = new User();
        from.setUsername("조두희");
        from.setPassword("password");

        User to = new User();
        to.setUsername("박현도");
        to.setPassword("password");

        userDao.save(to);
        userDao.save(from);

        Friend friend = new Friend();
        friend.setUser(from);
        friend.setFriend(to);
        friendDao.save(friend);

        Message message = new Message();
        message.setContents("Hello");
        message.setFriend(friend);
        message.setSentAt(LocalDateTime.now());
        messageDao.save(message);

        // When
        Message result = messageService.getDetail(message.getId());

        // Then
        assertNotNull(result);
        assertEquals(message, result);
    }

    @Test
    public void testNotFoundException() {
        // Given

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            messageService.getDetail((long) 1);
        });

        assertEquals("그런 메세지는 없어요.", exception.getMessage());
    }
}
