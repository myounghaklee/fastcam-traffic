package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.service.FollowReadService;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetFollowingMemberUsecase {
    private final MemberReadService memberReadService;
    private final FollowReadService followReadService;

    public List<MemberDto> execute(Long memberId){
        /*
        1. fromMemberId = memberId -> Follow List
        2. 1번을 순회하면서 회원정보 찾기
         */
        var followings = followReadService.getFollowings(memberId);
    }
}
