package com.example.server.domains.member.dto;

import com.example.server.domains.member.entity.Member;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;

/**
 * @param id UUID 패턴 검사 정규식 @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", message = "잘못된 회원 고유 번호")
 */
@Builder
public record MemberDto(
        @Nullable String id,
        @NotNull(message = "Null 데이터 입력")
        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]+$", message = "이름 형식이 잘못되었습니다.")
        @Size(min = 2, max = 15, message = "이름은 2글자에서 15글자 사이입니다.")
        String name,
        @NotNull(message = "Null 데이터 입력")
        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]+$", message = "닉네임 형식이 잘못되었습니다.")
        @Size(min = 3, max = 20, message = "닉네임은 3글자에서 20글자 사이입니다.")
        String nickname,
        @Nullable @PastOrPresent(message = "생년월일이 잘못됐습니다.")
        LocalDate birthday
) {
        public static MemberDto toDto(Member member) {
                return MemberDto.builder()
                        .id(member.getId())
                        .name(member.getName())
                        .nickname(member.getNickname())
                        .birthday(member.getBirthday())
                        .build();
        }
}