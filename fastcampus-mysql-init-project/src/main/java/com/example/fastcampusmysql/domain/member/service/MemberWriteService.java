package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;

public class MemberWriteService {
    public void create(RegisterMemberCommand command){
        /*
         목표 : 회원정보 등록
         - 닉네임 10자 넘으면 안됨
         파라미터 : memberRegisterCommand
         val member = Member.fo(memberRegisterCommand)
         memberRepoistory.save();
         */
        var member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthDate())
                .build();
    }
}
