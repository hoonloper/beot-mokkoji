package com.example.server.domains.member.services;

import com.example.server.application.controllers.MemberDto;
import com.example.server.domains.member.entity.Member;
import com.example.server.domains.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberReadService {
    private final MemberRepository memberRepository;

    public MemberDto signIn(MemberDto member) {
        Member mem = memberRepository.findByNameAndNickname(member.name(), member.nickname());
        return toDto(mem);
    }

    private MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getName(), member.getNickname(), member.getBirthday());
    }
}
