package com.example.server.domains.beot.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record BeotDto(
        @Nullable
        @Min(value = 1L, message = "잘못된 고유 번호입니다.")
        Long id,

        @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", message = "잘못된 회원 고유 번호")
        @NotNull
        String fromMemberId,

        @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", message = "잘못된 회원 고유 번호")
        @NotNull
        String toMemberId,

        @PastOrPresent(message = "생년월일이 잘못됐습니다.")
        LocalDateTime createdAt
) {}
