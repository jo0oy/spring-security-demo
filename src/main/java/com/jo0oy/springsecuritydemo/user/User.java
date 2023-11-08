package com.jo0oy.springsecuritydemo.user;

import com.jo0oy.springsecuritydemo.global.auditing.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String authority;

    @Builder
    private User(String username,
                 String password,
                 String authority) {
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public Boolean isAdmin() {
        return this.authority.equals("ROLE_ADMIN");
    }
}
