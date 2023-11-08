package com.jo0oy.springsecuritydemo.notice;

import com.jo0oy.springsecuritydemo.global.auditing.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notice")
@Entity
public class Notice extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Builder
    private Notice(String title,
                   String content) {
        this.title = title;
        this.content = content;
    }
}
