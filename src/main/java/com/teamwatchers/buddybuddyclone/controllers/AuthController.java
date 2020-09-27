package com.teamwatchers.buddybuddyclone.controllers;

import com.teamwatchers.buddybuddyclone.controllers.request.AuthSignUpRequest;
import com.teamwatchers.buddybuddyclone.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody AuthSignUpRequest request, HttpServletResponse response) {
        boolean isSignUp = authService.signUp(request);

        return isSignUp ? ResponseEntity.ok(true) : ResponseEntity.status(422).body(false);
    }
}
