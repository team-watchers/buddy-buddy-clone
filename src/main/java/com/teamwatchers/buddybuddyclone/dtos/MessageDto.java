package com.teamwatchers.buddybuddyclone.dtos;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class MessageDto implements ModelDto {
    @Getter @Setter
    private String sender;

    @Getter @Setter
    private LocalDateTime sentAt;

    @Getter @Setter
    private String contents;

    @Override
    public Object toEntity() {
        return null;
    }
}
