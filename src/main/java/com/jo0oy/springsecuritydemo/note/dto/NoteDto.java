package com.jo0oy.springsecuritydemo.note.dto;

import com.jo0oy.springsecuritydemo.note.Note;

import java.time.LocalDateTime;
import java.util.Objects;

public record NoteDto(
    Long id,
    String title,
    String content,
    String username,
    LocalDateTime createdAt,
    LocalDateTime modifiedAt
) {
    public NoteDto(Note entity) {
        this(
            entity.getId(),
            entity.getTitle(),
            entity.getContent(),
            entity.getUser().getUsername(),
            entity.getCreatedAt(),
            Objects.isNull(entity.getModifiedAt()) ? entity.getCreatedAt() : entity.getModifiedAt()
        );
    }
}
