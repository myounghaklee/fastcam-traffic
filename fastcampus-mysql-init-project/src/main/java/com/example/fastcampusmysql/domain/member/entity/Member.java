package com.example.fastcampusmysql.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Member {
    private final Long id;
    private String nickname;
    private final String email;
    private final LocalDate birthday;
    private final LocalDateTime createAt;

    private final static Long NAME_MAX_LENGTH = 10L;

    @Builder
    public Member(Long id, String nickname, String email, LocalDate birthday, LocalDateTime createAt) {
        this.id = id;
        validateNickname(nickname);
        this.nickname = Objects.requireNonNull(nickname);
        this.email = Objects.requireNonNull(email);
        this.birthday = Objects.requireNonNull(birthday);
        this.createAt = createAt==null ? LocalDateTime.now() :createAt;
    }

    public void changeNicname(String other){
        Objects.requireNonNull(other);
        validateNickname(other);
        nickname = other;
    }

    void validateNickname(String nickname){
        Assert.isTrue(nickname.length() <= NAME_MAX_LENGTH, "최대길이를 초과할 수 없습니다.");
    }
}
