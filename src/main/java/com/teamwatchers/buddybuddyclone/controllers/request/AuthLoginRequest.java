package com.teamwatchers.buddybuddyclone.controllers.request;

import lombok.Getter;
import lombok.Setter;

public class AuthLoginRequest {
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String password;
}
