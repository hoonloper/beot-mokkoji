package com.example.server.domains.member.service;

import com.example.server.application.exceptions.NotFoundException;
import com.example.server.application.exceptions.UnauthorizedException;
import com.example.server.domains.member.dto.MemberDto;
import com.example.server.domains.member.entity.Member;
import com.example.server.domains.member.repository.MemberRepository;
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
        return MemberDto.toDto(member);
    }


    /* WRITE */
    public MemberDto signUp(MemberDto memberDto) {
        Member foundMember = memberRepository.findByNameAndNickname(memberDto.name(), memberDto.nickname());
        if(foundMember != null) {
            throw new UnauthorizedException("이미 가입된 회원입니다.");
        }
        return MemberDto.toDto(memberRepository.save(Member
                .builder()
                .id(UUID.randomUUID().toString())
                .name(memberDto.name())
                .nickname(memberDto.nickname())
                .birthday(memberDto.birthday())
                .build()
            ));
    }
}
