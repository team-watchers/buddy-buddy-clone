package com.teamwatchers.buddybuddyclone.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "friend_id")
    private Friend friend;

    @Getter
    @Setter
    @Column(nullable = false)
    private String contents;

    @Getter
    @Setter
    @Column(name = "sent_at", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime sentAt;

    // TODO:: Test ^.^
    public String getSender() {
        return friend != null ? friend.getUser().getUsername() : null;
    }

    // TODO:: Test ^.^
    public String getReceiver() {
        return friend != null ? friend.getFriend().getUsername() : null;
    }
}
