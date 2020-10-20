package com.teamwatchers.buddybuddyclone.services.auth;

import com.teamwatchers.buddybuddyclone.controllers.request.AuthLoginRequest;
import com.teamwatchers.buddybuddyclone.controllers.request.AuthSignUpRequest;

public interface AuthService {
    public boolean signUp(AuthSignUpRequest request);
    public boolean login(AuthLoginRequest request);
}
