package com.example.server.domain.member;

import com.example.server.domain.member.dto.MemberDto;
import com.example.server.domain.member.entity.Member;
import com.example.server.domain.member.repository.MemberRepository;
import com.example.server.domain.member.services.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @Mock
    MemberRepository memberRepository;

    @Nested
    @DisplayName("성공 케이스")
    class Success {

        @Test
        @DisplayName("로그인을 위해 멤버 정보를 가져온다.")
        void signIn() {
            MemberDto memberDto = new MemberDto(null, "이름", "닉네임", LocalDate.of(2022, Month.JUNE, 30));
            Member member = new Member("UUID1", "이름", "닉네임", LocalDate.of(2022, Month.JUNE, 30));
            when(memberRepository.findByNameAndNickname("이름", "닉네임")).thenReturn(member);

            MemberService memberService = new MemberService(memberRepository);
            MemberDto resultMemberDto = memberService.signIn(memberDto);

            assertThat(resultMemberDto).isNotNull();
            assertThat(resultMemberDto.name()).isEqualTo(memberDto.name());
            assertThat(resultMemberDto.nickname()).isEqualTo(memberDto.nickname());
            assertThat(resultMemberDto.birthday()).isEqualTo(memberDto.birthday());
            assertThat(resultMemberDto.id()).isNotNull();
        }
    }
}
