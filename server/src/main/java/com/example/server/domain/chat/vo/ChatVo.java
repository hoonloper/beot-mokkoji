package com.example.server.domain.chat.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChatVo {
    ChatEventType type; // 메시지 타입
    String roomId; // 방 번호
    String senderId; // 채팅을 보낸 사람
    String message; // 메시지
    String sendAt; // 채팅 발송 시간
}