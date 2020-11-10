package com.teamwatchers.buddybuddyclone.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(unique = true, nullable = false)
    @Getter @Setter
    private String username;

    @Column(nullable = false)
    @Getter @Setter
    private String password;

    @Getter @Setter
    private String statusMessage;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @Getter
    private List<Friend> friends = new ArrayList<>();

    public void addFriend(User toBeFriend) {
        Friend friend = new Friend();
        friend.setUser(this);
        friend.setFriend(toBeFriend);
        friends.add(friend);
    }
}
