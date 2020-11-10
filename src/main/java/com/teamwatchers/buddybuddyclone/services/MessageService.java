package com.teamwatchers.buddybuddyclone.services;

import com.teamwatchers.buddybuddyclone.models.Friend;
import com.teamwatchers.buddybuddyclone.models.Message;

public interface MessageService {
    Message send(Friend friend, String content);
    Message getDetail(Long messageId);
}
