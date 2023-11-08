package com.jo0oy.springsecuritydemo.note;

import com.jo0oy.springsecuritydemo.global.auditing.BaseTimeEntity;
import com.jo0oy.springsecuritydemo.user.User;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "note")
@Entity
public class Note extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    private Note(String title,
                 String content,
                 User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update(String title,
                       String content) {
        this.title = title;
        this.content = content;
    }
}
