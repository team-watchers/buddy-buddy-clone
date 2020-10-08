package com.teamwatchers.buddybuddyclone.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.teamwatchers.buddybuddyclone.controllers.request.UserChangeStatusRequest;
import com.teamwatchers.buddybuddyclone.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user/change-status-message", method = RequestMethod.PATCH)
    public ResponseEntity<?> changeStatusMessage(
        @RequestBody UserChangeStatusRequest request,
        HttpServletResponse response
    ) {
        userService.changeStatusMessage(request.getUserId(), request.getStatusMessage());

        return ResponseEntity.ok(true);
    }

}
