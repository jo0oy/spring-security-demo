package com.jo0oy.springsecuritydemo.notice.dto;

import com.jo0oy.springsecuritydemo.notice.Notice;

public record NoticeRegisterDto(
    String title,
    String content
) {
    public Notice toEntity() {
        return Notice.builder()
            .title(this.title)
            .content(this.content)
            .build();
    }
}
