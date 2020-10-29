package com.teamwatchers.buddybuddyclone.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamwatchers.buddybuddyclone.controllers.request.AuthLoginRequest;
import com.teamwatchers.buddybuddyclone.services.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthControllerLoginTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private AuthService authService;

    @Test
    public void testLoginSuccess() throws Exception {
        // Given
        String username = "test";
        String password = "12345";

        AuthLoginRequest request = new AuthLoginRequest();
        request.setUsername(username);
        request.setPassword(password);

        when(authService.login(any())).thenReturn(true);

        mvc = MockMvcBuilders.webAppContextSetup(context).build();

        ObjectMapper mapper = new ObjectMapper();

        // When
        MockHttpServletResponse response = mvc.perform(
            post("/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        ).andReturn().getResponse();

        // Then
        assertEquals(200, response.getStatus());
    }
}
