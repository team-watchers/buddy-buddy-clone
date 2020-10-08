package com.teamwatchers.buddybuddyclone.services;

import com.teamwatchers.buddybuddyclone.models.User;

public interface UserService {
    User changeStatusMessage(Long userId, String statusMessage);
}
