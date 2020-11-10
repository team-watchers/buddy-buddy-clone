package com.teamwatchers.buddybuddyclone.controllers;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamwatchers.buddybuddyclone.controllers.request.MessageSendRequest;
import com.teamwatchers.buddybuddyclone.models.Friend;
import com.teamwatchers.buddybuddyclone.models.Message;
import com.teamwatchers.buddybuddyclone.models.User;
import com.teamwatchers.buddybuddyclone.services.FriendService;
import com.teamwatchers.buddybuddyclone.services.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MessageControllerGetDetailTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private FriendService friendService;

    @MockBean
    private MessageService messageService;

    @Test
    public void test() throws Exception
    {
        // Given
        User sender = User.builder()
            .username("user1")
            .password("secret")
            .build();

        Friend friend = Friend.builder()
            .user(sender)
            .build();

        Message message = Message.builder()
            .friend(friend)
            .contents("zzzzzz")
            .sentAt(LocalDateTime.now())
            .build();

        when(messageService.getDetail(anyLong()))
            .thenReturn(message);

        mvc = MockMvcBuilders.webAppContextSetup(context).build();

        // When & Then
        mvc.perform(
            get("/v1/message/1")
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.sender").value("user1"))
        .andExpect(jsonPath("$.contents").value("zzzzzz"));
    }
}
