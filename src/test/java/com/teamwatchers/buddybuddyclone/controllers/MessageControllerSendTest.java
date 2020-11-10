package com.teamwatchers.buddybuddyclone.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamwatchers.buddybuddyclone.controllers.request.MessageSendRequest;
import com.teamwatchers.buddybuddyclone.models.Friend;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MessageControllerSendTest {
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
        long userId = 1;
        long friendId = 2;
        String sentContents = "테스트 쪽지";

        MessageSendRequest request = MessageSendRequest.builder()
            .userId(userId)
            .friendId(friendId)
            .contents(sentContents)
            .build();

        User user = User.builder().build();
        Friend friend = Friend.builder().build();

        when(friendService.getFriend(anyLong(), anyLong()))
            .thenReturn(friend);

        when(messageService.send(friend, sentContents))
            .thenReturn(null);

        mvc = MockMvcBuilders.webAppContextSetup(context).build();

        ObjectMapper mapper = new ObjectMapper();

        // When & Then
        mvc.perform(
            post("/v1/message/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        ).andExpect(status().isOk());
    }
}
