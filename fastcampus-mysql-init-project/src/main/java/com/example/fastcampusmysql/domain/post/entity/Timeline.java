package com.example.fastcampusmysql.domain.post.entity;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Timeline {

    private final Long id;
    private final Long memberId;
    private final LocalDateTime createdAt;

    @Builder
    public Timeline(Long id, Long memberId, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.createdAt = createdAt == null? LocalDateTime.now() : createdAt;
    }
}
