package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.MemberNicknameHistoryDto;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNickNameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberReadService {
    private final MemberNicknameHistoryRepository memberRepository;
    public MemberDto getMember(Long id) {
        var member = memberRepository.findById(id).orElseThrow();

        return toDto(member);
    }

    public List<MemberNicknameHistoryDto> getNicknameHistories(Long memberId){
        
    }

    public MemberDto toDto(Member member){
        return new MemberDto(member.getId(), member.getEmail(), member.getNickname(), member.getBirthday());
    }

    private MemberNicknameHistoryDto toDto(MemberNickNameHistory history){
        return new MemberNicknameHistoryDto(
                history.getId(),
                history.getMemberId(),
                history.getNickname(),
                history.getCreatedAt()
        );
    }
}
