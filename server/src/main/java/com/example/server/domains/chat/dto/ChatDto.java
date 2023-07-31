package com.example.server.domains.chat.dto;

import lombok.Builder;

@Builder
public record ChatDto(
        String id,
        String message,
        String senderId,
        String senderName,
        String receiverId,
        String roomId,
        String createdAt) {
}
