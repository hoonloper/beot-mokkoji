package com.example.server.domains.chat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatDto {
    private ChatEventType type; // 메시지 타입
    private String roomId; // 방 번호
    private String senderId; // 채팅을 보낸 사람
    private String message; // 메시지
    private String sendAt; // 채팅 발송 시간
}