package com.teamwatchers.buddybuddyclone.services;

import com.teamwatchers.buddybuddyclone.daos.FriendDao;
import com.teamwatchers.buddybuddyclone.daos.MessageDao;
import com.teamwatchers.buddybuddyclone.daos.UserDao;
import com.teamwatchers.buddybuddyclone.models.Friend;
import com.teamwatchers.buddybuddyclone.models.Message;
import com.teamwatchers.buddybuddyclone.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class MessageServiceImplSendTest {
    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private FriendDao friendDao;

    @Test
    public void test() {
        // Given
        Clock clock = Clock.fixed(Instant.parse("2014-12-22T10:15:30.00Z"), ZoneId.of("UTC"));
        String dateTimeExpected = "2014-12-22T10:15:30";
        MessageService messageService = new MessageServiceImpl(messageDao, clock);

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
        String contents = "쪽지";

        // When
        Message message = messageService.send(friend, contents);

        // Then
        assertEquals(contents, message.getContents());
        assertEquals(friend.getId(), message.getFriend().getId());
        assertEquals(dateTimeExpected, message.getSentAt().toString());
    }
}
