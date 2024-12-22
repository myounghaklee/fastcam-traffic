package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNickNameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberWriteService {
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;
    private final MemberRepository memberRepository;
    public Member create(RegisterMemberCommand command){
        var member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthDate())
                .build();
        var savedMember = memberRepository.save(member);
        savedMemberNicknameHistory(savedMember);

        return memberRepository.save(member);
    }

    private void savedMemberNicknameHistory(Member member) {
        var history = MemberNickNameHistory.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();
        memberNicknameHistoryRepository.save(history);
    }

    public void changeNickname(Long memberId, String nickname){
        var member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);
        memberRepository.save(member);
        //TODO: save teh change history
        savedMemberNicknameHistory(member);
    }
}
