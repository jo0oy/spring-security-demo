package com.jo0oy.springsecuritydemo.notice.dto;

import com.jo0oy.springsecuritydemo.notice.Notice;

import java.time.LocalDateTime;
import java.util.Objects;

public record NoticeDto(
    Long id,
    String title,
    String content,
    LocalDateTime createdAt,
    LocalDateTime modifiedAt
) {
    public NoticeDto(Notice entity) {
        this(
            entity.getId(),
            entity.getTitle(),
            entity.getContent(),
            entity.getCreatedAt(),
            Objects.isNull(entity.getModifiedAt())
                ? entity.getCreatedAt() : entity.getModifiedAt()
        );
    }
}
