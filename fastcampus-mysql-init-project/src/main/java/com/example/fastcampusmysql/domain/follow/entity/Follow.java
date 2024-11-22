package com.example.fastcampusmysql.domain.follow.entity;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Follow {
    private final Long id;
    private final Long fromMemberId;
    private final Long toMemberId;
    private final LocalDateTime createdAt;

    public Follow(Long id, Long fromMemberId, Long toMemberId, LocalDateTime createdAt) {
        this.id = id;
        this.fromMemberId = Objects.requireNonNull(fromMemberId);
        this.toMemberId = Objects.requireNonNull(toMemberId);
        this.createdAt = createdAt == null ? LocalDateTime.now():createdAt;
    }
}
