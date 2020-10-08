package com.teamwatchers.buddybuddyclone.dtos;

import com.teamwatchers.buddybuddyclone.models.User;

public class UserDto implements ModelDto {
    private Long id;

    private String username;

    private String statusMessage;

    @Override
    public User toEntity() {
        return User.builder()
            .id(id)
            .username(username)
            .statusMessage(statusMessage)
            .build();
    }
}
