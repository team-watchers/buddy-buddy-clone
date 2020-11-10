package com.teamwatchers.buddybuddyclone.services;

import com.teamwatchers.buddybuddyclone.daos.MessageDao;
import com.teamwatchers.buddybuddyclone.models.Friend;
import com.teamwatchers.buddybuddyclone.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageDao messageDao;
    private Clock clock;

    @Autowired
    public MessageServiceImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
        this.clock = Clock.systemDefaultZone();
    }

    public MessageServiceImpl(MessageDao messageDao, Clock clock) {
        this.messageDao = messageDao;
        this.clock = clock;
    }

    @Override
    public Message send(Friend friend, String content) {
        Message message = new Message();
        message.setContents(content);
        message.setFriend(friend);
        message.setSentAt(LocalDateTime.now(clock));

        return messageDao.save(message);
    }

    @Override
    public Message getDetail(Long messageId) {
        Message message = messageDao.findById(messageId).orElseThrow(
            // TODO:: NotFoundEntityException 구현
            () -> new RuntimeException("그런 메세지는 없어요.")
        );

        return message;
    }
}
