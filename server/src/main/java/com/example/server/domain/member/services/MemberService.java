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
        Member member = memberRepository.findByNameAndNickname(memberDto.getName(), memberDto.getNickname());
        if(member == null) {
            throw new NotFoundException("회원 정보를 찾을 수 없습니다.");
        }
        return MemberDto.of(member);
    }


    /* WRITE */
    public MemberDto signUp(MemberDto memberDto) {
        Member foundMember = memberRepository.findByNameAndNickname(memberDto.getName(), memberDto.getNickname());
        if(foundMember != null) {
            throw new UnauthorizedException("이미 가입된 회원입니다.");
        }
        return MemberDto.of(memberRepository.save(new Member(UUID.randomUUID().toString(), memberDto.getName(), memberDto.getNickname(), memberDto.getBirthday())));
    }
}
