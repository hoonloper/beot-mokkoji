package com.example.server.domains.member.services;

import com.example.server.domains.member.dto.MemberDto;
import com.example.server.domains.member.entity.Member;
import com.example.server.domains.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberWriteService {
    private final MemberRepository memberRepository;
    public String signUp(MemberDto member) {
        String memberUUID = member.id() == null ? UUID.randomUUID().toString() : member.id();
        memberRepository.save(new Member(memberUUID, member.name(), member.nickname(), member.birthday()));
        return memberUUID;
    }
}
