package com.jo0oy.springsecuritydemo.user.dto;

import com.jo0oy.springsecuritydemo.user.User;

public record UserRegisterDto(
    String username,
    String password
) {
    public User toEntity(String authority) {
        return User.builder()
            .username(this.username)
            .password(this.password)
            .authority(authority)
            .build();
    }
}
