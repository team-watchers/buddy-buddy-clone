package com.teamwatchers.buddybuddyclone.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserChangeStatusRequest {
    @Getter @Setter
    private Long userId;

    @Getter @Setter
    private String statusMessage;
}
