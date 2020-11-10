package com.teamwatchers.buddybuddyclone.controllers.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class MessageSendRequest {
    @Getter @Setter
    private long userId;
    @Getter @Setter
    private long friendId;
    @Getter @Setter
    private String contents;
}
