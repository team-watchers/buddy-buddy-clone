package com.teamwatchers.buddybuddyclone.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamwatchers.buddybuddyclone.controllers.request.UserChangeStatusRequest;
import com.teamwatchers.buddybuddyclone.models.User;
import com.teamwatchers.buddybuddyclone.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerChangeStatusMessageTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UserService userService;

    @Test
    public void testSuccess() throws Exception {
        // Given
        Long userId = (long) 1;
        String statusMessage = "after";

        UserChangeStatusRequest request = UserChangeStatusRequest.builder()
            .userId(userId)
            .statusMessage(statusMessage)
            .build();

        User user = User.builder().build();

        when(userService.changeStatusMessage(anyLong(), anyString()))
            .thenReturn(null);

        mvc = MockMvcBuilders.webAppContextSetup(context).build();

        ObjectMapper mapper = new ObjectMapper();

        // When & Then
        mvc.perform(
            patch("/v1/user/change-status-message")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        ).andExpect(status().isOk());
    }

}
