package com.example.server.domain.room.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
public class RoomDto {
        // 한글, 영어, 숫자만 입력 가능
        @NotNull(message = "Null 데이터 입력")
        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]+$", message = "채팅방 이름 형식이 잘못되었습니다.")
        @Size(min = 3, max = 30, message = "채팅방 이름은 3글자에서 30글자 사이입니다.")
        String name;

        // UUID 패턴 검사 정규식
        // @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", message = "잘못된 회원 고유 번호")
        @NotNull(message = "Null 데이터 입력")
        String memberId;

        @Builder
        public RoomDto(String name, String memberId) {
                this.name = name;
                this.memberId = memberId;
        }
}
