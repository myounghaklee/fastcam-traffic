package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.member.service.FollowWriteService;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import com.example.fastcampusmysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateFollowMemberUsecase {

    private final MemberReadService memberReadService;
    private final MemberWriteService memberWriteService;
    private final FollowWriteService followWriteService;

    public void execute(Long fromMemberId, Long toMemberId){
        var fromMember = memberReadService.getMember(fromMemberId);
        var toMember = memberReadService.getMember(toMemberId);
        followWriteService.create(fromMember, toMember);
    }

}
