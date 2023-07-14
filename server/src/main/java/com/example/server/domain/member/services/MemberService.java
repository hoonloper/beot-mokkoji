package com.example.server.domain.member.services;

import com.example.server.application.exceptions.NotFoundException;
import com.example.server.application.exceptions.UnauthorizedException;
import com.example.server.domain.member.dto.MemberDto;
import com.example.server.domain.member.entity.Member;
import com.example.server.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    /* READ */
    public MemberDto signIn(MemberDto memberDto) {
        Member member = memberRepository.findByNameAndNickname(memberDto.name(), memberDto.nickname());
        if(member == null) {
            throw new NotFoundException("회원 정보를 찾을 수 없습니다.");
        }
        return toDto(member);
    }

    private MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getName(), member.getNickname(), member.getBirthday());
    }

    /* WRITE */
    public MemberDto signUp(MemberDto memberDto) {
        Member foundMember = memberRepository.findByNameAndNickname(memberDto.name(), memberDto.nickname());
        if(foundMember != null) {
            throw new UnauthorizedException("이미 가입된 회원입니다.");
        }
        String memberUUID = memberDto.id() == null ? UUID.randomUUID().toString() : memberDto.id();
        Member member = memberRepository.save(new Member(memberUUID, memberDto.name(), memberDto.nickname(), memberDto.birthday()));
        return toDto(member);
    }
}
