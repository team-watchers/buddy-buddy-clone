package com.teamwatchers.buddybuddyclone.controllers;

import com.teamwatchers.buddybuddyclone.controllers.request.MessageSendRequest;
import com.teamwatchers.buddybuddyclone.controllers.request.UserChangeStatusRequest;
import com.teamwatchers.buddybuddyclone.dtos.MessageDto;
import com.teamwatchers.buddybuddyclone.models.Friend;
import com.teamwatchers.buddybuddyclone.models.Message;
import com.teamwatchers.buddybuddyclone.services.FriendService;
import com.teamwatchers.buddybuddyclone.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {
    private final MessageService messageService;
    private final FriendService friendService;

    @Autowired
    public MessageController(MessageService messageService, FriendService friendService) {
        this.messageService = messageService;
        this.friendService = friendService;
    }

    @RequestMapping(value = "/message/send", method = RequestMethod.POST)
    public ResponseEntity<?> send(
            @RequestBody MessageSendRequest request,
            HttpServletResponse response
    ) {
        Friend friend = friendService.getFriend(request.getUserId(), request.getFriendId());
        messageService.send(friend, request.getContents());

        return ResponseEntity.ok(true);
    }

    @RequestMapping(path = "/message/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDetail(
        @PathVariable("id") Long messageId,
        HttpServletResponse response
    ) {
        Message message = messageService.getDetail(messageId);
        MessageDto dto = MessageDto.builder()
            .sender(message.getSender())
            .contents(message.getContents())
            .sentAt(message.getSentAt())
            .build();

        return ResponseEntity.ok(dto);
    }

}
