package com.teamwatchers.buddybuddyclone.controllers.request;

import lombok.Getter;
import lombok.Setter;

public class AuthSignUpRequest {
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String matchingPassword;
}
