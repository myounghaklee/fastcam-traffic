package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;

import java.util.List;

public class GetFollowingMemberUsecase {

    public List<MemberDto> execute(Long memberId){
        /*
        1. fromMemberId = memberId -> Follow List
        2. 1번을 순회하면서 회원정보 찾기
         */

    }
}
