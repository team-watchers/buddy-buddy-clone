package com.teamwatchers.buddybuddyclone.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
}
