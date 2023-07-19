package com.example.server.domain.member;

import com.example.server.domain.member.dto.MemberDto;
import com.example.server.domain.member.entity.Member;
import com.example.server.domain.member.repository.MemberRepository;
import com.example.server.domain.member.services.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @Mock
    MemberRepository memberRepository;

    @Nested
    @DisplayName("성공 케이스")
    class Success {
        private final String NAME = "이름";
        private final String NICKNAME = "닉네임";
        private final LocalDate BIRTHDAY = LocalDate.of(2022, Month.JUNE, 30);
        private MemberDto memberDto;

        @BeforeEach
        void setup() {
            memberDto = new MemberDto(null, NAME, NICKNAME, BIRTHDAY);
        }

        @Test
        @DisplayName("로그인 정보로 멤버 정보 가져오기 테스트")
        void signIn() {
            Member member = new Member("UUID1", NAME, NICKNAME, BIRTHDAY);
            when(memberRepository.findByNameAndNickname(NAME, NICKNAME)).thenReturn(member);

            MemberService memberService = new MemberService(memberRepository);
            MemberDto resultMemberDto = memberService.signIn(memberDto);

            assertThat(resultMemberDto).isNotNull();
            assertThat(resultMemberDto.getName()).isEqualTo(memberDto.getName());
            assertThat(resultMemberDto.getNickname()).isEqualTo(memberDto.getNickname());
            assertThat(resultMemberDto.getBirthday()).isEqualTo(memberDto.getBirthday());
            assertThat(resultMemberDto.getId()).isNotNull().isEqualTo("UUID1");
        }

        @Test
        @DisplayName("입력한 정보로 회원가입 테스트")
        void signUp() {
            when(memberRepository.save(any(Member.class))).thenReturn(new Member("NEW-UUID", NAME, NICKNAME, BIRTHDAY));

            MemberService memberService = new MemberService(memberRepository);
            MemberDto resultMemberDto = memberService.signUp(memberDto);

            assertThat(resultMemberDto).isNotNull();
            assertThat(resultMemberDto.getName()).isEqualTo(memberDto.getName());
            assertThat(resultMemberDto.getNickname()).isEqualTo(memberDto.getNickname());
            assertThat(resultMemberDto.getBirthday()).isEqualTo(memberDto.getBirthday());
            assertThat(resultMemberDto.getId()).isNotNull().isEqualTo("NEW-UUID");
        }
    }
}
