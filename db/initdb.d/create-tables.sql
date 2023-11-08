
create table user (
                        id bigint not null auto_increment,
                        created_at datetime(6),
                        modified_at datetime(6),
                        username varchar(100) not null,
                        password varchar(255) not null,
                        authority varchar(50),
                        primary key (id)
) engine=InnoDB;

create table note (
                        id bigint not null auto_increment,
                        created_at datetime(6),
                        modified_at datetime(6),
                        title varchar(200) not null,
                        content text,
                        user_id bigint not null,
                        primary key (id)
) engine=InnoDB;

### insert user test data
## admin, adminpass
insert into user(created_at, modified_at, username, password, authority)
values ('2023-10-20 10:00:30', '2023-10-21 14:01:25', 'admin', '{bcrypt}$2a$10$3CR/6fXZpkQSBLOeqUPrwuYaN/xDYZs2rEletzOS9tETJClAdR.3K', 'ROLE_ADMIN');

## user1, user1pass
insert into user(created_at, modified_at, username, password, authority)
values ('2023-10-24 23:05:10', null, 'user1', '{bcrypt}$2a$10$YsjU8sx7w0vlKdSqHUUKa.FShb8cjq26rPgEBjNEC7BZK5WOMyA4m', 'ROLE_USER');

## user2, user2pass
insert into user(created_at, modified_at, username, password, authority)
values ('2023-10-24 23:20:24', null, 'user2', '{bcrypt}$2a$10$HUnd0lcn3tTxeko2QXBuHesSbZTi0pZDTV/zUFbI5FgqFVl4yxMwS', 'ROLE_USER');

### insert note test data
insert into note(created_at, modified_at, title, content, user_id)
values ('2023-11-01 22:10:03', null, '테스트1', '첫번째 테스트 글 내용입니다.', 2);

insert into note(created_at, modified_at, title, content, user_id)
values ('2023-11-02 15:30:00', '2023-11-03 22:00:10', '테스트2', '두번째 테스트 글 내용입니다.', 3);

insert into note(created_at, modified_at, title, content, user_id)
values ('2023-11-02 22:45:10', null, '테스트3', '세번째 테스트 글 내용입니다.', 3);

insert into note(created_at, modified_at, title, content, user_id)
values ('2023-11-04 21:25:00', null, '여름 여행 계획', '여름 여행 계획 작성중...', 2);
