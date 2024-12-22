package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.follow.repository.FollowRepository;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowWriteService {

    private final FollowRepository followRepository;
    public void create(MemberDto fromMember, MemberDto toMember){
        

    }
}
