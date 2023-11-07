package com.jo0oy.springsecuritydemo.user.dto;

import com.jo0oy.springsecuritydemo.user.User;

public record UserDto(
    Long id,
    String username,
    String authority
) {
    public UserDto(User entity) {
        this(entity.getId(), entity.getUsername(), entity.getAuthority());
    }
}
