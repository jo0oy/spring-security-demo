package com.jo0oy.springsecuritydemo.note.dto;

import com.jo0oy.springsecuritydemo.note.Note;
import com.jo0oy.springsecuritydemo.user.User;

public record NoteRegisterDto(
    String title,
    String content
) {
    public Note toEntity(User user) {
        return Note.builder()
            .title(this.title)
            .content(this.content)
            .user(user)
            .build();
    }
}
